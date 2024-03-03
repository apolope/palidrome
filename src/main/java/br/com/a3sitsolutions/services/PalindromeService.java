package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import java.util.List;

public interface PalindromeService {

    Uni<List<PalindromeDTO>> getPalindromes(String q);

    Uni<List<PalindromeDTO>> getPalindromesByMatrixId(String id);

    Uni<List<String>> getPalindromesByMatrix(MatrixDTO matrix);

    Uni<PalindromeDTO> savePalindrome(PalindromeDTO palindrome);

    Uni<Void> savePalindromes(MatrixDTO matrix);

    Uni<Boolean> deleteByMatrixId(ObjectId matrixId);
}
