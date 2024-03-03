package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import java.util.List;

/**
 * Interface for managing palindrome-related operations.
 * Provides methods for retrieving palindromes, saving new palindromes, and deleting palindromes associated with a specific matrix.
 */
public interface PalindromeService {

    /**
     * Retrieves a list of PalindromeDTOs that match a given query string.
     *
     * @param q The query string to search for within palindromes.
     * @return A Uni that emits a list of PalindromeDTOs matching the query.
     */
    Uni<List<PalindromeDTO>> getPalindromes(String q);

    /**
     * Retrieves a list of PalindromeDTOs that match a given query string and matrix id.
     *
     * @param q The query string to search for within palindromes.
     * @param matrixId The id of the matrix to retrieve associated palindromes.
     * @return A Uni that emits a list of PalindromeDTOs matching the query and matrix id.
     */
    Uni<List<PalindromeDTO>> getPalindromes(String q, String matrixId);

    /**
     * Retrieves all PalindromeDTOs associated with a specific matrix ID.
     *
     * @param id The unique identifier of the matrix to retrieve associated palindromes.
     * @return A Uni that emits a list of PalindromeDTOs associated with the specified matrix ID.
     */
    Uni<List<PalindromeDTO>> getPalindromesByMatrixId(String id);

    /**
     * Saves a new palindrome.
     *
     * @param palindrome The PalindromeDTO to be saved.
     * @return A Uni that emits the saved PalindromeDTO.
     */
    Uni<PalindromeDTO> savePalindrome(PalindromeDTO palindrome);

    /**
     * Saves all palindromes found within the data of a given MatrixDTO.
     *
     * @param matrix The MatrixDTO to search for palindromes within its data and save them.
     * @return A Uni representing the completion of the save operation.
     */
    Uni<Void> savePalindromes(MatrixDTO matrix);

    /**
     * Deletes all palindromes associated with a specific matrix ID.
     *
     * @param matrixId The ObjectId of the matrix for which associated palindromes should be deleted.
     * @return A Uni that emits true if the deletion was successful, or fail with exception.
     */
    Uni<Boolean> deleteByMatrixId(ObjectId matrixId);
}
