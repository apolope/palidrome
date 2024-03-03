package br.com.a3sitsolutions.resources;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.services.MatrixService;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
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
    @Operation(
            summary = "Retrieve all Matrices",
            description = "Retrieves a list of all matrices stored in the database, including their IDs, data, and any other relevant information. This endpoint is useful for obtaining a comprehensive overview of all matrices currently available."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Successful retrieval of matrices list.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatrixDTO[].class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example Matrices List",
                                            summary = "Example list of matrices",
                                            description = "An example of a response containing a list of matrices.",
                                            value = "[{'id': '5fcb1234', 'data': [['X', 'O'], ['O', 'X']]}, {'id': '5fcb5678', 'data': [['A', 'B'], ['C', 'D']]}]"
                                    )
                            }
                    )
            ),
            @APIResponse(
                    responseCode = "500",
                    description = "Internal server error when trying to retrieve matrices.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Internal Server Error Response",
                                            summary = "Example response for an internal server error",
                                            value = "{ 'message': 'An unexpected error occurred while retrieving matrices', 'statusCode': 400 "
                                    )
                            }
                    )
            )
    })
    public Uni<List<MatrixDTO>> getMatrices() {
        return service.getMatrices();
    }

    @POST
    @Operation(
            summary = "Save or Update a Matrix",
            description = "Saves a new matrix to the database or updates an existing one if the matrix ID is provided. The endpoint expects a matrix object, including data and, optionally, an ID. If an ID is provided and exists in the database, the corresponding matrix is updated. Otherwise, a new matrix is created."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Matrix successfully saved or updated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatrixDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Saved Matrix",
                                            summary = "Example of a saved or updated matrix",
                                            description = "An example response of a successfully saved or updated matrix.",
                                            value = "{'id': '5fcb1234', 'data': [['X', 'O'], ['O', 'X']]}")
                            }
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Request - the provided matrix object is invalid or missing required fields.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Invalid Request Response",
                                            summary = "Example response for an invalid request",
                                            value = "{ 'message': 'The matrix must be between 5x5 and 10x10', 'statusCode': 400 "
                                    )
                            }
                    )
            )
    })
    public Uni<MatrixDTO> save(
            @NotNull MatrixDTO matrixDTO) {
        return service.saveOrUpdate(matrixDTO);
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Retrieve a Matrix by its ID",
            description = "Retrieves the details of a specific matrix identified by its unique ID. This includes the matrix data and any other relevant information. If the specified ID does not correspond to an existing matrix, a 404 error is returned."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the matrix.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatrixDTO.class),
                            examples = @ExampleObject(
                                    name = "Example Matrix",
                                    summary = "Example response for a single matrix",
                                    value = "{'id': '5fcb1234', 'data': [['X', 'O'], ['O', 'X']]}"
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Matrix ID - the provided ID is not in a valid format.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Invalid ID Response",
                                    summary = "Response for an invalid ID format",
                                    value = "{'error': 'Problem converting id 5fcb1234', 'statusCode': 400 }"
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Matrix Not Found - no matrix was found corresponding to the provided ID.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Matrix Not Found Response",
                                    summary = "Response when no matrix is found for the given ID",
                                    value = "{'error': 'The Matrix id 5fcb1234 was not found', 'statusCode': 404}"
                            )
                    )
            )
    })
    public Uni<MatrixDTO> getMatrix(
            @Parameter(
                    description = "The unique identifier of the matrix to be retrieved.",
                    required = true,
                    example = "5fcb1234"
            ) @PathParam("id") String id) {
        return service.getMatrix(id);
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update a Matrix by ID",
            description = "Updates the details of an existing matrix identified by its unique ID. This operation replaces the existing matrix data with the new data provided in the request. If the specified ID does not exist, a 404 error is returned. Ensure the request body contains the updated matrix data."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Matrix successfully updated.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatrixDTO.class),
                            examples = @ExampleObject(
                                    name = "Updated Matrix",
                                    summary = "Example response for an updated matrix",
                                    description = "An example of a matrix after being updated.",
                                    value = "{ 'id': '5fcb1234', 'data': [['A', 'B'], ['C', 'D']]}")
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Request - The request is malformed or the matrix data is invalid.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Invalid Matrix Example",
                                    summary = "Response for an invalid or malformed request",
                                    value = "{'error': 'Problem converting id 5fcb1234', 'statusCode': 400 }"
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Matrix Not Found - No matrix found with the provided ID.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Matrix Not Found Example",
                                    summary = "Response when no matrix is found for the ID",
                                    value = "{'error': 'The Matrix id 5fcb1234 was not found', 'statusCode': 404}"
                            )
                    )
            )
    })
    public Uni<MatrixDTO> updateMatrix(
            @Parameter(
                    description = "The unique identifier of the matrix to be updated.",
                    required = true,
                    example = "5fcb1234"
            ) @PathParam("id") String id,
            @Parameter(
                    description = "The new matrix content to update. Ensure to provide the complete matrix data.",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = MatrixDTO.class),
                            examples = @ExampleObject(
                                    name = "Matrix Update Example",
                                    summary = "Example request for updating a matrix",
                                    description = "An example request body containing new matrix data.",
                                    value = "{ 'data': [['A', 'B'], ['C', 'D']]}")
                    )
            ) @NotNull MatrixDTO matrixDTO) {
        matrixDTO.setId(new ObjectId(id));
        return service.saveOrUpdate(matrixDTO);
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete a Matrix by ID",
            description = "Deletes the specified matrix from the database based on its unique identifier. " +
                    "This operation is irreversible. If the matrix is successfully deleted, " +
                    "a boolean value of true is returned. If no matrix with the given ID exists, " +
                    "the operation will return a boolean value of false, and a 404 status code is issued."
    )
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Matrix successfully deleted.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Boolean.class),
                            examples = @ExampleObject(
                                    name = "Matrix Deleted",
                                    summary = "Successful Deletion",
                                    value = "true"
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "400",
                    description = "Invalid Request - The provided ID is not in a valid format.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Invalid ID Format",
                                    summary = "Response for an Invalid ID Format",
                                    value = "{'error': 'Problem converting id 5fcb1234', 'statusCode': 400 }"
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Matrix Not Found - No matrix found with the provided ID.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Matrix Not Found",
                                    summary = "Response when no matrix is found for the ID",
                                    value = "{'error': 'The Matrix id 5fcb1234 was not found', 'statusCode': 404}"
                            )
                    )
            ),
            @APIResponse(
                    responseCode = "500",
                    description = "Internal Server Error - Problem occurred while trying to delete the matrix.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Delete Matrix Problem",
                                    summary = "Internal Server Error Response",
                                    value = "{'error': 'The matrix id 5fcb1234 could not be deleted', 'statusCode': 500}"
                            )
                    )
            )
    })
    public Uni<Boolean> deleteMatrix(
            @Parameter(
                    description = "The unique identifier of the matrix to be deleted.",
                    required = true,
                    example = "5fcb1234"
            ) @PathParam("id") String id) {
        return service.deleteMatrix(id);
    }

}
