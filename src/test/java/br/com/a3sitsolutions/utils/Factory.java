package br.com.a3sitsolutions.utils;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.models.Matrix;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.Random;

@Singleton
public class Factory {

    public Uni<Matrix> matrixUniFactory() {
        Matrix matrix = new Matrix();

        matrix.setId(1L);

        matrix.setMatrix(matrixDataFactoryValid());

        return Uni.createFrom().item(matrix);
    }

    public Matrix matrixFactoryEntity() {
        Matrix matrix = new Matrix();

        matrix.setId(1L);
        matrix.setMatrix(matrixDataFactoryValid());

        return matrix;
    }

    public MatrixDTO matrixFactoryDTO() {
        MatrixDTO matrix = new MatrixDTO();

        matrix.setId(1L);
        matrix.setMatrix(matrixDataFactoryValid());

        return matrix;
    }

    public String palindromeFactory() {
        List<String> palindromes = List.of(
                "LEVEL",
                "ROTOR",
                "RADAR",
                "MADAM",
                "REFER",
                "CIVIC",
                "DEIFIED",
                "RACECAR",
                "TENET",
                "REVIVER"
        );

        return palindromes.get(new Random().nextInt(palindromes.size()));
    }

    public List<List<Character>> matrixDataFactoryValid() {
        return List.of(List.of('A', 'B', 'C', 'D', 'E', 'F'),
                List.of('G', 'H', 'I', 'J', 'K', 'L'),
                List.of('M', 'N', 'O', 'P', 'Q', 'R'),
                List.of('S', 'T', 'U', 'V', 'W', 'X'),
                List.of('Y', 'Z', 'A', 'B', 'C', 'D'),
                List.of('E', 'F', 'G', 'H', 'I', 'J'));
    }

    public List<List<Character>> matrixDataFactoryInvalid() {
        return List.of(List.of('A', 'B', 'C'),
                List.of('D', 'E', 'F'),
                List.of('G', 'H', 'I'));
    }
}
