package org.unsa.model.dominio.restaurantes;

public class Plato {
    private String idPlato;
    private String nombre;
    private String descripcion;
    private boolean disponible;
    private Dinero precio;

    public String getId(){
        return idPlato;
    }

    public boolean estaDisponible(){
        return disponible;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public Dinero getPrecio(){
        return precio;
    }
    public String getNombre(){
        return nombre;
    }



    public void marcarComoDisponible(boolean disponible) {
        // TODO implement here
    }

    public void actualizarPrecio(Dinero nuevoPrecio) {
        // TODO implement here
    }
}