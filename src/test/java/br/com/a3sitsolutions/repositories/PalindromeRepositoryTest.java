package br.com.a3sitsolutions.repositories;

import br.com.a3sitsolutions.utils.Factory;
import br.com.a3sitsolutions.models.Palindrome;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
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

        Long matrixId = 1L;
        Palindrome expectedPalindrome = new Palindrome();
        expectedPalindrome.setPalindrome(palindromeRandom);
        expectedPalindrome.setMatrix(matrixId);
        List<Palindrome> expectedList = Collections.singletonList(expectedPalindrome);

        Mockito.when(palindromeRepository.findPalindromeByMatrixId(anyLong()))
                .thenAnswer(invocation -> Uni.createFrom().item(expectedList));

        Uni<List<Palindrome>> resultUni = palindromeRepository.findPalindromeByMatrixId(matrixId);

        List<Palindrome> result = resultUni.await().indefinitely();
        assertEquals(expectedList.size(), result.size());
        assertEquals(expectedPalindrome.getPalindrome(), result.get(0).getPalindrome());
    }

    @Test
    void findPalindromesByQuery() {
        String palindromeRandom = factory.palindromeFactory();

        Palindrome expectedPalindrome = new Palindrome();
        expectedPalindrome.setId(1L);
        expectedPalindrome.setPalindrome(palindromeRandom);
        expectedPalindrome.setMatrix(1L);

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

        Palindrome expectedPalindrome = new Palindrome();
        expectedPalindrome.setId(1L);
        expectedPalindrome.setPalindrome(palindromeRandom);
        expectedPalindrome.setMatrix(1L);

        Mockito.when(palindromeRepository.findPalindromesByQueryAndMatrixId(anyString(), eq(1L)))
                .thenAnswer(invocation -> {
                    String query = invocation.getArgument(0, String.class);
                    Long id = invocation.getArgument(1, Long.class);
                    if (palindromeRandom.equals(query) && 1L == id) {
                        return Uni.createFrom().item(List.of(expectedPalindrome));
                    } else {
                        return Uni.createFrom().item(List.of());
                    }
                });

        Uni<List<Palindrome>> resultUni = palindromeRepository.findPalindromesByQueryAndMatrixId(palindromeRandom, 1L);

        List<Palindrome> result = resultUni.await().indefinitely();
        assertFalse(result.isEmpty());
        assertEquals(expectedPalindrome.getPalindrome(), result.get(0).getPalindrome());
    }
}