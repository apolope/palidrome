package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import io.smallrye.mutiny.Uni;
import java.util.List;

/**
 * Service interface for managing matrices operations.
 * Provides methods for retrieving, saving/updating, and deleting matrices,
 * as well as validation checks for matrix dimensions.
 */
public interface MatrixService {

    /**
     * Retrieves all matrices stored in the database.
     *
     * @return A Uni that, when subscribed, emits a list of MatrixDTO objects representing all matrices.
     */
    Uni<List<MatrixDTO>> getMatrices();

    /**
     * Saves a new matrix or updates an existing one based on the presence of an ID in the given MatrixDTO.
     * If the MatrixDTO contains an ID that matches an existing matrix, that matrix is updated.
     * Otherwise, a new matrix is created and saved.
     *
     * @param matrixDTO The MatrixDTO to be saved or updated.
     * @return A Uni that emits the saved or updated MatrixDTO.
     * @throws SaveException if there is an issue with saving or updating the matrix in the database.
     */
    Uni<MatrixDTO> saveOrUpdate(MatrixDTO matrixDTO);

    /**
     * Retrieves a matrix by its unique identifier.
     *
     * @param id The unique ID of the matrix to retrieve.
     * @return A Uni that emits the MatrixDTO representing the matrix with the specified ID,
     *         or fail with exception.
     */
    Uni<MatrixDTO> getMatrix(String id);

    /**
     * Deletes a matrix by its unique identifier.
     *
     * @param id The unique ID of the matrix to be deleted.
     * @return A Uni<Boolean> that emits true if the matrix was successfully deleted, or fail with exception.
     */
    Uni<Boolean> deleteMatrix(String id);

    /**
     * Validates the overall dimensions of the matrix against predefined minimum and maximum lengths.
     *
     * @param matrix The MatrixDTO to validate.
     * @return true if the matrix dimensions are within the valid range, false otherwise.
     */
    Boolean checkMatrixLength(MatrixDTO matrix);

    /**
     * Validates that all rows in the matrix have the same length, ensuring the matrix is properly formatted.
     *
     * @param matrix The MatrixDTO to check for consistent row lengths.
     * @return true if all rows in the matrix have the same length, false otherwise.
     */
    Boolean checkMatrixRowLenght(MatrixDTO matrix);

    /**
     * Retrieves the minimum valid length for a matrix.
     *
     * @return The minimum number of rows and columns a valid matrix must have.
     */
    Integer minLength();

    /**
     * Retrieves the maximum valid length for a matrix.
     *
     * @return The maximum number of rows and columns a valid matrix can have.
     */
    Integer maxLength();
}
