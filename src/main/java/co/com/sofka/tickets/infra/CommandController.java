package co.com.sofka.tickets.infra;


import co.com.sofka.tickets.domain.program.command.AddServiceCommand;
import co.com.sofka.tickets.domain.program.command.AssignScoreCommand;
import co.com.sofka.tickets.domain.program.command.CreateTicketCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/assignScore")
    public Response executor(AssignScoreCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createProgram")
    public Response executor(CreateTicketCommand command) {
        bus.publish(command.getType(), command);//emitir comandos, los casos de uso
        return Response.ok().build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addCourse")
    public Response executor(AddServiceCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

}