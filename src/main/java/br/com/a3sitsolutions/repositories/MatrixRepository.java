package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.exceptions.NotFoundException;
import br.com.a3sitsolutions.models.Matrix;
import br.com.a3sitsolutions.utils.MessagesUtil;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MatrixRepository implements PanacheRepositoryBase<Matrix, Long> {

    public Uni<Matrix> findByMatrixId(Long id) {
        return findById(id)
                .onItem().ifNull().failWith(() -> new NotFoundException(MessagesUtil.NOT_FOUND_ID, id.toString()))
                .onFailure().recoverWithUni(Uni.createFrom().failure(() -> new NotFoundException(MessagesUtil.NOT_FOUND_ID, id.toString())));
    }

    public Uni<Matrix> persistOrUpdate(Matrix matrix) {
        if (matrix.getId() == null) {
            return Panache.withTransaction(() -> persist(matrix)).replaceWith(matrix);
        } else {
            return Panache.withTransaction(() -> findById(matrix.getId())
                    .onItem().ifNotNull().invoke(entity -> {
                        entity.setMatrix(matrix.getMatrix());
                    })).replaceWith(matrix);
        }
    }
}
