package br.com.fiap.domain.resource;
import br.com.fiap.domain.entity.Filho;
import br.com.fiap.domain.service.FilhoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/filho")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public class FilhoResource implements Resource<Filho, Long> {

    @Context
    UriInfo uriInfo;
    FilhoService service = new FilhoService();

    @Override
    public Response persist(Filho filho) {
        filho.setId(null);
        Filho persit = service.persist(filho);
        if (Objects.isNull(persit)) return Response.status(404).build();
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();
        return Response.created(uri).entity(persit).build();
    }

    @Override
    public Response findAll() {
        List<Filho> all = service.findAll();
        return Response.ok(all).build();
    }

    @Override
    public Response findById(@PathParam("id") Long id) {
        Filho filho = service.findById(id);
        if (Objects.isNull(filho)) return Response.status(404).build();
        return Response.ok(filho).build();
    }
}
