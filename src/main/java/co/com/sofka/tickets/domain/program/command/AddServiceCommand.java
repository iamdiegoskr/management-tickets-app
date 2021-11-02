package co.com.sofka.tickets.domain.program.command;

import co.com.sofka.tickets.domain.generic.Command;

import java.util.List;

public class AddServiceCommand extends Command {

    private String ticketId;
    private  String serviceId;
    private String name;
    private List<String> categories;

    public AddServiceCommand(){

    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
