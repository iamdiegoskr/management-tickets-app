package co.com.sofka.tickets.infra.generic;

import co.com.sofka.tickets.domain.generic.DomainEvent;
import co.com.sofka.tickets.domain.generic.EventStoreRepository;
import co.com.sofka.tickets.domain.generic.StoredEvent;
import co.com.sofka.tickets.infra.MessageService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public abstract class UseCaseHandle {
    @Inject
    private  EventStoreRepository repository;

    @Inject
    private   MessageService messageService;;

    public void saveProgram(String programId, List<DomainEvent> events) {
        events.stream().map(event -> {
            String eventBody = EventSerializer.instance().serialize(event);
            return new StoredEvent(event.getClass().getTypeName(), new Date(), eventBody);
        }).forEach(storedEvent -> repository.saveEvent("program", programId, storedEvent));
        events.forEach(messageService::send);
    }
}
