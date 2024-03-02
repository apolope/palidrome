package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.exceptions.IdConverterExeception;
import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.exceptions.SaveException;
import br.com.a3sitsolutions.repositories.MatrixRepository;
import br.com.a3sitsolutions.services.MatrixService;
import br.com.a3sitsolutions.utils.Matrix;
import br.com.a3sitsolutions.utils.Messages;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
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

    @Override
    public Uni<List<MatrixDTO>> getMatrices() {
        return matrixRepository.listAll().onItem().transform(matrices -> {
            return matrices.stream().map(MatrixDTO::new).collect(Collectors.toList());
        });
    }

    @Override
    public Uni<MatrixDTO> saveOrUpdate(MatrixDTO matrixDTO) throws SaveException {

        if (!checkMatrixLength(matrixDTO)) {
            throw new SaveException(Messages.formatLengthProblemMessage(MATRIX_MIN_LENGTH,MATRIX_MAX_LENGTH));
        }

        if (!checkMatrixRowLenght(matrixDTO)) {
            throw new SaveException(Messages.formatRowLengthProblemMessage(matrixDTO.getMatrix().get(0).size()));
        }

        return matrixRepository.persistOrUpdate(matrixDTO.toEntity())
                .onItem().transform(MatrixDTO::new)
                .onFailure().call(Unchecked.supplier(() -> {
                    throw new SaveException();
                }));
    }

    @Override
    public Uni<MatrixDTO> getMatrix(String id) {

        ObjectId oId;

        try {
            oId = new ObjectId(id);
        } catch (Exception e) {
            throw new IdConverterExeception(Messages.ID_CONVERTER_PROBLEM, id);
        }

        Uni<MatrixDTO> matrix = matrixRepository.findByMatrixId(oId)
                .onItem().ifNotNull().transform(MatrixDTO::new)
                .onFailure().transform(e -> new NotFoundException(Messages.NOT_FOUND_ID, id));

        return matrix;
    }

    @Override
    public Uni<Boolean> deleteMatrix(String id) {
        return matrixRepository.deleteById(new ObjectId(id));
    }

    @Override
    public Boolean checkMatrixLength(MatrixDTO matrix) {
        return Matrix.checkMatrixLength(matrix, MATRIX_MIN_LENGTH, MATRIX_MAX_LENGTH);
    }

    @Override
    public Boolean checkMatrixRowLenght(MatrixDTO matrix) {
        return Matrix.checkMatrixRowLenght(matrix);
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
