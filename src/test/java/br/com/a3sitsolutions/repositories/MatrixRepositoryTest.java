package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.Utils.Factory;
import br.com.a3sitsolutions.models.Matrix;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MatrixRepositoryTest {

    @Inject
    MatrixRepository matrixRepository;

    @Inject
    Factory factory;

    @Test
    void findByMatrixId() {
        Matrix matrix = factory.matrixFactory().await().indefinitely();
        assertNotNull(matrix);

        Uni<Matrix> resultUni = matrixRepository.findByMatrixId(matrix.getId());

        Matrix resultMatrix = resultUni.await().indefinitely();

        assertNotNull(resultMatrix);
        assertEquals(matrix.getId(), resultMatrix.getId());
    }
}

