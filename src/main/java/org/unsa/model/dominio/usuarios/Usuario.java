// file: src/main/java/org/unsa/model/User.java
package org.unsa.model.dominio.usuarios;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    private  boolean activo;

    private static final Logger logger = Logger.getLogger(Usuario.class.getName());
    //constructor
    public Usuario(){
        this.id = UUID.randomUUID().toString();
        this.fechaRegistro = new Date();
        this.activo = true;
        if(logger.isLoggable(Level.INFO)) {
            logger.info("Usuario creado(vacio) con ID" + this.id);
        }
    }

    public Usuario(String id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
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
        this.email = email;
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
        this.email = email;
        this.telefono = telefono;
        if (logger.isLoggable(Level.INFO)) {
            logger.info("Datos de contacto actualizados para el usuario " + this.id);
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

    //depuracion
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