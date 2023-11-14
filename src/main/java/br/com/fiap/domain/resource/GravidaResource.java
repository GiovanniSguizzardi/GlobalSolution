package br.com.fiap.domain.resource;
import br.com.fiap.domain.entity.Gravida;
import br.com.fiap.domain.service.GravidaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/gravida")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GravidaResource implements Resource<Gravida, Long> {

    @Context
    UriInfo uriInfo;
    GravidaService service = new GravidaService();

    @POST
    @Override
    public Response persist(Gravida gravida) {
        gravida.setId(null);
        Gravida persit = service.persist(gravida);
        if (Objects.isNull(persit)) return Response.status(404).build();
        UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
        URI uri = uriBuilder.path(String.valueOf(persit.getId())).build();
        return Response.created(uri).entity(persit).build();
    }

    @GET
    @Override
    public Response findAll() {
        List<Gravida> all = service.findAll();
        return Response.ok(all).build();
    }

    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {
        Gravida gravida = service.findById(id);
        if (Objects.isNull(gravida)) return Response.status(404).build();
        return Response.ok(gravida).build();
    }
}