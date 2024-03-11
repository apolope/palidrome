package br.com.a3sitsolutions.services.impl;

import br.com.a3sitsolutions.utils.Factory;
import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.exceptions.SaveException;
import br.com.a3sitsolutions.models.Matrix;
import br.com.a3sitsolutions.repositories.MatrixRepository;
import br.com.a3sitsolutions.services.MatrixService;
import br.com.a3sitsolutions.services.PalindromeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MatrixServiceImplTest {

    @InjectMock
    MatrixRepository matrixRepository;

    @InjectMock
    MatrixService matrixService;

    @InjectMock
    PalindromeService palindromeService;

    @Inject
    Factory factory;

    @Test
    void getMatrices() {
        List<MatrixDTO> matrices = List.of(factory.matrixFactoryDTO());

        Mockito.when(matrixService.getMatrices()).thenReturn(Uni.createFrom().item(matrices));

        matrixService.getMatrices().subscribe().with(retrievedMatrices -> {
            assertEquals(matrices.size(), retrievedMatrices.size());
        });
    }

    @Test
    void saveOrUpdateSuccess() {
        MatrixDTO matrixDTO = factory.matrixFactoryDTO();

        Mockito.when(matrixService.saveOrUpdate(any(MatrixDTO.class))).thenReturn(Uni.createFrom().item(matrixDTO));

        matrixService.saveOrUpdate(matrixDTO).subscribe().with(Assertions::assertNotNull);
    }

    @Test
    void saveOrUpdateFailure() {
        MatrixDTO matrixDTO = factory.matrixFactoryDTO();

        matrixService.saveOrUpdate(matrixDTO).subscribe().with(
                result -> fail(),
                failure -> assertInstanceOf(SaveException.class, failure)
        );
    }

    @Test
    void getMatrixSuccess() {
        Matrix matrix = factory.matrixFactoryEntity();
        Mockito.when(matrixService.getMatrix(anyLong())).thenReturn(Uni.createFrom().item(matrix.of()));

        matrixService.getMatrix(1L).subscribe().with(matrixDTO -> {
            assertNotNull(matrixDTO);
            assertEquals(1L, matrixDTO.getId());
            assertEquals(matrix.getMatrix(), matrixDTO.getMatrix());
        });
    }

    @Test
    void getMatrixFail() {
        Mockito.when(matrixService.getMatrix(anyLong())).thenReturn(Uni.createFrom().nullItem());

        matrixService.getMatrix(1L).subscribe().with(
                matrixDTO -> fail(),
                failure -> assertInstanceOf(NotFoundException.class, failure)
        );
    }

    @Test
    void deleteMatrixSuccess() {
        Mockito.when(matrixService.deleteMatrix(anyLong())).thenReturn(Uni.createFrom().item(true));

        matrixService.deleteMatrix(1L).subscribe().with(Assertions::assertTrue);

        Mockito.verify(matrixService, Mockito.times(1)).deleteMatrix(1L);
    }

    @Test
    void deleteMatrixFail() {
        Mockito.when(matrixService.deleteMatrix(1L)).thenReturn(Uni.createFrom().item(false));

        matrixService.deleteMatrix(1L).subscribe().with(Assertions::assertFalse, failure -> {
            assertInstanceOf(NotFoundException.class, failure);
        });

        Mockito.verify(matrixService, Mockito.times(1)).deleteMatrix(1L);
    }

    @Test
    void checkMatrixLengthSuccess() {
        MatrixDTO matrixDTO = new MatrixDTO();
        matrixDTO.setMatrix(factory.matrixDataFactoryValid());

        Mockito.when(matrixService.checkMatrixLength(any(MatrixDTO.class))).thenReturn(true);

        assertTrue(matrixService.checkMatrixLength(matrixDTO));
    }

    @Test
    void checkMatrixLengthFail() {
        MatrixDTO matrixDTO = new MatrixDTO();
        matrixDTO.setMatrix(factory.matrixDataFactoryInvalid());

        Mockito.when(matrixService.checkMatrixLength(any(MatrixDTO.class))).thenReturn(false);

        assertFalse(matrixService.checkMatrixLength(matrixDTO));
    }

    @Test
    void checkMatrixRowLenghtSuccess() {
        List<List<Character>> matrix = factory.matrixDataFactoryValid();

        MatrixDTO matrixDTO = new MatrixDTO();
        matrixDTO.setMatrix(matrix);

        Mockito.when(matrixService.checkMatrixRowLenght(any(MatrixDTO.class))).thenReturn(true);

        assertTrue(matrixService.checkMatrixRowLenght(matrixDTO));
    }

    @Test
    void checkMatrixRowLenghtFail() {
        List<List<Character>> matrix = factory.matrixDataFactoryValid();

        MatrixDTO matrixDTO = new MatrixDTO();
        matrixDTO.setMatrix(matrix);

        Mockito.when(matrixService.checkMatrixRowLenght(any(MatrixDTO.class))).thenReturn(false);

        assertFalse(matrixService.checkMatrixRowLenght(matrixDTO));
    }
}