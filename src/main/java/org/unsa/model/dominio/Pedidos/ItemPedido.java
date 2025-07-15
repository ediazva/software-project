package org.unsa.model.dominio.Pedidos;

import org.unsa.model.dominio.restaurantes.Dinero;

public class ItemPedido {
    private int id;
    private int idPlato;
    private String nombrePlato;
    private int cantidad;
    public Dinero precioUnitario;
    private Dinero subtotal;

    public Dinero calcularSubtotal() {
        // TODO implement here
        return null;
    }

    public void actualizarCantidad(int nuevaCantidad) {
        // TODO implement here
    }

}