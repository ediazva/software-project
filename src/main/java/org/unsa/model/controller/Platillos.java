package org.unsa.model.controller;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Platillos {

    /**
     * Default constructor
     */
    public Platillos() {
    }

    /**
     * 
     */
    public IPlatilloServicio servicioPlatillo;

    /**
     * @param idRestaurante 
     * @param platillo 
     * @param bindingResult 
     * @param model 
     * @return
     */
    public String crearPlatillo(Long idRestaurante, PlatilloModel platillo, BindingResult bindingResult, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idRestaurante 
     * @param model 
     * @return
     */
    public String listarPlatillosPorRestaurante(Long idRestaurante, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPlatillo 
     * @param model 
     * @return
     */
    public String verDetallePlatillo(Long idPlatillo, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPlatillo 
     * @param platillo 
     * @param bindingResult 
     * @param model 
     * @return
     */
    public String actualizarPlatillo(Long idPlatillo, PlatilloModel platillo, BindingResult bindingResult, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idPlatillo 
     * @return
     */
    public String eliminarPlatillo(Long idPlatillo) {
        // TODO implement here
        return "";
    }

}