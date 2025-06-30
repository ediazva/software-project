package Model.Dominio.Restaurantes;

import Dominio.Usuarios.Administrador;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Restaurante {

    /**
     * Default constructor
     */
    public Restaurante() {
    }

    /**
     * 
     */
    private Int id;

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private String direccion;

    /**
     * 
     */
    private String telefono;

    /**
     * 
     */
    private TipoCocina tipoCocina;

    /**
     * 
     */
    private double calificacionPromedio;

    /**
     * 
     */
    private HoracioAtencion horarioAtencion;

    /**
     * 
     */
    private List<Plato> platos;

    /**
     * 
     */
    public Administrador admin;

    /**
     * 
     */
    public Administrador 1;

    /**
     * 
     */
    public Plato 1..*;

    /**
     * 
     */
    public HorarioAtencion 0..*;


    /**
     * 
     */
    public Plato 1..*;

    /**
     * 
     */
    public HorarioAtencion 1;

    /**
     * @param plato
     */
    public void añadirPlato(Plato plato) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void eliminarPlato(Int id) {
        // TODO implement here
    }

    /**
     * @param horario
     */
    public void actualizarHorario(HorarioAtencion horario) {
        // TODO implement here
    }

    /**
     * @return
     */
    public boolean estaDisponible() {
        // TODO implement here
        return false;
    }

}