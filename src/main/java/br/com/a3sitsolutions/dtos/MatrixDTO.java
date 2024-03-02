package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.models.Matrix;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class MatrixDTO {

    private ObjectId id;

    private List<List<Character>> matrix;

    public MatrixDTO(Matrix matrix) {
        this.id = matrix.getId();
        this.matrix = matrix.getMatrix();
    }

    public Matrix toEntity() {
        Matrix matrix = new Matrix();
        matrix.setId(this.id);
        matrix.setMatrix(this.matrix);
        return matrix;
    }

}
