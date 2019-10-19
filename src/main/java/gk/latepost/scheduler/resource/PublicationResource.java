package gk.latepost.scheduler.resource;

import gk.latepost.scheduler.model.Publication;
import gk.latepost.scheduler.service.PublicationService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    @Path("/hello")
    public String hello() {
        return "hello";
    }

    @GET
    public List<Publication> getAll() {
        return publicationService.getAll();
    }

    @GET
    @Path("/{id}")
    public Publication get(@PathParam("id") long id) {
        return publicationService.get(id);
    }

    @POST
    public Publication create(Publication publication) {
        return publicationService.create(publication);
    }

    @PUT
    @Path("/{id}")
    public Publication update(@PathParam("id") long id, Publication publication) {
        return publicationService.update(publication);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        publicationService.delete(id);
    }
    //todo search by text
}