package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.services.PalindromeService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

@Path("/palindrome")
@Tag(name = "Palindrome")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PalindromeResource {

    @Inject
    PalindromeService palindromeService;

    @GET
    @Path("/{id}")
    public Uni<List<String>> getPalindromesByMatrixId(@PathParam("id") String id) {
        return palindromeService.getPalindromesByMatrixId(id);
    }
}
