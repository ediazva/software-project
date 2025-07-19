// file: src/main/java/org/unsa/service/interfaces/IPlatilloServicio.java
package org.unsa.service.interfaces;

import org.unsa.model.domain.restaurantes.Plato;
import org.unsa.model.domain.restaurantes.Dinero; // Necesario para crear/actualizar Plato

import java.util.List;

/**
 * Interfaz para el servicio de gestion de platillos.
 * Define las operaciones de negocio relacionadas con los platos de un restaurante.
 */
public interface IPlatilloServicio {
    Plato crearPlatillo(String idRestaurante, String nombre, String descripcion, Dinero precio);
    List<Plato> listarPlatillosPorRestaurante(String idRestaurante);
    Plato verDetallePlatillo(String idPlatillo);
    Plato actualizarPlatillo(String idPlatillo, String nombre, String descripcion, Dinero precio, boolean disponible);
    void eliminarPlatillo(String idPlatillo);
    void marcarPlatilloComoDisponible(String idPlatillo, boolean disponible);
}
