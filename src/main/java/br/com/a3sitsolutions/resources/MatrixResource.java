package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.services.MatrixService;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/matrix")
@Tag(name = "Matrix")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MatrixResource {

    @Inject
    MatrixService service;

    @GET
    public Uni<List<MatrixDTO>> getMatrices() {
        return service.getMatrices();
    }

    @POST
    public Uni<MatrixDTO> save(
            @NotNull MatrixDTO matrixDTO) {
        return service.saveOrUpdate(matrixDTO);
    }

    @GET
    @Path("/{id}")
    public Uni<MatrixDTO> getMatrix(
            @PathParam("id") String id) {
        return service.getMatrix(id);
    }

    @PUT
    @Path("/{id}")
    public Uni<MatrixDTO> updateMatrix(
            @PathParam("id") String id,
            @NotNull MatrixDTO matrixDTO) {
        matrixDTO.setId(new ObjectId(id));
        return service.saveOrUpdate(matrixDTO);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> deleteMatrix(
            @PathParam("id") String id) {
        return service.deleteMatrix(id);
    }
}
