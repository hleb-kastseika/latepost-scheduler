package gk.latepost.scheduler.resource;

import gk.latepost.scheduler.model.Publication;
import gk.latepost.scheduler.service.PublicationService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * PublicationResource class.
 * <p>
 * Date: Oct 19, 2019
 * <p>
 *
 * @author Gleb Kosteiko
 */
@Path("/publications")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class PublicationResource {
    @Inject
    private PublicationService publicationService;

    @GET
    public Response getAll() {
        return Response
                .ok(publicationService.getAll())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") long id) {
        Publication publication = publicationService.get(id);
        if (publication != null) {
            return Response.ok(publication).build();
        }
        return Response
                .status(NOT_FOUND)
                .build();
    }

    @POST
    public Response create(Publication publication) {
        return Response
                .status(CREATED)
                .entity(publicationService.create(publication))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") long id, Publication publication) {
        return Response
                .ok(publicationService.update(publication))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        publicationService.delete(id);
        return Response.noContent().entity("").build();
    }
}