package br.com.a3sitsolutions.services;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.exceptions.SaveException;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface MatrixService {

    Uni<List<MatrixDTO>> getMatrices();

    Uni<MatrixDTO> saveOrUpdate(MatrixDTO matrixDTO) throws SaveException;

    Uni<MatrixDTO> getMatrix(String id);

    Uni<Boolean> deleteMatrix(String id);

    Boolean checkMatrixLength(MatrixDTO matrix);

    Boolean checkMatrixRowLenght(MatrixDTO matrix);

    Integer minLength();

    Integer maxLength();
}
