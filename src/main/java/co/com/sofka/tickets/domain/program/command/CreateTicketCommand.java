package co.com.sofka.tickets.domain.program.command;

import co.com.sofka.tickets.domain.generic.Command;

import java.time.LocalDate;

public class CreateTicketCommand extends Command {
    private String ticketId;
    private LocalDate date;
    private String urgencyLevel;

    public CreateTicketCommand(){

    }

    public String getTicketId() {
        return ticketId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }
}
