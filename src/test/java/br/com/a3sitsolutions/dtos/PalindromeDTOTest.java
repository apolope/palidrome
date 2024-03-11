package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.utils.Factory;
import br.com.a3sitsolutions.models.Palindrome;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PalindromeDTOTest {

    @Inject
    Factory factory;

    @Test
    void toEntity() {
        String palindromeStr = factory.palindromeFactory();

        PalindromeDTO dto = new PalindromeDTO();
        dto.setId(1L);
        dto.setPalindrome(palindromeStr);
        dto.setMatrix(1L);

        Palindrome entity = dto.toEntity();

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(palindromeStr, entity.getPalindrome());
        assertEquals(1L, entity.getMatrix());
    }

    @Test
    void getSetId() {
        PalindromeDTO dto = new PalindromeDTO();
        dto.setId(1L);

        assertEquals(1L, dto.getId());
    }

    @Test
    void getSetPalindrome() {
        String palindromeStr = factory.palindromeFactory();
        PalindromeDTO dto = new PalindromeDTO();
        dto.setPalindrome(palindromeStr);

        assertEquals(palindromeStr, dto.getPalindrome());
    }

    @Test
    void getSetMatrix() {
        PalindromeDTO dto = new PalindromeDTO();
        dto.setMatrix(1L);

        assertEquals(1L, dto.getMatrix());
    }

    @Test
    void testEquals() {
        PalindromeDTO dto1 = new PalindromeDTO();
        dto1.setId(1L);
        dto1.setPalindrome(factory.palindromeFactory());
        dto1.setMatrix(1L);

        PalindromeDTO dto2 = new PalindromeDTO();
        dto2.setId(dto1.getId());
        dto2.setPalindrome(dto1.getPalindrome());
        dto2.setMatrix(dto1.getMatrix());

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PalindromeDTO dto = new PalindromeDTO();
        dto.setId(1L);
        dto.setPalindrome(factory.palindromeFactory());
        dto.setMatrix(1L);

        assertNotNull(dto.toString());
    }
}