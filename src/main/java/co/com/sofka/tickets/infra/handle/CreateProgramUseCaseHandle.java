package co.com.sofka.tickets.infra.handle;

import co.com.sofka.tickets.domain.program.command.CreateTicketCommand;
import co.com.sofka.tickets.infra.generic.UseCaseHandle;
import co.com.sofka.tickets.usecases.CreateProgramUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateProgramUseCaseHandle extends UseCaseHandle {

    private final CreateProgramUseCase createProgramUseCase;

    public CreateProgramUseCaseHandle(CreateProgramUseCase createProgramUseCase) {
        this.createProgramUseCase = createProgramUseCase;
    }

    @ConsumeEvent(value = "sofkau.program.createprogram")
    void consumeBlocking(CreateTicketCommand command) {
        var events = createProgramUseCase.apply(command);
        saveProgram(command.getProgramId(), events);
    }
}