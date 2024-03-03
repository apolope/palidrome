package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.Utils.Factory;
import br.com.a3sitsolutions.models.Matrix;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class MatrixDTOTest {

    @Inject
    Factory factory;

    @Test
    void toEntity() {
        ObjectId id = new ObjectId();
        List<List<Character>> matrixData = factory.matrixDataFactoryValid();

        MatrixDTO dto = new MatrixDTO();
        dto.setId(id);
        dto.setMatrix(matrixData);

        Matrix entity = dto.toEntity();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(matrixData, entity.getMatrix());
    }

    @Test
    void getSetId() {
        ObjectId id = new ObjectId();
        MatrixDTO dto = new MatrixDTO();
        dto.setId(id);

        assertEquals(id, dto.getId());
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
        dto1.setId(new ObjectId());
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
        dto.setId(new ObjectId());
        dto.setMatrix(factory.matrixDataFactoryValid());

        assertNotNull(dto.toString());
    }
}