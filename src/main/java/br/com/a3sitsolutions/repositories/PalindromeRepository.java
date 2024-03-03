package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.exceptions.IdConverterExeception;
import br.com.a3sitsolutions.models.Palindrome;
import br.com.a3sitsolutions.utils.MessagesUtil;
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
        return findPalindromesByQueryAndMatrixId(null, id);
    }

    public Uni<List<Palindrome>> findPalindromesByQuery(String q) {
        return findPalindromesByQueryAndMatrixId(q, null);
    }

    public Uni<List<Palindrome>> findPalindromesByQueryAndMatrixId(String q, String matrixId) {
        ObjectId matrixObjectId;

        if (matrixId != null) {
            matrixObjectId = getObjectId(matrixId);

            if (matrixObjectId == null) {
                return Uni.createFrom().failure(new IdConverterExeception(MessagesUtil.ID_CONVERTER_PROBLEM, matrixId));
            }

            if (q == null) {
                return find("matrix", matrixObjectId).list();
            }

            return find("{'matrix': ?1, 'palindrome': {$regex: ?2, $options: 'i'}}", matrixObjectId, formatQuery(q)).list();
        }

        if (q != null) {
            return find("{'palindrome': {$regex: ?1, $options: 'i'}}", formatQuery(q)).list();
        }

        return findAll().list();
    }

    private String formatQuery(String q) {
        return ".*" + q + ".*";
    }

    private ObjectId getObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
