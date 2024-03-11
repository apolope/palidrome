package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.models.Palindrome;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PalindromeRepository implements PanacheRepositoryBase<Palindrome, Long> {

    public Uni<Palindrome> saveOrUpdateByMatrixId(Palindrome palindrome) {
        if (palindrome.getId() == null) {
            return Panache.withTransaction(() -> persist(palindrome)).replaceWith(palindrome);
        } else {
            return Panache.withTransaction(() -> findById(palindrome.getId())
                    .onItem().ifNotNull().transform(entity -> entity)
                    .onItem().ifNull().fail()
            );
        }
    }

    public Uni<List<Palindrome>> findPalindromeByMatrixId(Long id) {
        return findPalindromesByQueryAndMatrixId(null, id);
    }

    public Uni<List<Palindrome>> findPalindromesByQuery(String q) {
        return findPalindromesByQueryAndMatrixId(q, null);
    }

    public Uni<List<Palindrome>> findPalindromesByQueryAndMatrixId(String q, Long matrixId) {
        if (matrixId != null) {
            if (q == null) {
                return find("matrix = ?1", matrixId).list();
            } else {
                return find("matrix = ?1 and palindrome like ?2", matrixId, "%" + q + "%").list();
            }
        } else {
            if (q != null) {
                return find("palindrome like ?1", "%" + q + "%").list();
            }
        }
        return findAll().list();
    }
}
