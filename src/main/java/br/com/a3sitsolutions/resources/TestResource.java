package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import br.com.a3sitsolutions.services.TestService;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
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

@Path("/test")
@Tag(name = "Test")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @Inject
    TestService testService;

    @POST
    @Operation(
            summary = "Obtain Palindromes from Matrix",
            description = "Processes the provided matrix to find and return any palindromes contained within. " +
                    "The matrix data should be provided in the request body. " +
                    "This operation returns a list of PalindromeDTO objects representing the palindromes found."
    )
    @APIResponse(
            responseCode = "200",
            description = "Successfully processed the matrix and found palindromes",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PalindromeDTO[].class),
                    examples = {
                            @ExampleObject(
                                    name = "Palindrome List Example",
                                    summary = "Example of returned palindromes from a matrix",
                                    value = "[{'id': '5fcb1234', 'palindrome': 'ARARA', 'matrixId': '5fcb5678'}]"
                            )
                    }
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Matrix row length Data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = {
                            @ExampleObject(
                                    name = "Invalid Matrix rows",
                                    summary = "Response for invalid matrix rows data",
                                    value = "{'message': 'All rows of the matrix must have 4', 'statusCode': 400}"
                            )
                    }
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid Matrix row length Data",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    examples = {
                            @ExampleObject(
                                    name = "Invalid Matrix length",
                                    summary = "Response for invalid matrix length data",
                                    value = "{'message': 'The matrix must be between 5x5 and 10x10', 'statusCode': 400}"
                            )
                    }
            )
    )
    public Uni<List<PalindromeDTO>> obtainPalindrome(
            @RequestBody(
                    description = "Matrix object containing the data to be processed for palindromes",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = MatrixDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Matrix Submission Example",
                                            summary = "Example matrix submission for palindrome processing",
                                            value = "{'matrix': [['a', 'b', 'a'], ['m', 'a', 'd'], ['r', 'a', 'c']]}"
                                    )
                            }
                    )
            ) @NotNull MatrixDTO matrix
    ) {
        return testService.obtainPalindromes(matrix);
    }

    @GET
    @RolesAllowed("admin")
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
            ) @QueryParam("q") String q,
            @Parameter(
                    description = "The unique identifier of the matrix to retrieve associated palindromes.",
                    required = false,
                    example = "5fcb1234"
            ) @QueryParam("matrixId") Long matrixId
    ) {
        return testService.getPalindromes(q, matrixId);
    }
}
