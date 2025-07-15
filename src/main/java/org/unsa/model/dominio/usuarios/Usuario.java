// file: src/main/java/org/unsa/model/dominio/usuarios/Usuario.java
package org.unsa.model.dominio.usuarios;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase que representa un Usuario base en el sistema SueldoMinimo web.
 * Demuestra el estilo "Things" (POO) con encapsulacion y responsabilidades claras.
 */
public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    private  boolean activo;

    private static final Logger logger = Logger.getLogger(Usuario.class.getName());
    //constructor vacio
    public Usuario(){
        this.id = UUID.randomUUID().toString();
        this.fechaRegistro = new Date();
        this.activo = true;
        if(logger.isLoggable(Level.INFO)) {
            logger.info("Usuario creado(vacio) con ID" + this.id);
        }
    }

    /**
     * Constructor para la clase Usuario con parametros iniciales.
     * @param id Identificador unico del usuario.
     * @param nombre Nombre completo del usuario.
     * @param email Correo electronico del usuario.
     * @param telefono Numero de telefono del usuario.
     */

    public Usuario(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        if(email==null || !email.contains("@")){
            logger.log(Level.SEVERE, "El email no es valido");
            throw new IllegalArgumentException("El email no es valido");
        }
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = new Date();
        this.activo = true;
        if(logger.isLoggable(Level.INFO)) {
            logger.info("Usuario creado (parametros) con ID" + this.id + "y nombre:  " + this.nombre);
        }
    }

    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            String logMessage = String.format("Intento de establecer email invalido para usuario %s: %s", this.id, email);
            logger.log(Level.WARNING,logMessage);
            throw new IllegalArgumentException("El email es invalido.");
        }
        this.email = email;
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Email actualizado para usuario " + this.id + " a: " + email);
        }
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    //metodos implementados

    public void actualizarDatosContacto(String email, String telefono) {

        try{
            setEmail(email);
            this.telefono = telefono;
            if (logger.isLoggable(Level.INFO)) {
                logger.info("Datos de contacto actualizados para el usuario " + this.id);
            }
        }catch (IllegalArgumentException e){
            logger.log(Level.SEVERE, String.format("Fallo al actualizar datos de contacto para usuario %s", this.id), e);
        }
    }
    public void activarCuenta() {
        this.activo = true;
        if(logger.isLoggable(Level.INFO)) {
            logger.info("Cuenta de " + this.nombre + "activada");
        }
    }

    public void desactivarCuenta() {
        this.activo = false;
        if(logger.isLoggable(Level.INFO)) {
            logger.info("Cuenta de " + this.nombre + "desactivada");
        }
    }

    /**
     * Representacion en cadena del objeto Usuario para depuracion.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", activo=" + activo +
                '}';
    }
}