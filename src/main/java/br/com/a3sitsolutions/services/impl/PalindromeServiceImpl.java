package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.services.MatrixService;
import br.com.a3sitsolutions.services.PalindromeService;
import br.com.a3sitsolutions.utils.Matrix;
import br.com.a3sitsolutions.utils.Messages;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.ArrayList;
import java.util.List;
import static br.com.a3sitsolutions.utils.Palindrome.findPalindromes;

@ApplicationScoped
public class PalindromeServiceImpl implements PalindromeService {

    @ConfigProperty(name = "feature-flag.palindrome-length.min" , defaultValue = "5")
    Integer PALINDROME_MIN_LENGTH;

    @Inject
    MatrixService matrixService;

    @Override
    public Uni<List<String>> getPalindromesByMatrixId(String id) {
        return matrixService.getMatrix(id)
                .onItem().transformToUni(matrix -> {
                    if (matrix != null) {
                        return Uni.createFrom().item(getPalindromesByMatrix(matrix));
                    } else {
                        return Uni.createFrom().failure(new NotFoundException(Messages.NOT_FOUND_ID, id));
                    }
                })
                .onFailure().recoverWithUni(failure -> Uni.createFrom().failure(new NotFoundException(Messages.NOT_FOUND_ID, id)));
    }

    @Override
    public List<String> getPalindromesByMatrix(MatrixDTO matrix) {
        List<String> palindromes = new ArrayList<>();

        if (Matrix.isValideMatrix(matrix, matrixService.minLength(), matrixService.maxLength())) {
            palindromes = findPalindromes(matrix, PALINDROME_MIN_LENGTH);
        }

        return palindromes;
    }
}
