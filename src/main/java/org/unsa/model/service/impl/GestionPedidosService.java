// file: src/main/java/org/unsa/model/servicios/GestionPedidosService.java
package org.unsa.model.service.impl;

import org.springframework.stereotype.Service;
import org.unsa.model.controller.PedidosController;
import org.unsa.model.domain.pedidos.Pedido;
import org.unsa.model.domain.pedidos.DatosPlatoPedido;
import org.unsa.model.service.PedidoManager;
import org.unsa.model.domain.pedidos.EstadoPedido;
import org.unsa.model.domain.usuarios.Direccion;
import org.unsa.model.service.Interfaces.IPedidoServicio;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de servicio para la gestion de pedidos.
 * Contiene la logica de negocio relacionada con la creacion, actualizacion y consulta de pedidos.
 */
@Service
public class GestionPedidosService implements IPedidoServicio {

    private static final Logger logger = LoggerFactory.getLogger(GestionPedidosService.class);
    private final PedidoManager pedidoManager;

    /**
     * Constructor por defecto para la clase GestionPedidosService.
     * Inicializa el PedidoManager.
     */
    public GestionPedidosService() {
        this.pedidoManager = new PedidoManager();
        logger.info("Servicio de GestionPedidosService inicializado.");
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
    @Override
    public Pedido crearPedido(Integer idCliente, Integer idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales) {
        logger.info(("Solicitud para crear un nuevo pedido para cliente {} en restaurante {}",idCliente,idRestaurante);
        try {
            Pedido nuevoPedido = pedidoManager.crearNuevoPedido(idCliente, idRestaurante, itemsCarrito, direccionEntrega, instruccionesEspeciales);
            logger.info(() -> "Pedido " + nuevoPedido.getId() + " creado exitosamente.");
            return nuevoPedido;
        } catch (IllegalArgumentException e) {
            logger.log( "Fallo al crear pedido: ", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Pedido obtenerPedidoPorId(Integer idPedido) {
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
    public List<Pedido> obtenerPedidosPorCliente(Integer idCliente) {
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
    public void actualizarEstadoPedido(Integer idPedido, EstadoPedido nuevoEstado) {
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
    public void asignarRepartidorAPedido(Integer idPedido, Integer idRepartidor) {
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
    public void cancelarPedido(Integer idPedido, Integer idUsuario) {
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
    public void confirmarEntrega(Integer idPedido) {
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
