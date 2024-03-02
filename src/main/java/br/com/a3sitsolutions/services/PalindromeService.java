package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import io.smallrye.mutiny.Uni;
import java.util.List;

public interface PalindromeService {

    Uni<List<String>> getPalindromesByMatrixId(String id);

    List<String> getPalindromesByMatrix(MatrixDTO matrix);
}
