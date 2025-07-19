// file: src/main/java/org/unsa/service/restaurantes/CatalogoRestaurantesService.java
package org.unsa.model.service.impl;

import org.unsa.model.domain.restaurantes.Restaurante;
import org.unsa.model.domain.restaurantes.TipoCocina;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Clase de servicio para la gestion y consulta del catalogo de restaurantes.
 * Se encarga de la logica de negocio relacionada con la busqueda y obtencion de restaurantes.
 */
public class CatalogoRestaurantesService {

    private static final Logger logger = Logger.getLogger(CatalogoRestaurantesService.class.getName());

    // SIMULACION: Lista de restaurantes para propositos de demostracion.
    // En una aplicacion real, esto interactuaria con un RestauranteRepository.
    private final List<Restaurante> restaurantesSimulados;

    /**
     * Constructor por defecto para la clase CatalogoRestaurantesService.
     * Inicializa una lista de restaurantes de ejemplo.
     */
    public CatalogoRestaurantesService() {
        this.restaurantesSimulados = new ArrayList<>();
        // Añadir algunos restaurantes de ejemplo
        restaurantesSimulados.add(new Restaurante(1, "Pizzeria La Delicia", "Av. Principal 123", "987654321", TipoCocina.ITALIANA));
        restaurantesSimulados.add(new Restaurante(2, "Antojitos Peruanos", "Calle Falsa 456", "999888777", TipoCocina.PERUANA));
        restaurantesSimulados.add(new Restaurante(3, "Sushi Master", "Jr. Paz 789", "911223344", TipoCocina.JAPONESA));
        restaurantesSimulados.add(new Restaurante(4, "El Sabor Mexicano", "Av. Sol 101", "955667788", TipoCocina.MEXICANA));

        logger.info(() -> "Servicio de CatalogoRestaurantesService inicializado con " + restaurantesSimulados.size() + " restaurantes simulados.");
    }

    /**
     * Busca restaurantes por un texto dado en su nombre, direccion o tipo de cocina.
     * @param texto El texto a buscar (puede ser nulo o vacio para obtener todos).
     * @return Una lista de restaurantes que coinciden con el criterio de busqueda.
     */
    public List<Restaurante> buscarPorTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            logger.info(() -> "Busqueda de restaurantes sin texto. Obteniendo todos.");
            return obtenerTodos();
        }

        String textoLowerCase = texto.toLowerCase();
        List<Restaurante> resultados = restaurantesSimulados.stream()
                .filter(r -> r.getNombre().toLowerCase().contains(textoLowerCase) ||
                        r.getDireccion().toLowerCase().contains(textoLowerCase) ||
                        r.getTipoCocina().name().toLowerCase().contains(textoLowerCase)) // Compara con el nombre del enum
                .collect(Collectors.toList());

        logger.info(() -> "Busqueda por texto '" + texto + "' resulto en " + resultados.size() + " restaurantes.");
        return resultados;
    }

    /**
     * Obtiene una lista de todos los restaurantes disponibles en el catalogo.
     * @return Una lista de todos los objetos Restaurante.
     */
    public List<Restaurante> obtenerTodos() {
        logger.info(() -> "Obteniendo todos los " + restaurantesSimulados.size() + " restaurantes.");
        return new ArrayList<>(restaurantesSimulados); // Retorna una copia para evitar modificacion externa
    }

    // NOTA: Los metodos como 'asignarRepartidorAPedido', 'actualizarEstadoEntrega' y 'crearNuevoPedido'
    // fueron eliminados de esta clase porque no corresponden a la responsabilidad de un
    // CatalogoRestaurantesService. Esos metodos pertenecen a un servicio de Pedidos (ej. PedidoManager).
}
