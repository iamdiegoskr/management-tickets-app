package co.com.sofka.tickets.usecases;

import co.com.sofka.tickets.domain.generic.DomainEvent;
import co.com.sofka.tickets.domain.program.Ticket;
import co.com.sofka.tickets.domain.program.command.CreateTicketCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateProgramUseCase  implements Function<CreateTicketCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreateTicketCommand command) {
        var ticket = new Ticket(command.getTicketId(), command.getDate(), command.getUrgencyLevel());
        return ticket.getUncommittedChanges();
    }
}
