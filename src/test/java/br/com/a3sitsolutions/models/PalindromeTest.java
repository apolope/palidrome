package br.com.a3sitsolutions.models;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    @Test
    void getSetId() {
        Palindrome palindrome = new Palindrome();
        ObjectId id = new ObjectId();
        palindrome.setId(id);

        assertEquals(id, palindrome.getId());
    }

    @Test
    void getSetPalindrome() {
        Palindrome palindrome = new Palindrome();
        String content = "madam";
        palindrome.setPalindrome(content);

        assertEquals(content, palindrome.getPalindrome());
    }

    @Test
    void getSetMatrix() {
        Palindrome palindrome = new Palindrome();
        ObjectId matrixId = new ObjectId();
        palindrome.setMatrix(matrixId);

        assertEquals(matrixId, palindrome.getMatrix());
    }

    @Test
    void testEquals() {
        Palindrome palindrome1 = new Palindrome();
        palindrome1.setPalindrome("level");
        palindrome1.setMatrix(new ObjectId());

        Palindrome palindrome2 = new Palindrome();
        palindrome2.setPalindrome("level");
        palindrome2.setMatrix(palindrome1.getMatrix());

        assertEquals(palindrome1, palindrome2);
    }

    @Test
    void canEqual() {
        Palindrome palindrome = new Palindrome();

        assertTrue(palindrome.canEqual(new Palindrome()));
    }

    @Test
    void testHashCode() {
        Palindrome palindrome = new Palindrome();
        palindrome.setPalindrome("level");
        palindrome.setMatrix(new ObjectId());

        Palindrome samePalindrome = new Palindrome();
        samePalindrome.setPalindrome("level");
        samePalindrome.setMatrix(palindrome.getMatrix());

        assertEquals(palindrome.hashCode(), samePalindrome.hashCode());
    }

    @Test
    void testToString() {
        Palindrome palindrome = new Palindrome();
        palindrome.setPalindrome("level");
        palindrome.setMatrix(new ObjectId());

        String toStringResult = palindrome.toString();
        assertTrue(toStringResult.contains("level"));
        assertNotNull(toStringResult);
    }
}