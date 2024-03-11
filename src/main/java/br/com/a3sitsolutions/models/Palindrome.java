package br.com.a3sitsolutions.models;

import br.com.a3sitsolutions.dtos.PalindromeDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Palindrome extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String palindrome;
    private Long matrix;

    public PalindromeDTO of() {
        return new PalindromeDTO(this);
    }
}
