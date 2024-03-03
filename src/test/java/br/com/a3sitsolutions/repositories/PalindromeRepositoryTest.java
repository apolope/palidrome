package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.utils.Factory;
import br.com.a3sitsolutions.models.Palindrome;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;

@QuarkusTest
class PalindromeRepositoryTest {

    @Inject
    Factory factory;

    @InjectMock
    PalindromeRepository palindromeRepository;

    @Test
    void findPalindromeByMatrixId() {
        String palindromeRandom = factory.palindromeFactory();

        ObjectId matrixId = new ObjectId();
        Palindrome expectedPalindrome = new Palindrome();
        expectedPalindrome.setPalindrome(palindromeRandom);
        expectedPalindrome.setMatrix(matrixId);
        List<Palindrome> expectedList = Collections.singletonList(expectedPalindrome);

        Mockito.when(palindromeRepository.findPalindromeByMatrixId(anyString()))
                .thenAnswer(invocation -> Uni.createFrom().item(expectedList));

        Uni<List<Palindrome>> resultUni = palindromeRepository.findPalindromeByMatrixId(matrixId.toString());

        List<Palindrome> result = resultUni.await().indefinitely();
        assertEquals(expectedList.size(), result.size());
        assertEquals(expectedPalindrome.getPalindrome(), result.get(0).getPalindrome());
    }

    @Test
    void findPalindromesByQuery() {
        String palindromeRandom = factory.palindromeFactory();

        Palindrome expectedPalindrome = new Palindrome();
        expectedPalindrome.setId(new ObjectId());
        expectedPalindrome.setPalindrome(palindromeRandom);
        expectedPalindrome.setMatrix(new ObjectId());

        Mockito.when(palindromeRepository.findPalindromesByQuery(anyString()))
                .thenAnswer(invocation -> {
                    return Uni.createFrom().item(List.of(expectedPalindrome));
                });

        Uni<List<Palindrome>> resultUni = palindromeRepository.findPalindromesByQuery(palindromeRandom);

        List<Palindrome> result = resultUni.await().indefinitely();
        assertFalse(result.isEmpty());
        assertEquals(palindromeRandom, result.get(0).getPalindrome());
    }

    @Test
    void findPalindromesByQueryAndMatrixId() {
        String palindromeRandom = factory.palindromeFactory();

        ObjectId matrixId = new ObjectId();
        Palindrome expectedPalindrome = new Palindrome();
        expectedPalindrome.setId(new ObjectId());
        expectedPalindrome.setPalindrome(palindromeRandom);
        expectedPalindrome.setMatrix(matrixId);

        Mockito.when(palindromeRepository.findPalindromesByQueryAndMatrixId(anyString(), eq(matrixId.toHexString())))
                .thenAnswer(invocation -> {
                    String query = invocation.getArgument(0, String.class);
                    String id = invocation.getArgument(1, String.class);
                    if (palindromeRandom.equals(query) && matrixId.toHexString().equals(id)) {
                        return Uni.createFrom().item(List.of(expectedPalindrome));
                    } else {
                        return Uni.createFrom().item(List.of());
                    }
                });

        Uni<List<Palindrome>> resultUni = palindromeRepository.findPalindromesByQueryAndMatrixId(palindromeRandom, matrixId.toHexString());

        List<Palindrome> result = resultUni.await().indefinitely();
        assertFalse(result.isEmpty());
        assertEquals(expectedPalindrome.getPalindrome(), result.get(0).getPalindrome());
    }
}