package Model.Presentacion.Controlador;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Pedidos {

    /**
     * Default constructor
     */
    public Pedidos() {
    }

    /**
     * 
     */
    public IPedidoServicio servicioPedido;

    /**
     * @param idUsuario 
     * @param model 
     * @return
     */
    public String verPedidosUsuario(Long idUsuario, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPedido 
     * @param model 
     * @return
     */
    public String verDetallePedido(Long idPedido, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idUsuario 
     * @param carrito 
     * @param model 
     * @return
     */
    public String crearPedido(Long idUsuario, List<ItemCarrito> carrito, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPedido 
     * @param nuevoEstado 
     * @return
     */
    public String actualizarEstadoPedido(Long idPedido, String nuevoEstado) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPedido 
     * @param idUsuario 
     * @return
     */
    public String cancelarPedido(Long idPedido, Long idUsuario) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPedido 
     * @return
     */
    public String confirmarEntrega(Long idPedido) {
        // TODO implement here
        return "";
    }

}