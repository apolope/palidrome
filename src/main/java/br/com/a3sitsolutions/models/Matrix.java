package br.com.a3sitsolutions.models;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.ObjectId;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@MongoEntity(collection = "matrix")
public class Matrix extends ReactivePanacheMongoEntity {

    public ObjectId id;
    private List<List<Character>> matrix;

    public MatrixDTO of() {
        return new MatrixDTO(this);
    }

}
