package br.com.a3sitsolutions.models;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void of() {
        Matrix matrix = new Matrix();
        matrix.setMatrix(List.of(List.of('A', 'B'), List.of('C', 'D')));

        MatrixDTO result = matrix.of();

        assertNotNull(result);
        assertEquals(matrix.getMatrix(), result.getMatrix());
    }

    @Test
    void getSetId() {
        Matrix matrix = new Matrix();
        matrix.setId(1L);

        assertEquals(1L, matrix.getId());
    }

    @Test
    void getSetMatrix() {
        Matrix matrix = new Matrix();
        List<List<Character>> expectedMatrix = List.of(List.of('A', 'B'), List.of('C', 'D'));
        matrix.setMatrix(expectedMatrix);

        assertEquals(expectedMatrix, matrix.getMatrix());
    }

    @Test
    void testToString() {
        Matrix matrix = new Matrix();
        matrix.setMatrix(List.of(List.of('A', 'B'), List.of('C', 'D')));

        String result = matrix.toString();

        assertNotNull(result);
        assertTrue(
                result.contains("A") &&
                        result.contains("B") &&
                        result.contains("C") &&
                        result.contains("D"));
    }

    @Test
    void testEquals() {
        Matrix matrix1 = new Matrix();
        matrix1.setMatrix(List.of(List.of('A'), List.of('B')));
        Matrix matrix2 = new Matrix();
        matrix2.setMatrix(List.of(List.of('A'), List.of('B')));

        assertEquals(matrix1, matrix2);
    }

    @Test
    void canEqual() {
        Matrix matrix1 = new Matrix();
        matrix1.setMatrix(List.of(List.of('A'), List.of('B')));
        Matrix matrix2 = new Matrix();
        matrix2.setMatrix(List.of(List.of('C'), List.of('D')));

        assertNotEquals(matrix1, matrix2);
    }

    @Test
    void testHashCode() {
        Matrix matrix1 = new Matrix();
        matrix1.setMatrix(List.of(List.of('A'), List.of('B')));
        Matrix matrix2 = new Matrix();
        matrix2.setMatrix(List.of(List.of('A'), List.of('B')));

        assertEquals(matrix1.hashCode(), matrix2.hashCode());
    }
}