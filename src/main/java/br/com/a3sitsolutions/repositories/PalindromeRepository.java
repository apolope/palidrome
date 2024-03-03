package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.models.Palindrome;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;
import java.util.List;

@ApplicationScoped
public class PalindromeRepository implements ReactivePanacheMongoRepository<Palindrome> {

    public Uni<Palindrome> saveOrUpdateByMatrixId(Palindrome palindrome) {
        return persistOrUpdate(palindrome);
    }

    public Uni<List<Palindrome>> findPalindromeByMatrixId(String id) {
        ObjectId matrixId;
        try {
            matrixId = new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return Uni.createFrom().failure(e);
        }
        return find("matrix", matrixId).list();
    }

    public Uni<List<Palindrome>> findPalindromesByQuery(String q) {
        String regexPattern = ".*" + q + ".*";
        return find("{'palindrome': {$regex: ?1, $options: 'i'}}", regexPattern).list();
    }
}
