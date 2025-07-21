
package org.unsa.model.domain.usuarios;
import jakarta.persistence.Embeddable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String calle;
    private String ciudad;
    private String distrito;
    private String referencia;

    // Puedes agregar validaciones con @NotNull, @Size, etc.
}
