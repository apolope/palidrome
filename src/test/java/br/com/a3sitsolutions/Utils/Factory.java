package br.com.a3sitsolutions.Utils;

import br.com.a3sitsolutions.models.Matrix;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Random;

@Singleton
public class Factory {

    public Uni<Matrix> matrixFactory() {
        Matrix matrix = new Matrix();

        matrix.setId(new ObjectId());

        matrix.setMatrix(List.of(
                List.of('A', 'B', 'C'),
                List.of('D', 'E', 'F'),
                List.of('G', 'H', 'I')));

        return Uni.createFrom().item(matrix);
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
}
