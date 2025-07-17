// file: src/main/java/org/unsa/dto/pedidos/ActualizarEstadoPedidoRequest.java
package org.unsa.dto.pedidos;

/**
 * DTO para la solicitud de actualizacion del estado de un pedido.
 */
public class ActualizarEstadoPedidoRequest {
    private String nuevoEstado; // El estado como String, se convertira a EstadoPedido en el servicio

    public ActualizarEstadoPedidoRequest() {}

    public ActualizarEstadoPedidoRequest(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    // Getter y Setter
    public String getNuevoEstado() { return nuevoEstado; }
    public void setNuevoEstado(String nuevoEstado) { this.nuevoEstado = nuevoEstado; }
}
