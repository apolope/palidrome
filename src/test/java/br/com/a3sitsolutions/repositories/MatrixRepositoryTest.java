package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.utils.Factory;
import br.com.a3sitsolutions.models.Matrix;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MatrixRepositoryTest {

    @InjectMock
    MatrixRepository matrixRepository;

    @Inject
    Factory factory;

    @Test
    void findByMatrixId() {
        Matrix matrix = factory.matrixUniFactory().await().indefinitely();
        assertNotNull(matrix);

        Mockito.when(matrixRepository.findByMatrixId(matrix.getId()))
                .thenAnswer(invocation -> Uni.createFrom().item(matrix));

        Uni<Matrix> resultUni = matrixRepository.findByMatrixId(matrix.getId());

        Matrix resultMatrix = resultUni.await().indefinitely();

        assertNotNull(resultMatrix);
        assertEquals(matrix.getId(), resultMatrix.getId());
    }
}

