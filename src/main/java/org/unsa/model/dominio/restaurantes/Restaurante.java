//file: src/main/java/org/unsa/model/dominio/restaurantes/Restaurante.java

package org.unsa.model.dominio.restaurantes;

import org.unsa.model.dominio.usuarios.Administrador;

import java.util.List;

public class Restaurante {
    private String idRestaurante;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private TipoCocina tipoCocina;
    private double calificacionPromedio;
    private HorarioAtencion horarioAtencion;
    private List<Plato> platos;
    private Administrador admin;

    public String getId(){
        return idRestaurante;
    }
    public String getNombre(){
        return nombre;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getTelefono(){
        return telefono;
    }
    public TipoCocina getTipoCocina(){
        return tipoCocina;
    }
    public List<Plato> getPlatos(){
        return platos;
    }
    public Administrador getAdmin() {
        return admin;
    }
    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }



    public void addPlato(Plato plato) {
        if (plato != null && !platos.contains(plato)) {
            platos.add(plato);
        }
    }


    public void eliminarPlato(String idRestaurante) {
        platos.removeIf(p -> p.getId().equals(idRestaurante));
    }

    public void actualizarHorario(HorarioAtencion horario) {
        this.horarioAtencion = horario;
    }

    public boolean estaDisponible() {
        return !platos.isEmpty() && horarioAtencion != null;
    }
}