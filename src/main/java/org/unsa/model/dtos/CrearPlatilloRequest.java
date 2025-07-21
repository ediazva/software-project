package org.unsa.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unsa.model.domain.restaurantes.Dinero;

/**
 * DTO para la solicitud de creación de un nuevo platillo.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearPlatilloRequest {
    private String nombre;
    private String descripcion;
    private Dinero precio;
}
