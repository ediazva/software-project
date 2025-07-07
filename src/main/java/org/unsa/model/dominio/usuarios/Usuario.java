// file: src/main/java/org/unsa/model/User.java
package org.unsa.model.dominio.usuarios;

import java.util.Date;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    private  boolean activo;

    //constructor
    public Usuario(){
       this.fechaRegistro = new Date();
       this.activo = true;
    }

    public Usuario(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = new Date();
        this.activo = true;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
        System.out.println("Datos actualizados" + this.nombre);
    }
    public void activarCuenta() {
        this.activo = true;
        System.out.println("Cuenta de " + this.nombre + "activada");
    }

    public void desactivarCuenta() {
        this.activo = false;
        System.out.println("Cuenta de " + this.nombre + "desactivada");
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