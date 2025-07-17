// file: src/main/java/org/unsa/model/servicios/GestionPedidosService.java
package org.unsa.model.service;

import org.unsa.model.dominio.pedidos.Pedido;
import org.unsa.model.dominio.pedidos.DatosPlatoPedido;
import org.unsa.model.dominio.pedidos.PedidoManager;
import org.unsa.model.dominio.pedidos.EstadoPedido;
import org.unsa.model.dominio.usuarios.Direccion;
import org.unsa.model.service.Interfaces.IPedidoServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de servicio para la gestion de pedidos.
 * Contiene la logica de negocio relacionada con la creacion, actualizacion y consulta de pedidos.
 */
public class GestionPedidosService implements IPedidoServicio { // ¡Implementa la interfaz!

    private static final Logger logger = Logger.getLogger(GestionPedidosService.class.getName());
    private final PedidoManager pedidoManager; // Inyectar el PedidoManager

    /**
     * Constructor por defecto para la clase GestionPedidosService.
     * Inicializa el PedidoManager.
     */
    public GestionPedidosService() {
        this.pedidoManager = new PedidoManager(); // En Spring, esto se inyectaria automaticamente
        logger.info(() -> "Servicio de GestionPedidosService inicializado.");
    }

    /**
     * Crea un nuevo pedido en el sistema, encapsulando la logica del PedidoManager.
     * @param idCliente ID del cliente que realiza el pedido.
     * @param idRestaurante ID del restaurante al que se hace el pedido.
     * @param itemsCarrito Lista de datos de platos y cantidades para el pedido.
     * @param direccionEntrega Direccion de entrega del pedido.
     * @param instruccionesEspeciales Instrucciones adicionales para el pedido (puede ser nulo).
     * @return El objeto Pedido recien creado.
     * @throws IllegalArgumentException Si los datos proporcionados son invalidos.
     */
    @Override // Anotacion @Override es buena practica
    public Pedido crearPedido(String idCliente, String idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales) {
        logger.info(() -> "Solicitud para crear un nuevo pedido para cliente " + idCliente + " en restaurante " + idRestaurante);
        try {
            Pedido nuevoPedido = pedidoManager.crearNuevoPedido(idCliente, idRestaurante, itemsCarrito, direccionEntrega, instruccionesEspeciales);
            logger.info(() -> "Pedido " + nuevoPedido.getId() + " creado exitosamente.");
            return nuevoPedido;
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, () -> "Fallo al crear pedido: " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Pedido obtenerPedidoPorId(String idPedido) {
        logger.info(() -> "Obteniendo pedido con ID: " + idPedido);
        // SIMULACION: En una app real, buscaria en un repositorio
        // Por ahora, devolveremos un pedido dummy si el ID coincide con uno de prueba
        if ("ped-001".equals(idPedido)) {
            // Necesitamos datos para crear un pedido dummy
            return new Pedido(1, 1, "rest-002", new Direccion("Av. Los Incas", "456", "Arequipa", "04001", "Frente al parque"), new ArrayList<>(), "Dummy");
        }
        logger.warning(() -> "Pedido con ID " + idPedido + " no encontrado (simulacion).");
        return null; // O lanzar una excepcion NotFound
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(String idCliente) {
        logger.info(() -> "Obteniendo pedidos para cliente con ID: " + idCliente);
        // SIMULACION: Devolver una lista de pedidos dummy
        List<Pedido> pedidos = new ArrayList<>();
        if ("cli-001".equals(idCliente)) {
            pedidos.add(new Pedido("ped-001", "cli-001", "rest-002", new Direccion("Av. Los Incas", "456", "Arequipa", "04001", "Frente al parque"), new ArrayList<>(), "Dummy 1"));
            pedidos.add(new Pedido("ped-005", "cli-001", "rest-001", new Direccion("Av. Los Incas", "456", "Arequipa", "04001", "Frente al parque"), new ArrayList<>(), "Dummy 2"));
        }
        return pedidos;
    }

    @Override
    public void actualizarEstadoPedido(String idPedido, EstadoPedido nuevoEstado) {
        logger.info(() -> "Actualizando estado del pedido " + idPedido + " a: " + nuevoEstado);
        // SIMULACION: En una app real, buscaria el pedido y actualizaria su estado
        Pedido pedido = obtenerPedidoPorId(idPedido); // Simular obtener el pedido
        if (pedido != null) {
            pedido.actualizarEstado(nuevoEstado);
            logger.info(() -> "Estado de pedido " + idPedido + " actualizado a " + nuevoEstado + " (simulacion).");
        } else {
            logger.warning(() -> "No se pudo actualizar estado. Pedido con ID " + idPedido + " no encontrado (simulacion).");
        }
    }

    @Override
    public void asignarRepartidorAPedido(String idPedido, String idRepartidor) {
        logger.info(() -> "Asignando repartidor " + idRepartidor + " al pedido " + idPedido);
        // SIMULACION
        Pedido pedido = obtenerPedidoPorId(idPedido);
        if (pedido != null) {
            pedido.setIdRepartidor(idRepartidor);
            logger.info(() -> "Repartidor " + idRepartidor + " asignado a pedido " + idPedido + " (simulacion).");
        } else {
            logger.warning(() -> "No se pudo asignar repartidor. Pedido con ID " + idPedido + " no encontrado (simulacion).");
        }
    }

    @Override
    public void cancelarPedido(String idPedido, String idUsuario) {
        logger.info(() -> "Cancelando pedido " + idPedido + " por usuario " + idUsuario);
        // SIMULACION
        Pedido pedido = obtenerPedidoPorId(idPedido);
        if (pedido != null) {
            pedido.actualizarEstado(EstadoPedido.Cancelado);
            logger.info(() -> "Pedido " + idPedido + " cancelado por usuario " + idUsuario + " (simulacion).");
        } else {
            logger.warning(() -> "No se pudo cancelar. Pedido con ID " + idPedido + " no encontrado (simulacion).");
        }
    }

    @Override
    public void confirmarEntrega(String idPedido) {
        logger.info(() -> "Confirmando entrega del pedido " + idPedido);
        // SIMULACION
        Pedido pedido = obtenerPedidoPorId(idPedido);
        if (pedido != null) {
            pedido.actualizarEstado(EstadoPedido.ENTREGADO);
            logger.info(() -> "Pedido " + idPedido + " confirmado como ENTREGADO (simulacion).");
        } else {
            logger.warning(() -> "No se pudo confirmar entrega. Pedido con ID " + idPedido + " no encontrado (simulacion).");
        }
    }
}
