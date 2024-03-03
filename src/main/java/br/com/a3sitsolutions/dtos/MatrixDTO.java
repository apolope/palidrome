package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.models.Matrix;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import java.util.List;

@Data
@NoArgsConstructor
public class MatrixDTO {

    @Schema(description = "The unique identifier of the item.", example = "5fcb1234")
    private ObjectId id;

    @Schema(description = "The matrix.", example = "[['X', 'O'], ['O', 'X']]")
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
