// file: src/main/java/org/unsa/controller/PedidosController.java
package org.unsa.model.controller;

import org.unsa.model.domain.pedidos.Pedido;
import org.unsa.model.domain.pedidos.EstadoPedido;
import org.unsa.model.dtos.CrearPedidoRequest;
import org.unsa.model.dtos.ActualizarEstadoPedidoRequest;
import org.unsa.model.service.Interfaces.IPedidoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controlador REST para la gestion de pedidos.
 * Expone endpoints para crear, consultar y actualizar pedidos.
 */
@RestController
@RequestMapping("/pedidos") // Ruta base para todos los endpoints de pedidos
public class PedidosController {

    private static final Logger logger = LoggerFactory.getLogger(PedidosController.class);

    private final IPedidoServicio pedidoServicio;

    @Autowired // Inyeccion de dependencia
    public PedidosController(IPedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
        logger.info("PedidosController inicializado.");
    }

    /**
     * Endpoint para crear un nuevo pedido.
     * POST /api/pedidos
     * @param request El DTO con los datos para crear el pedido.
     * @return ResponseEntity con el pedido creado y estado HTTP 201.
     */
    @PutMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody CrearPedidoRequest request) {
        logger.info("Recibida solicitud para crear pedido para cliente: {}  ",request.getIdCliente());
        try {

            if (request.getIdCliente() == null || request.getIdCliente() <= 0 ||
                    request.getIdRestaurante() == null || request.getIdRestaurante() <= 0 ||
                    request.getItemsCarrito() == null || request.getItemsCarrito().isEmpty() ||
                    request.getDireccionEntrega() == null) {

                logger.warn( "Datos de creación de pedido incompletos o inválidos.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Pedido nuevoPedido = pedidoServicio.crearPedido(
                    request.getIdCliente(),
                    request.getIdRestaurante(),
                    request.getItemsCarrito(),
                    request.getDireccionEntrega(),
                    request.getInstruccionesEspeciales()
            );
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.warn("Error al crear pedido: {} ", e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.warn("Error interno al crear pedido: {} ",e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para obtener un pedido por su ID.
     * GET /api/pedidos/{idPedido}
     * @param idPedido ID del pedido a buscar (int).
     * @return ResponseEntity con el pedido encontrado y estado HTTP 200, o 404 si no existe.
     */
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> verDetallePedido(@PathVariable Integer idPedido) {
        logger.info("Recibida solicitud para ver detalle de pedido con ID: {}",idPedido);
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(idPedido);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK); // 200 OK
        } else {
            logger.warn("Pedido con ID {} no encontrado ",idPedido);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    /**
     * Endpoint para obtener todos los pedidos de un usuario.
     * GET /api/pedidos/usuario/{idUsuario}
     * @param idUsuario ID del usuario (int).
     * @return ResponseEntity con la lista de pedidos y estado HTTP 200.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Pedido>> verPedidosUsuario(@PathVariable int idUsuario) { // ID cambiado a int
        logger.info("Recibida solicitud para ver pedidos de usuario con ID: {} ",idUsuario);
        List<Pedido> pedidos = pedidoServicio.obtenerPedidosPorCliente(idUsuario);
        return new ResponseEntity<>(pedidos, HttpStatus.OK); // 200 OK
    }

    @PutMapping("/{idPedido}/estado")
    public ResponseEntity<Void> actualizarEstadoPedido(
            @PathVariable Integer idPedido,
            @RequestBody ActualizarEstadoPedidoRequest request) {
        logger.warn("Recibida solicitud para actualizar estado de pedido {} a {}",
                     idPedido,
                     request.getNuevoEstado());

        try {
            EstadoPedido nuevoEstado = EstadoPedido.valueOf(request.getNuevoEstado().toUpperCase());
            pedidoServicio.actualizarEstadoPedido(idPedido, nuevoEstado);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            logger.warn("Estado invalido o pedido no encontrado para ID {} : {}", idPedido, e.getMessage(), e);

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.warn("Error interno al actualizar estado de pedido {} : {} ", idPedido, e.getMessage(), e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para cancelar un pedido.
     * PUT /api/pedidos/{idPedido}/cancelar
     * @param idPedido ID del pedido a cancelar (int).
     * @param idUsuario ID del usuario que solicita la cancelacion (int).
     * @return ResponseEntity con estado HTTP 200 si es exitoso, o 400/404/500.
     */
    @PutMapping("/{idPedido}/cancelar")
    public ResponseEntity<Void> cancelarPedido(@PathVariable Integer idPedido, @RequestParam Integer idUsuario) { // IDs cambiados a int
        logger.warn("Recibida solicitud para cancelar pedido {} por usuario {} ",idPedido,idUsuario);
        try {
            pedidoServicio.cancelarPedido(idPedido, idUsuario);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (IllegalArgumentException e) {
            logger.warn("Error al cancelar pedido {} : {} ",idPedido, e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.warn("Error interno al cancelar pedido {} : {} ",idPedido,e.getMessage(),e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint para confirmar la entrega de un pedido.
     * PUT /api/pedidos/{idPedido}/confirmar-entrega
     * @param idPedido ID del pedido a confirmar (int).
     * @return ResponseEntity con estado HTTP 200 si es exitoso, o 404/500.
     */
    @PutMapping("/{idPedido}/confirmar-entrega")
    public ResponseEntity<Void> confirmarEntrega(@PathVariable int idPedido) { // ID cambiado a int
        logger.warn("Recibida solicitud para confirmar entrega de pedido  {} ",idPedido);
        try {
            pedidoServicio.confirmarEntrega(idPedido);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (IllegalArgumentException e) {
            logger.warn("Error al confirmar entrega de pedido  {} : {} ",idPedido,e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si el pedido no existe
        } catch (Exception e) {
            logger.warn("Error interno al confirmar entrega de pedido {}  : {} ",idPedido,e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
