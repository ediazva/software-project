package Model.Dominio.Pedidos;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Entrega {

    /**
     * Default constructor
     */
    public Entrega() {
    }

    /**
     * 
     */
    private Int id;

    /**
     * 
     */
    private Int idPedido;

    /**
     * 
     */
    private Int idRepartidor;

    /**
     * 
     */
    private EstadoEntrega estado;

    /**
     * 
     */
    private Date fechaHoraAsignacion;

    /**
     * 
     */
    private Date fechaHoraRecojo;

    /**
     * 
     */
    private Date fechaHoraEntrega;

    /**
     * 
     */
    private String ubicacionActualRepartidor;






    /**
     * @param idRepartidor
     */
    public void asignarRepartidor(String idRepartidor) {
        // TODO implement here
    }

    /**
     * @param nuevoEstado
     */
    public void actualizarEstado(EstadoEntrega nuevoEstado) {
        // TODO implement here
    }

    /**
     * 
     */
    public void registrarRecojo() {
        // TODO implement here
    }

    /**
     * @param coordenadas
     */
    public void actualizarUbicacion(String coordenadas) {
        // TODO implement here
    }

}