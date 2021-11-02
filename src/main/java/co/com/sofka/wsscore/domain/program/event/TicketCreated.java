package co.com.sofka.wsscore.domain.program.event;

import co.com.sofka.wsscore.domain.generic.DomainEvent;

import java.time.LocalDate;

public class TicketCreated extends DomainEvent {

    private final LocalDate localDate;
    private final String urgencyLevel;

    public TicketCreated(LocalDate localDate, String urgencyLevel) {
        super("sofkau.ticket.ticketcreated");
        this.localDate = localDate;
        this.urgencyLevel = urgencyLevel;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }
}
