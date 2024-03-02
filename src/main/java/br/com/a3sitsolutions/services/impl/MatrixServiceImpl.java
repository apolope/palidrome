package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.exceptions.SaveException;
import br.com.a3sitsolutions.repositories.MatrixRepository;
import br.com.a3sitsolutions.services.MatrixService;
import br.com.a3sitsolutions.utils.translationsn.Translator;
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

    @ConfigProperty(name = "feature-flag.matrix-length" , defaultValue = "5")
    private Integer MATRIX_LENGTH;

    @Inject
    private MatrixRepository matrixRepository;

    @Override
    public Uni<List<MatrixDTO>> getMatrices() {
        return matrixRepository.listAll().onItem().transform(matrices -> {
            return matrices.stream().map(MatrixDTO::new).collect(Collectors.toList());
        });
    }

    @Override
    public Uni<MatrixDTO> saveOrUpdate(MatrixDTO matrixDTO) throws SaveException {

        if (!checkMatrixLength(matrixDTO)) {
            throw new SaveException(Translator.toLocale("http_messages","length.problem",new Object[]{String.format("%d x %d",MATRIX_LENGTH,MATRIX_LENGTH)}));
        }

        return matrixRepository.persistOrUpdate(matrixDTO.toEntity())
                .onItem().transform(MatrixDTO::new)
                .onFailure().call(Unchecked.supplier(() -> {
                    throw new SaveException();
                }));
    }

    @Override
    public Uni<MatrixDTO> getMatrix(String id) {
        return matrixRepository.findByMatrixId(new ObjectId(id)).onItem().transform(MatrixDTO::new);
    }

    @Override
    public Uni<Boolean> deleteMatrix(String id) {
        return matrixRepository.deleteById(new ObjectId(id));
    }

    @Override
    public Boolean checkMatrixLength(MatrixDTO matrix) {
        if (matrix.getMatrix().size() != MATRIX_LENGTH) {
            return false;
        }

        for (List<Character> row : matrix.getMatrix()) {
            if (row == null || row.size() != MATRIX_LENGTH) {
                return false;
            }
        }

        return true;
    }
}
