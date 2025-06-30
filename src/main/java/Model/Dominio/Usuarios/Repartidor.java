package Model.Dominio.Usuarios;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Repartidor extends Usuario {

    /**
     * Default constructor
     */
    public Repartidor() {
    }

    /**
     * 
     */
    private Int id;

    /**
     * 
     */
    private String idUsuarioPlataforma;

    /**
     * 
     */
    private String vehiculo;

    /**
     * 
     */
    private String licenciaConducir;

    /**
     * 
     */
    private boolean disponible;

    /**
     * 
     */
    private double calificacion;

    /**
     * @param disponible
     */
    public void marcarComoDisponible(boolean disponible) {
        // TODO implement here
    }

    /**
     * @param coordenadas
     */
    public void actualizarUbicacion(String coordenadas) {
        // TODO implement here
    }

    /**
     * @param idEntrega
     */
    public void aceptarEntrega(String idEntrega) {
        // TODO implement here
    }

    /**
     * @param puntuacion
     */
    public void recibirCalificacion(int puntuacion) {
        // TODO implement here
    }

}