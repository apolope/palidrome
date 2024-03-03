package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.models.Palindrome;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
public class PalindromeDTO {

    private ObjectId id;
    private String palindrome;
    private ObjectId matrix;

    public PalindromeDTO(Palindrome palindrome) {
        this.id = palindrome.getId();
        this.palindrome = palindrome.getPalindrome();
        this.matrix = palindrome.getMatrix();
    }

    public Palindrome toEntity() {
        Palindrome palindrome = new Palindrome();
        palindrome.setId(this.id);
        palindrome.setPalindrome(this.palindrome);
        palindrome.setMatrix(this.matrix);
        return palindrome;
    }
}
