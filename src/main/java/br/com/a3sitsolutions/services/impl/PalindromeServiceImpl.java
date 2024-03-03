package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.dtos.PalindromeDTO;
import br.com.a3sitsolutions.exceptions.DeleteException;
import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.exceptions.SaveException;
import br.com.a3sitsolutions.models.Palindrome;
import br.com.a3sitsolutions.repositories.PalindromeRepository;
import br.com.a3sitsolutions.services.MatrixService;
import br.com.a3sitsolutions.services.PalindromeService;
import br.com.a3sitsolutions.utils.MatrixUtil;
import br.com.a3sitsolutions.utils.MessagesUtil;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.List;
import java.util.stream.Collectors;
import static br.com.a3sitsolutions.utils.PalindromeUtil.findPalindromes;

@ApplicationScoped
public class PalindromeServiceImpl implements PalindromeService {

    @ConfigProperty(name = "feature-flag.palindrome-length.min" , defaultValue = "5")
    Integer PALINDROME_MIN_LENGTH;

    @Inject
    PalindromeRepository repo;

    @Inject
    MatrixService matrixService;

    @Override
    public Uni<List<PalindromeDTO>> getPalindromes(String q) {
        if (q != null) {
            return repo.findPalindromesByQuery(q).onItem().transform(palindromes -> {
                return palindromes.stream().map(PalindromeDTO::new).collect(Collectors.toList());
            });
        } else {
            return repo.listAll()
                    .onItem().transform(palindromes -> palindromes.stream().map(PalindromeDTO::new).collect(Collectors.toList()));
        }
    }

    @Override
    public Uni<List<PalindromeDTO>> getPalindromes(String q, String matrixId) {
        return repo.findPalindromesByQueryAndMatrixId(q, matrixId)
                .onItem().ifNull().fail()
                .onItem().ifNotNull().transform(palindromes -> palindromes.stream().map(PalindromeDTO::new).collect(Collectors.toList()))
                .onFailure().transform(e -> new NotFoundException(MessagesUtil.NOT_FOUND_ID, matrixId));
    }

    @Override
    public Uni<List<PalindromeDTO>> getPalindromesByMatrixId(String id) {
        return matrixService.getMatrix(id)
                .onItem().transformToUni(matrixDTO -> {
                    if (matrixDTO == null || matrixDTO.getId() == null) {
                        return Uni.createFrom().failure(new NotFoundException(MessagesUtil.NOT_FOUND_ID, id));
                    }
                    return repo.findPalindromeByMatrixId(id)
                            .onItem().transform(palindromes -> palindromes.stream().map(PalindromeDTO::new).collect(Collectors.toList()));
                });
    }

    @Override
    public Uni<PalindromeDTO> savePalindrome(PalindromeDTO palindrome) {
        if (palindrome.getMatrix() == null || palindrome.getPalindrome().isEmpty()) {
            return Uni.createFrom().failure(new SaveException(MessagesUtil.EMPTY_PALINDROME_CONTENT));
        }

        return matrixService.getMatrix(palindrome.getMatrix().toString())
                .onItem().ifNull().fail()
                .onItem().transformToUni(matrixDTO -> {
                    if (matrixDTO != null && matrixDTO.getId() != null) {
                        return repo.saveOrUpdateByMatrixId(palindrome.toEntity())
                                .onItem().transform(PalindromeDTO::new)
                                .onFailure().transform(e -> new SaveException());
                    } else {
                        return Uni.createFrom().failure(new NotFoundException(MessagesUtil.NOT_FOUND_ID, palindrome.getMatrix().toString()));
                    }
                })
                .onFailure().transform(e -> new NotFoundException(MessagesUtil.NOT_FOUND_ID, palindrome.getMatrix().toString()));
    }

    @Override
    public Uni<Void> savePalindromes(MatrixDTO matrixDTO) {
        if (!MatrixUtil.checkMatrixLength(matrixDTO, matrixService.minLength(), matrixService.maxLength())) {
            return Uni.createFrom().failure(new SaveException(MessagesUtil.formatLengthProblemMessage(matrixService.minLength(), matrixService.maxLength())));
        }

        if (matrixDTO.getMatrix() != null && !MatrixUtil.checkMatrixRowLenght(matrixDTO)) {
            return Uni.createFrom().failure(new SaveException(MessagesUtil.formatRowLengthProblemMessage(matrixDTO.getMatrix().get(0).size())));
        }

        List<Uni<Void>> saveOperations = findPalindromes(matrixDTO, PALINDROME_MIN_LENGTH).stream()
                .map(word -> {
                    Palindrome palindrome = new Palindrome();
                    palindrome.setPalindrome(word);
                    palindrome.setMatrix(matrixDTO.getId());
                    return repo.persistOrUpdate(palindrome).replaceWithVoid();
                })
                .collect(Collectors.toList());

        return Uni.combine().all().unis(saveOperations).discardItems();
    }

    @Override
    public Uni<Boolean> deleteByMatrixId(ObjectId matrixId) {
        return repo.delete("matrix", matrixId)
                .onItem().transform(deletedCount -> deletedCount > 0)
                .onFailure().recoverWithItem(Boolean.FALSE)
                .onFailure().recoverWithUni(failure ->
                        Uni.createFrom().failure(new DeleteException(MessagesUtil.PALINDROME_DELETE_PROBLEM, matrixId.toHexString())));
    }
}
