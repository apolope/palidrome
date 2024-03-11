package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import io.smallrye.mutiny.Uni;
import java.util.List;

/**
 * Interface for the TestService that provides palindrome processing functionalities related to the Bradesco challenge.
 * This service includes methods for identifying and extracting palindromes from given matrices.
 */
public interface TestService {

    /**
     * Processes a given matrix to identify and extract palindromes.
     *
     * This method analyzes the provided matrix, searching for sequences that are palindromes.
     * A palindrome is defined as a sequence of characters which reads the same backward as forward.
     * The search is performed across all rows, columns, and potentially diagonals of the matrix,
     * depending on the implementation.
     *
     * @param matrix The MatrixDTO containing the data to be processed.
     *               It should include the matrix dimensions and the content for each cell.
     * @return A Uni that emits a list of PalindromeDTO objects. Each PalindromeDTO represents
     *         a found palindrome within the matrix. The list is empty if no palindromes are found.
     */
    Uni<List<PalindromeDTO>> obtainPalindromes(MatrixDTO matrix);

    /**
     * Retrieves a list of PalindromeDTOs that match a given query string.
     *
     * @param q The query string to search for within palindromes.
     * @return A Uni that emits a list of PalindromeDTOs matching the query.
     */
    Uni<List<PalindromeDTO>> getPalindromes(String q, Long matrixId);
}
