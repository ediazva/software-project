package org.unsa.model.dtos;

public class ActualizarEstadoPedidoRequest {
    private String nuevoEstado;

    public ActualizarEstadoPedidoRequest() {}

    public ActualizarEstadoPedidoRequest(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }
}
