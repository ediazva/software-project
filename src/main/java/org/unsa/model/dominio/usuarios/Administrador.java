//file: src/main/java/org/unsa/model/User.java
package org.unsa.model.dominio.usuarios;

import Model.Dominio.Restaurantes.*;
public class Administrador extends Usuario {

    private String departamento;

    public Administrador(int id, String nombre , String email, String telefono, String departamento){
        super(id,nombre,email,telefono);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String setDepartamento(String departamento) {
        return departamento;
    }

    /*
    @param registrar un nuevo restaurante
    */
    public void registrarRestaurante(Restaurante restaurante) {
        System.out.println("Administrador"+ getnombre() + "esta registrando el restaurante " + restaurante.getNombre());
    }
    /*
    @param //idRestaurante
    @param //idPlato
    */
    public void añadirPlatoARestaurante(String idRestaurante, Plato plato) {
        System.out.println("Admnistrador" + getNombre() + "añadio el plato "+ plato.getNombre() +
                "al restaurante con ID "+ idRestaurante);
    }

    public void eliminarPlatoDeRestaurante(String idRestaurante, String idPlato) {
        // TODO implement here
    }

    public void actualizarHorarioRestaurante(String idRestaurante, HorarioAtencion horario) {
        // TODO implement here
    }

    public void añadirPlatoARestaurante(int id, Plato plato) {
        // TODO implement here
    }

    public void eliminarPlatoDeRestaurante(int idRestaurante, int idPlato) {
        // TODO implement here
    }

    public void actualizarHorarioRestaurante(int idRestaurante, HorarioAtencion horario) {
        // TODO implement here
    }
}

