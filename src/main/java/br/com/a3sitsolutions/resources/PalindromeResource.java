package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.dtos.PalindromeDTO;
import br.com.a3sitsolutions.services.PalindromeService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
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
    public Uni<List<PalindromeDTO>> getPalidromes(
            @Parameter(description = "Query") @QueryParam("q") String q
    ) {
        return palindromeService.getPalindromes(q);
    }

    @GET
    @Path("/{id}")
    public Uni<List<PalindromeDTO>> getPalindromesByMatrixId(@PathParam("id") String id) {
        return palindromeService.getPalindromesByMatrixId(id);
    }

    @POST
    public Uni<PalindromeDTO> savePalindrome(PalindromeDTO palindrome) {
        return palindromeService.savePalindrome(palindrome);
    }
}
