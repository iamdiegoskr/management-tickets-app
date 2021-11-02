package co.com.sofka.tickets.usecases;

import co.com.sofka.tickets.domain.generic.DomainEvent;
import co.com.sofka.tickets.domain.generic.EventStoreRepository;
import co.com.sofka.tickets.domain.program.Ticket;
import co.com.sofka.tickets.domain.program.command.AddServiceCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class AddServiceUseCase implements Function<AddServiceCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public AddServiceUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddServiceCommand addServiceCommand) {
        var ticket = Ticket.from(
                addServiceCommand.getTicketId(), repository.getEventsBy("program", addServiceCommand.getTicketId())
        );
        ticket.addService(addServiceCommand.getServiceId(), addServiceCommand.getName(), addServiceCommand.getCategories());
        return ticket.getUncommittedChanges();
    }
}
