package Model.Servicios;

import Dominio.Pedidos.EstadoEntrega;
import Dominio.Pedidos.Pedido;
import Dominio.Pedidos.Direccion;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class CatalogoRestaurantesService {

    /**
     * Default constructor
     */
    public CatalogoRestaurantesService() {
    }


    /**
     * @param texto 
     * @return
     */
    public List<Restaurante> buscarPorTexto(String texto) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Restaurante> obtenerTodos() {
        // TODO implement here
        return null;
    }

    /**
     * @param idPedido 
     * @param idRepartidor
     */
    public void asignarRepartidorAPedido(Int idPedido, Int idRepartidor) {
        // TODO implement here
    }

    /**
     * @param idEntrega 
     * @param nuevoEstado
     */
    public void actualizarEstadoEntrega(Int idEntrega, EstadoEntrega nuevoEstado) {
        // TODO implement here
    }

    /**
     * @param idCliente 
     * @param idRestaurante 
     * @param itemsCarrito 
     * @param direccionEntrega 
     * @return
     */
    public Pedido crearNuevoPedido(Int idCliente, Int idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega) {
        // TODO implement here
        return null;
    }

}