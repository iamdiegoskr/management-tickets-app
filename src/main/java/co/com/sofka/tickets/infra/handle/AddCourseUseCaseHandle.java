package co.com.sofka.tickets.infra.handle;

import co.com.sofka.tickets.domain.program.command.AddServiceCommand;

import co.com.sofka.tickets.infra.generic.UseCaseHandle;
import co.com.sofka.tickets.usecases.AddServiceUseCase;

import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class AddCourseUseCaseHandle extends UseCaseHandle {
    private final AddServiceUseCase addServiceUseCase;

    public AddCourseUseCaseHandle(AddServiceUseCase addServiceUseCase) {
        this.addServiceUseCase = addServiceUseCase;
    }

    @ConsumeEvent(value = "sofkau.program.addcourse")
    void consumeBlocking(AddServiceCommand command) {
        var events = addServiceUseCase.apply(command);
        saveProgram(command.getProgramId(), events);
    }


}