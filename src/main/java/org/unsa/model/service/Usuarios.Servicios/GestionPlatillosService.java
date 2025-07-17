// file: src/main/java/org/unsa/service/platillos/GestionPlatillosService.java
package org.unsa.service.platillos; // Nuevo paquete para servicios de platillos

import org.unsa.model.dominio.restaurantes.Plato;
import org.unsa.model.dominio.restaurantes.Dinero;
import org.unsa.service.interfaces.IPlatilloServicio; // Importar la interfaz

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase de servicio para la gestion de platillos.
 * Se encarga de la logica de negocio relacionada con la creacion, actualizacion y consulta de platos.
 */
public class GestionPlatillosService implements IPlatilloServicio { // ¡Implementa la interfaz!

    private static final Logger logger = Logger.getLogger(GestionPlatillosService.class.getName());

    // SIMULACION: Lista de platos para propositos de demostracion.
    // En una aplicacion real, esto interactuaria con un PlatoRepository.
    private final List<Plato> platosSimulados;

    public GestionPlatillosService() {
        this.platosSimulados = new ArrayList<>();
        // Añadir algunos platos de ejemplo para simular
        platosSimulados.add(new Plato(1, "Pizza Pepperoni", "Deliciosa pizza con queso y pepperoni", true, new Dinero(15.99, "PEN")));
        platosSimulados.add(new Plato(2, "Ceviche Mixto", "Pescado y mariscos frescos", true, new Dinero(25.00, "PEN")));
        platosSimulados.add(new Plato(3, "Lomo Saltado", "Clasico plato peruano", true, new Dinero(30.00, "PEN")));
        platosSimulados.add(new Plato(5, "Ensalada Cesar", "Ensalada fresca con pollo", true, new Dinero(12.50, "PEN")));

        logger.info(() -> "Servicio de GestionPlatillosService inicializado con " + platosSimulados.size() + " platos simulados.");
    }

    @Override
    public Plato crearPlatillo(String idRestaurante, String nombre, String descripcion, Dinero precio) {
        if (idRestaurante == null || idRestaurante.trim().isEmpty()) throw new IllegalArgumentException("ID de restaurante no puede ser nulo o vacio.");
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("Nombre del plato no puede ser nulo o vacio.");
        if (precio == null) throw new IllegalArgumentException("Precio del plato no puede ser nulo.");

        String nuevoIdPlato = UUID.randomUUID().toString();
        Plato nuevoPlato = new Plato(nuevoIdPlato, nombre, descripcion, true, precio);
        platosSimulados.add(nuevoPlato); // Simular guardado
        logger.info(() -> "Platillo '" + nombre + "' creado para restaurante " + idRestaurante + " con ID: " + nuevoIdPlato);
        return nuevoPlato;
    }

    @Override
    public List<Plato> listarPlatillosPorRestaurante(String idRestaurante) {
        logger.info(() -> "Listando platillos para restaurante con ID: " + idRestaurante);
        // SIMULACION: Filtrar platos por un ID de restaurante (asumiendo que los platos tienen un idRestaurante asociado, aunque nuestro Plato actual no lo tiene. Esto es un placeholder)
        // Para la simulacion, devolveremos todos los platos. En una app real, los platos estarian asociados a un restaurante.
        return platosSimulados.stream()
                .filter(plato -> true) // Placeholder, en realidad plato.getIdRestaurante().equals(idRestaurante)
                .collect(Collectors.toList());
    }

    @Override
    public Plato verDetallePlatillo(String idPlatillo) {
        logger.info(() -> "Viendo detalle de platillo con ID: " + idPlatillo);
        return platosSimulados.stream()
                .filter(p -> p.getIdPlato().equals(idPlatillo))
                .findFirst()
                .orElse(null); // O lanzar una excepcion NotFound
    }

    @Override
    public Plato actualizarPlatillo(String idPlatillo, String nombre, String descripcion, Dinero precio, boolean disponible) {
        logger.info(() -> "Actualizando platillo con ID: " + idPlatillo);
        Plato platoExistente = verDetallePlatillo(idPlatillo);
        if (platoExistente != null) {
            platoExistente.setNombre(nombre);
            platoExistente.setDescripcion(descripcion);
            platoExistente.setPrecio(precio);
            platoExistente.setDisponible(disponible);
            logger.info(() -> "Platillo " + idPlatillo + " actualizado.");
            return platoExistente;
        }
        logger.warning(() -> "Platillo con ID " + idPlatillo + " no encontrado para actualizar.");
        return null;
    }

    @Override
    public void eliminarPlatillo(String idPlatillo) {
        logger.info(() -> "Eliminando platillo con ID: " + idPlatillo);
        boolean eliminado = platosSimulados.removeIf(p -> p.getIdPlato().equals(idPlatillo));
        if (eliminado) {
            logger.info(() -> "Platillo " + idPlatillo + " eliminado exitosamente.");
        } else {
            logger.warning(() -> "Platillo con ID " + idPlatillo + " no encontrado para eliminar.");
        }
    }

    @Override
    public void marcarPlatilloComoDisponible(String idPlatillo, boolean disponible) {
        logger.info(() -> "Marcando platillo " + idPlatillo + " como disponible: " + disponible);
        Plato plato = verDetallePlatillo(idPlatillo);
        if (plato != null) {
            plato.marcarComoDisponible(disponible);
            logger.info(() -> "Platillo " + idPlatillo + " disponibilidad actualizada a " + disponible + ".");
        } else {
            logger.warning(() -> "Platillo con ID " + idPlatillo + " no encontrado para actualizar disponibilidad.");
        }
    }
}
