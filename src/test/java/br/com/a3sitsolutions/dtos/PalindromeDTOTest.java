package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.Utils.Factory;
import br.com.a3sitsolutions.models.Palindrome;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class PalindromeDTOTest {

    @Inject
    Factory factory;

    @Test
    void toEntity() {
        ObjectId id = new ObjectId();
        String palindromeStr = factory.palindromeFactory();
        ObjectId matrixId = new ObjectId();

        PalindromeDTO dto = new PalindromeDTO();
        dto.setId(id);
        dto.setPalindrome(palindromeStr);
        dto.setMatrix(matrixId);

        Palindrome entity = dto.toEntity();

        assertNotNull(entity);
        assertEquals(id, entity.getId());
        assertEquals(palindromeStr, entity.getPalindrome());
        assertEquals(matrixId, entity.getMatrix());
    }

    @Test
    void getSetId() {
        ObjectId id = new ObjectId();
        PalindromeDTO dto = new PalindromeDTO();
        dto.setId(id);

        assertEquals(id, dto.getId());
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
        ObjectId matrixId = new ObjectId();
        PalindromeDTO dto = new PalindromeDTO();
        dto.setMatrix(matrixId);

        assertEquals(matrixId, dto.getMatrix());
    }

    @Test
    void testEquals() {
        PalindromeDTO dto1 = new PalindromeDTO();
        dto1.setId(new ObjectId());
        dto1.setPalindrome(factory.palindromeFactory());
        dto1.setMatrix(new ObjectId());

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
        dto.setId(new ObjectId());
        dto.setPalindrome(factory.palindromeFactory());
        dto.setMatrix(new ObjectId());

        assertNotNull(dto.toString());
    }
}