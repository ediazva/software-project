package Model.Dominio.Pedidos;

import Dominio.Restaurantes.Dinero;
import Dominio.Restaurantes.Restaurante;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Pedido {

    /**
     * Default constructor
     */
    public Pedido() {
    }

    /**
     * 
     */
    private Int id;

    /**
     * 
     */
    private Int idCliente;

    /**
     * 
     */
    private Int idRestaurante;

    /**
     * 
     */
    private Date fechaHoraCreacion;

    /**
     * 
     */
    private EstadoPedido estado;

    /**
     * 
     */
    private Dinero montoTotal;

    /**
     * 
     */
    private String instruccionesEspeciales;

    /**
     * 
     */
    private Direccion direccionEntrega;

    /**
     * 
     */
    public Restaurante restaurante;

    /**
     * 
     */
    public List<ItemPedido> items;




    /**
     * 
     */
    public ItemPedido 1..*;

    /**
     * 
     */
    public Dinero 1;

    /**
     * 
     */
    public Direccion 1;





    /**
     * @param item
     */
    public void añadirItem(ItemPedido item) {
        // TODO implement here
    }

    /**
     * @param nuevoEstado
     */
    public void actualizarEstado(EstadoPedido nuevoEstado) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Dinero calcularMontoTotal() {
        // TODO implement here
        return null;
    }

    /**
     * @param idRepartidor
     */
    public void asignarRepartidor(void idRepartidor) {
        // TODO implement here
    }

    /**
     * @return
     */
    public void cancelar() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<itemPedido> obtenerItems() {
        // TODO implement here
        return null;
    }

}