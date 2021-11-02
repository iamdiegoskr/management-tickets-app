package co.com.sofka.tickets.domain.program.event;

import co.com.sofka.tickets.domain.generic.DomainEvent;

import java.util.List;

public class ServiceAssigned extends DomainEvent {
    private final String courseId;
    private final String name;
    private final List<String> categories;

    public ServiceAssigned(String courseId, String name, List<String> categories) {
        super("sofkau.ticket.serviceassigned");
        this.courseId = courseId;
        this.name = name;
        this.categories = categories;
    }


    public List<String> getCategories() {
        return categories;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }
}
