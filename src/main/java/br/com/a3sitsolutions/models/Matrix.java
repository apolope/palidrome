package br.com.a3sitsolutions.models;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import br.com.a3sitsolutions.utils.MatrixConverter;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Matrix extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = MatrixConverter.class)
    private List<List<Character>> matrix;

    public MatrixDTO of() {
        return new MatrixDTO(this);
    }

}
