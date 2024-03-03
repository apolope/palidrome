package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.models.Matrix;
import br.com.a3sitsolutions.utils.MessagesUtil;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

@ApplicationScoped
public class MatrixRepository implements ReactivePanacheMongoRepository<Matrix> {

    public Uni<Matrix> findByMatrixId(ObjectId id) {
        return findById(id)
                .onItem().ifNull().fail()
                .onFailure().transform(e -> new NotFoundException(MessagesUtil.NOT_FOUND_ID, id.toString()));
    }
}
