package co.com.sofka.tickets.infra.materialize;

import co.com.sofka.tickets.domain.program.event.ServiceAssigned;
import co.com.sofka.tickets.domain.program.event.TicketCreated;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class TicketHandle {

    private final MongoClient mongoClient;

    public TicketHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.ticket.ticketcreated", blocking = true)
    void consumeProgramCreated(TicketCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("urgencylevel", event.getUrgencyLevel());
        document.put("date",event.getLocalDate());

        mongoClient.getDatabase("queries")
                .getCollection("ticket")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofkau.ticket.serviceassigned", blocking = true)
    void consumeTicketCreated(ServiceAssigned event) {
        BasicDBObject document = new BasicDBObject();
        document.put("services."+event.getCourseId()+".name", event.getName());

        event.getCategories().forEach(category -> {
            var key = "services."+event.getCourseId()+".categories."+Math.abs(category.hashCode());
            document.put(key+".name", category);
        });

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("ticket")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }


    /*@ConsumeEvent(value = "sofkau.program.scoreassigned", blocking = true)
    void consumeProgramCreated(ScoreAssigned event) {
        BasicDBObject document = new BasicDBObject();
        var key = "courses."+event.getCourseId()+".categories."+Math.abs(event.getCategory().hashCode());
        document.put(key+".scores."+Math.abs(event.getUser().hashCode())+".user", event.getUser());
        document.put(key+".scores."+Math.abs(event.getUser().hashCode())+".value", event.getValue());
        document.put(key+".scores."+Math.abs(event.getUser().hashCode())+".date", event.getDate());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("program")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }*/
}