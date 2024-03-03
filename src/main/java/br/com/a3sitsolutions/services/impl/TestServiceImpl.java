package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import br.com.a3sitsolutions.services.MatrixService;
import br.com.a3sitsolutions.services.PalindromeService;
import br.com.a3sitsolutions.services.TestService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TestServiceImpl implements TestService {

    @Inject
    MatrixService matrixService;

    @Inject
    PalindromeService palindromeService;

    @Override
    public Uni<List<PalindromeDTO>> obtainPalindromes(MatrixDTO matrixDTO) {
        return matrixService.saveOrUpdate(matrixDTO)
                .onItem().transformToUni(savedMatrixDTO -> {
                    return palindromeService.getPalindromesByMatrixId(savedMatrixDTO.getId().toString());
                });
    }

    @Override
    public Uni<List<PalindromeDTO>> getPalindromes(String q, String matrixId) {
        if (matrixId != null) {
            return palindromeService.getPalindromes(q, matrixId);
        } else {
            return palindromeService.getPalindromes(q);
        }
    }
}
