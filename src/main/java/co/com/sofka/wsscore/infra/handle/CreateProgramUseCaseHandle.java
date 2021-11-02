package co.com.sofka.wsscore.infra.handle;

import co.com.sofka.wsscore.domain.program.command.CreateTicketCommand;
import co.com.sofka.wsscore.infra.generic.UseCaseHandle;
import co.com.sofka.wsscore.usecases.CreateProgramUseCase;
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