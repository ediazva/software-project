// file: src/main/java/org/unsa/service/interfaces/IPedidoServicio.java
package org.unsa.model.service.Interfaces;

import org.unsa.model.dominio.pedidos.Pedido;
import org.unsa.model.dominio.pedidos.DatosPlatoPedido;
import org.unsa.model.dominio.pedidos.EstadoPedido;
import org.unsa.model.dominio.usuarios.Direccion;

import java.util.List;

/**
 * Interfaz para el servicio de gestion de pedidos.
 * Define las operaciones de negocio relacionadas con los pedidos.
 */
public interface IPedidoServicio {
    Pedido crearPedido(String idCliente, String idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales);
    Pedido obtenerPedidoPorId(String idPedido);
    List<Pedido> obtenerPedidosPorCliente(String idCliente);
    void actualizarEstadoPedido(String idPedido, EstadoPedido nuevoEstado);
    void asignarRepartidorAPedido(String idPedido, String idRepartidor);
    void cancelarPedido(String idPedido, String idUsuario);
    void confirmarEntrega(String idPedido);
}
