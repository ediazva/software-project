package org.unsa.model.controller;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Usuario {

    /**
     * Default constructor
     */
    public Usuario() {
    }

    /**
     * 
     */
    public IUsuarioServicio usuarioServicio;

    /**
     * @param idUsuario 
     * @param model 
     * @return
     */
    public String mostrarPerfil(Long idUsuario, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param idUsuario 
     * @param usuario 
     * @param bindingResult 
     * @param model 
     * @return
     */
    public String actualizarPerfil(Long idUsuario, UsuarioModel usuario, BindingResult bindingResult, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @param credenciales 
     * @param bindingResult 
     * @param model 
     * @return
     */
    public String iniciarSesion(CredencialesModel credenciales, BindingResult bindingResult, Model model) {
        // TODO implement here
        return "";
    }

    /**
     * @return
     */
    public String cerrarSesion() {
        // TODO implement here
        return "";
    }

    /**
     * @param usuario 
     * @param bindingResult 
     * @param model 
     * @return
     */
    public String registrarUsuario(UsuarioModel usuario, BindingResult bindingResult, Model model) {
        // TODO implement here
        return "";
    }

}