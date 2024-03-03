package br.com.a3sitsolutions.dtos;

import br.com.a3sitsolutions.models.Palindrome;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@NoArgsConstructor
public class PalindromeDTO {

    @Schema(description = "The unique identifier of the item.", example = "5fcb1234")
    private ObjectId id;

    @Schema(description = "The palindrome.", example = "XOXO")
    private String palindrome;

    @Schema(description = "The unique identifier of the matrix.", example = "5fcb1234")
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
