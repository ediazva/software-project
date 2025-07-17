// file: src/main/java/org/unsa/controller/pedidos/PedidosController.java
package org.unsa.model.controller;

import org.unsa.model.dominio.pedidos.Pedido;
import org.unsa.model.dominio.pedidos.EstadoPedido;
import org.unsa.dto.pedidos.CrearPedidoRequest;
import org.unsa.dto.pedidos.ActualizarEstadoPedidoRequest;
import org.unsa.service.interfaces.IPedidoServicio; // Inyectar la interfaz

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador REST para la gestion de pedidos.
 * Expone endpoints para crear, consultar y actualizar pedidos.
 */
@RestController
@RequestMapping("/api/pedidos") // Ruta base para todos los endpoints de pedidos
public class PedidosController {

    private static final Logger logger = Logger.getLogger(PedidosController.class.getName());

    private final IPedidoServicio pedidoServicio; // Inyectar la interfaz del servicio

    @Autowired // Inyeccion de dependencia
    public PedidosController(IPedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
        logger.info(() -> "PedidosController inicializado.");
    }

    /**
     * Endpoint para crear un nuevo pedido.
     * POST /api/pedidos
     * @param request El DTO con los datos para crear el pedido.
     * @return ResponseEntity con el pedido creado y estado HTTP 201.
     */
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody CrearPedidoRequest request) {
        logger.info(() -> "Recibida solicitud para crear pedido para cliente: " + request.getIdCliente());
        try {
            // Validaciones basicas del DTO
            if (request.getIdCliente() <= 0 || // Validacion para int ID
                    request.getIdRestaurante() <= 0 || // Validacion para int ID
                    request.getItemsCarrito() == null || request.getItemsCarrito().isEmpty() ||
                    request.getDireccionEntrega() == null) {
                logger.warning(() -> "Datos de creacion de pedido incompletos o invalidos.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Pedido nuevoPedido = pedidoServicio.crearPedido(
                    request.getIdCliente(),
                    request.getIdRestaurante(),
                    request.getItemsCarrito(),
                    request.getDireccionEntrega(),
                    request.getInstruccionesEspeciales()
            );
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, () -> "Error al crear pedido: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        } catch (Exception e) {
            logger.log(Level.SEVERE, () -> "Error interno al crear pedido: " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    /**
     * Endpoint para obtener un pedido por su ID.
     * GET /api/pedidos/{idPedido}
     * @param idPedido ID del pedido a buscar (int).
     * @return ResponseEntity con el pedido encontrado y estado HTTP 200, o 404 si no existe.
     */
    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> verDetallePedido(@PathVariable int idPedido) { // ID cambiado a int
        logger.info(() -> "Recibida solicitud para ver detalle de pedido con ID: " + idPedido);
        Pedido pedido = pedidoServicio.obtenerPedidoPorId(idPedido);
        if (pedido != null) {
            return new ResponseEntity<>(pedido, HttpStatus.OK); // 200 OK
        } else {
            logger.warning(() -> "Pedido con ID " + idPedido + " no encontrado.");
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
        logger.info(() -> "Recibida solicitud para ver pedidos de usuario con ID: " + idUsuario);
        List<Pedido> pedidos = pedidoServicio.obtenerPedidosPorCliente(idUsuario);
        return new ResponseEntity<>(pedidos, HttpStatus.OK); // 200 OK
    }

    /**
     * Endpoint para actualizar el estado de un pedido.
     * PUT /api/pedidos/{idPedido}/estado
     * @param idPedido ID del pedido a actualizar (int).
     * @param request DTO con el nuevo estado.
     * @return ResponseEntity con estado HTTP 200 si es exitoso, o 400/404/500.
     */
    @PutMapping("/{idPedido}/estado")
    public ResponseEntity<Void> actualizarEstadoPedido(@PathVariable int idPedido, @RequestBody ActualizarEstadoPedidoRequest request) { // ID cambiado a int
        logger.info(() -> "Recibida solicitud para actualizar estado de pedido " + idPedido + " a: " + request.getNuevoEstado());
        try {
            EstadoPedido nuevoEstado = EstadoPedido.valueOf(request.getNuevoEstado().toUpperCase()); // Convertir String a Enum
            pedidoServicio.actualizarEstadoPedido(idPedido, nuevoEstado);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, () -> "Estado invalido o pedido no encontrado para ID " + idPedido + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        } catch (Exception e) {
            logger.log(Level.SEVERE, () -> "Error interno al actualizar estado de pedido " + idPedido + ": " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
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
    public ResponseEntity<Void> cancelarPedido(@PathVariable int idPedido, @RequestParam int idUsuario) { // IDs cambiados a int
        logger.info(() -> "Recibida solicitud para cancelar pedido " + idPedido + " por usuario " + idUsuario);
        try {
            pedidoServicio.cancelarPedido(idPedido, idUsuario);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, () -> "Error al cancelar pedido " + idPedido + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.log(Level.SEVERE, () -> "Error interno al cancelar pedido " + idPedido + ": " + e.getMessage(), e);
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
        logger.info(() -> "Recibida solicitud para confirmar entrega de pedido " + idPedido);
        try {
            pedidoServicio.confirmarEntrega(idPedido);
            return new ResponseEntity<>(HttpStatus.OK); // 200 OK
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, () -> "Error al confirmar entrega de pedido " + idPedido + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si el pedido no existe
        } catch (Exception e) {
            logger.log(Level.SEVERE, () -> "Error interno al confirmar entrega de pedido " + idPedido + ": " + e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
