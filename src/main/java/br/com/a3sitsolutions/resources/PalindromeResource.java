package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.dtos.PalindromeDTO;
import br.com.a3sitsolutions.services.PalindromeService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
    @Operation(
            summary = "Retrieve Palindromes",
            description = "Retrieves a list of palindromes that match total ou partial content of the given query string. " +
                    "This can be used to search for palindromes by their content."
    )
    @APIResponse(
            responseCode = "200",
            description = "List of matching palindromes",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PalindromeDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Example Response",
                                    summary = "Example list of palindromes matching the query",
                                    value = "[{'id': '5fcb1234', 'palindrome': 'ARARA', 'matrix': '5fcb5678'}]"
                            )
                    }
            )
    )
    public Uni<List<PalindromeDTO>> getPalindromes(
            @Parameter(
                    description = "Query string to search for palindromes. The query can include any part of the palindrome content.",
                    required = false,
                    example = "ARA"
            ) @QueryParam("q") String q
    ) {
        return palindromeService.getPalindromes(q);
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Retrieve Palindromes by Matrix ID",
            description = "Retrieves a list of palindromes associated with a specific matrix ID. " +
                    "This operation returns all palindromes that are linked to the given matrix, " +
                    "allowing for a detailed view of palindromic patterns within the matrix data."
    )
    @APIResponse(
            responseCode = "200",
            description = "List of palindromes associated with the matrix ID",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PalindromeDTO[].class),
                    examples = {
                            @ExampleObject(
                                    name = "Example Palindromes List",
                                    summary = "Example response for palindromes associated with a matrix ID",
                                    value = "[{'id': '5fcb1234', 'palindrome': 'ARARA', 'matrix': '5fcb5678'}]"
                            )
                    }
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Matrix not found for the given ID",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(
                                    name = "Matrix Not Found",
                                    summary = "Response when no matrix is found for the given ID",
                                    value = "{'error': 'The Matrix id 5fcb1234 was not found', 'statusCode': 404}"
                            )
                    }
            )
    )
    public Uni<List<PalindromeDTO>> getPalindromesByMatrixId(
            @Parameter(
                    description = "The unique identifier of the matrix to retrieve associated palindromes.",
                    required = true,
                    example = "5fcb1234"
            ) @PathParam("id") Long id
    ) {
        return palindromeService.getPalindromesByMatrixId(id);
    }

    @POST
    @Operation(
            summary = "Save a new Palindrome",
            description = "Saves a new palindrome to the database. The request body must contain the palindrome data, " +
                    "including the content and any associated metadata. Upon successful saving, the saved palindrome " +
                    "data is returned along with a generated ID."
    )
    @APIResponse(
            responseCode = "200",
            description = "Palindrome successfully saved",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PalindromeDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Saved Palindrome",
                                    summary = "Example of a saved palindrome",
                                    description = "An example response of a successfully saved palindrome.",
                                    value = "{'id': '5fcb1234', 'palindrome': 'ARARA', 'matrix': '5fcb5678'}"
                            )
                    }
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Palindrome Data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = {
                            @ExampleObject(
                                    name = "Invalid Request",
                                    summary = "Response for an invalid palindrome submission",
                                    value = "{'message': 'Object content properties are required matrix and palindrome.', 'statusCode': 400}"
                            )
                    }
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Matrix not found for the given ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = {
                            @ExampleObject(
                                    name = "Invalid Request",
                                    summary = "Response for an invalid id of matrix",
                                    value = "{'message': 'The Matrix id 5fcb1234 was not found', 'statusCode': 404}"
                            )
                    }
            )
    )
    public Uni<PalindromeDTO> savePalindrome(
            @RequestBody(
                    description = "Palindrome object to be saved",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = PalindromeDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Palindrome Submission",
                                            summary = "Example palindrome submission",
                                            description = "An example request body containing palindrome data to be saved.",
                                            value = "{'palindrome': 'ARARA', 'matrix': '5fcb1234'}"
                                    )
                            }
                    )
            ) PalindromeDTO palindrome) {
        return palindromeService.savePalindrome(palindrome);
    }
}
