package co.com.sofka.tickets.infra.handle;

import co.com.sofka.tickets.domain.program.command.AssignScoreCommand;
import co.com.sofka.tickets.infra.generic.UseCaseHandle;
import co.com.sofka.tickets.usecases.ExtractScoreUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AssignScoreUseCaseHandle extends UseCaseHandle {
    private final ExtractScoreUseCase extractScoreUseCase;

    public AssignScoreUseCaseHandle(ExtractScoreUseCase extractScoreUseCase) {
        this.extractScoreUseCase = extractScoreUseCase;
    }

    @ConsumeEvent(value = "sofkau.program.assignscore")
    void consumeBlocking(AssignScoreCommand command) {
        var events = extractScoreUseCase.apply(command);
        saveProgram(command.getProgramId(), events);
    }


}