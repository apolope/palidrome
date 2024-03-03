package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import io.smallrye.mutiny.Uni;
import java.util.List;

public interface TestService {
    Uni<List<PalindromeDTO>> obtainPalindromes(MatrixDTO matrix);
}
