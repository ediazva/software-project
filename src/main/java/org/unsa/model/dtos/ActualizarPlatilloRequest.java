package org.unsa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unsa.model.domain.restaurantes.Dinero;

/**
 * DTO para la solicitud de actualización de un platillo existente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarPlatilloRequest {
    private String nombre;
    private String descripcion;
    private Dinero precio;
    private boolean disponible;
}
