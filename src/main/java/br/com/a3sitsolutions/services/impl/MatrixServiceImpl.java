package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.exceptions.DeleteException;
import br.com.a3sitsolutions.exceptions.IdConverterExeception;
import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.exceptions.SaveException;
import br.com.a3sitsolutions.models.Matrix;
import br.com.a3sitsolutions.repositories.MatrixRepository;
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

@ApplicationScoped
public class MatrixServiceImpl implements MatrixService {

    @ConfigProperty(name = "feature-flag.matrix-length.min" , defaultValue = "5")
    Integer MATRIX_MIN_LENGTH;

    @ConfigProperty(name = "feature-flag.matrix-length.max" , defaultValue = "5")
    Integer MATRIX_MAX_LENGTH;

    @Inject
    MatrixRepository matrixRepository;

    @Inject
    PalindromeService palindromeService;

    @Override
    public Uni<List<MatrixDTO>> getMatrices() {
        return matrixRepository.listAll().onItem().transform(matrices -> {
            return matrices.stream().map(MatrixDTO::new).collect(Collectors.toList());
        });
    }

    @Override
    public Uni<MatrixDTO> saveOrUpdate(MatrixDTO matrixDTO) {
        if (!checkMatrixLength(matrixDTO) || !checkMatrixRowLenght(matrixDTO)) {
            return Uni.createFrom().failure(new SaveException(MessagesUtil.formatLengthProblemMessage(MATRIX_MIN_LENGTH, MATRIX_MAX_LENGTH)));
        }

        // Assume que MatrixDTO.toEntity() está correto
        Matrix matrix = matrixDTO.toEntity();

        return matrixRepository.persistOrUpdate(matrix)
                .onItem().transformToUni(savedMatrix -> {
                    matrixDTO.setId(savedMatrix.getId());
                    return palindromeService.savePalindromes(matrixDTO)
                            .onItem().transform(ignored -> new MatrixDTO(savedMatrix)); // Transforma de volta para MatrixDTO
                })
                .onFailure().recoverWithUni(failure -> Uni.createFrom().failure(new SaveException()));
    }

    @Override
    public Uni<MatrixDTO> getMatrix(String id) {

        ObjectId oId;

        try {
            oId = new ObjectId(id);
        } catch (Exception e) {
            throw new IdConverterExeception(MessagesUtil.ID_CONVERTER_PROBLEM, id);
        }

        Uni<MatrixDTO> matrix = matrixRepository.findByMatrixId(oId)
                .onItem().ifNotNull().transform(MatrixDTO::new)
                .onFailure().transform(e -> new NotFoundException(MessagesUtil.NOT_FOUND_ID, id));

        return matrix;
    }

    @Override
    public Uni<Boolean> deleteMatrix(String id) {
        ObjectId matrixId;
        try {
            matrixId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return Uni.createFrom().failure(new IdConverterExeception(MessagesUtil.ID_CONVERTER_PROBLEM, id));
        }

        return matrixRepository.deleteById(matrixId)
                .onItem().transformToUni(deleted -> {
                    if (Boolean.TRUE.equals(deleted)) {
                        return palindromeService.deleteByMatrixId(matrixId)
                                .onItem().transform(deletedPalindromes -> Boolean.TRUE)
                                .onFailure().recoverWithItem(Boolean.FALSE);
                    } else {
                        return Uni.createFrom().item(Boolean.FALSE);
                    }
                })
                .onFailure().recoverWithUni(failure ->
                        Uni.createFrom().failure(new DeleteException(MessagesUtil.MATRIX_DELETE_PROBLEM, id))
                );
    }

    @Override
    public Boolean checkMatrixLength(MatrixDTO matrix) {
        return MatrixUtil.checkMatrixLength(matrix, MATRIX_MIN_LENGTH, MATRIX_MAX_LENGTH);
    }

    @Override
    public Boolean checkMatrixRowLenght(MatrixDTO matrix) {
        return MatrixUtil.checkMatrixRowLenght(matrix);
    }

    /**
     * @return
     */
    @Override
    public Integer minLength() {
        return MATRIX_MIN_LENGTH;
    }

    /**
     * @return
     */
    @Override
    public Integer maxLength() {
        return MATRIX_MAX_LENGTH;
    }
}
