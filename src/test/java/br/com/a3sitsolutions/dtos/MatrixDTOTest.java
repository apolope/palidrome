package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.utils.Factory;
import br.com.a3sitsolutions.models.Matrix;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MatrixDTOTest {

    @Inject
    Factory factory;

    @Test
    void toEntity() {
        List<List<Character>> matrixData = factory.matrixDataFactoryValid();

        MatrixDTO dto = new MatrixDTO();
        dto.setId(1L);
        dto.setMatrix(matrixData);

        Matrix entity = dto.toEntity();

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(matrixData, entity.getMatrix());
    }

    @Test
    void getSetId() {
        MatrixDTO dto = new MatrixDTO();
        dto.setId(1L);

        assertEquals(1L, dto.getId());
    }

    @Test
    void getSetMatrix() {
        List<List<Character>> matrixData = factory.matrixDataFactoryValid();
        MatrixDTO dto = new MatrixDTO();
        dto.setMatrix(matrixData);

        assertEquals(matrixData, dto.getMatrix());
    }

    @Test
    void testEquals() {
        MatrixDTO dto1 = new MatrixDTO();
        dto1.setId(1L);
        dto1.setMatrix(factory.matrixDataFactoryValid());

        MatrixDTO dto2 = new MatrixDTO();
        dto2.setId(dto1.getId());
        dto2.setMatrix(dto1.getMatrix());

        assertTrue(dto1.equals(dto2) && dto2.equals(dto1));
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        MatrixDTO dto = new MatrixDTO();
        dto.setId(1L);
        dto.setMatrix(factory.matrixDataFactoryValid());

        assertNotNull(dto.toString());
    }
}