package Model.Dominio.Pedidos;

import Dominio.Restaurantes.Dinero;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class ItemPedido {

    /**
     * Default constructor
     */
    public ItemPedido() {
    }

    /**
     * 
     */
    private Int id;

    /**
     * 
     */
    private Int idPlato;

    /**
     * 
     */
    private String nombrePlato;

    /**
     * 
     */
    private int cantidad;

    /**
     * 
     */
    public Dinero precioUnitario;

    /**
     * 
     */
    private Dinero subtotal;

    /**
     * 
     */
    public Pedido 1;



    /**
     * @return
     */
    public Dinero calcularSubtotal() {
        // TODO implement here
        return null;
    }

    /**
     * @param nuevaCantidad
     */
    public void actualizarCantidad(int nuevaCantidad) {
        // TODO implement here
    }

}