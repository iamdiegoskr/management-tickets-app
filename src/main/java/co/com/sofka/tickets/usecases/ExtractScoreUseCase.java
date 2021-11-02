package co.com.sofka.tickets.usecases;

import co.com.sofka.tickets.domain.generic.DomainEvent;
import co.com.sofka.tickets.domain.generic.EventStoreRepository;
import co.com.sofka.tickets.domain.program.command.AssignScoreCommand;
import co.com.sofka.tickets.domain.program.Ticket;
import co.com.sofka.tickets.infra.DataResponse;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Dependent
public class ExtractScoreUseCase implements Function<AssignScoreCommand, List<DomainEvent>> {
    private static final String URL_BASE = "https://campus.sofka.com.co";
    private final ProcessLogin processLogin;
    private final EventStoreRepository repository;

    public ExtractScoreUseCase(ProcessLogin processLogin, EventStoreRepository repository){
        this.processLogin = processLogin;
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AssignScoreCommand command) {
        processLogin.login();
        var program = Ticket.from(command.getProgramId(),
                repository.getEventsBy("program", command.getProgramId())
        );
        try {
            Connection.Response response = getResponse(command.getPath());
            processLogin.logout();
            new Gson().fromJson(response.body(), DataResponse.class).getData().stream()
                    .filter(d -> d.get(5).contains("Terminado"))
                    .forEach(data -> program.assignScore(html2text(data.get(0)), command.getCourseId(), command.getCategory(),  data.get(6), new Date()));
            return program.getUncommittedChanges();
        } catch (IOException e) {
           throw new ExtractScoreException();
        }
    }

    private Connection.Response getResponse(String path) throws IOException {
        return Jsoup.connect(URL_BASE+"/reports/"+path)
                        .userAgent("Mozilla/5.0")
                        .timeout(10 * 1000)
                        .cookies(processLogin.cookies())
                        .method(Connection.Method.POST)
                        .followRedirects(true)
                        .execute();
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
