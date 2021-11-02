package co.com.sofka.wsscore.domain.program;

import co.com.sofka.wsscore.domain.generic.AggregateRoot;
import co.com.sofka.wsscore.domain.generic.DomainEvent;
import co.com.sofka.wsscore.domain.generic.EventChange;
import co.com.sofka.wsscore.domain.program.event.CourseAssigned;
import co.com.sofka.wsscore.domain.program.event.TicketCreated;
import co.com.sofka.wsscore.domain.program.event.ScoreAssigned;

import java.time.LocalDate;
import java.util.*;


public class Ticket extends AggregateRoot implements EventChange {

    private Map<String, Service> services;
    private String urgencyLevel;

    public Ticket(String programId, LocalDate localDate, String urgencyLevel){
        super(programId);
        appendChange(new TicketCreated(localDate, urgencyLevel)).apply();
    }


    /*public void addCourse(String courseId, String name, List<String> categories){
        appendChange(new CourseAssigned(courseId, name, categories)).apply();
    }*/

    /*public void assignScore(String user, String courseId, String category, String value, Date date){
        appendChange(new ScoreAssigned(user, courseId, category, value, date)).apply();
    }*/


    private Ticket(String id){
        super(id);
        subscribe(this);

        listener((TicketCreated event)-> {
          this.urgencyLevel = event.getUrgencyLevel();
          this.services =  new HashMap<>();
        });

        /*listener((CourseAssigned event) -> {
            var course =  new Service(event.getCourseId(), event.getName());
            event.getCategories().forEach(course::addCategory);
            services.put(event.getCourseId(), course);
        });
        listener((ScoreAssigned event) -> {
            var scoreId = event.getCourseId()+event.getCategory()+event.getUser();
            this.scores.put(scoreId, new Score(
                    scoreId, event.getUser(), event.getValue(), event.getDate()
            ));
        });*/

    }

    public static Ticket from(String id, List<DomainEvent> events){
        var program = new Ticket(id);
        events.forEach(program::applyEvent);
        return program;
    }

    public Map<String, Service> getServices() {
        return services;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }
}