// file: src/main/java/org/unsa/dto/platillos/ActualizarPlatilloRequest.java
package org.unsa.dto.platillos;

import org.unsa.model.dominio.restaurantes.Dinero;

/**
 * DTO para la solicitud de actualizacion de un platillo existente.
 */
public class ActualizarPlatilloRequest {
    private String nombre;
    private String descripcion;
    private Dinero precio;
    private boolean disponible;

    public ActualizarPlatilloRequest() {}

    public ActualizarPlatilloRequest(String nombre, String descripcion, Dinero precio, boolean disponible) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Dinero getPrecio() { return precio; }
    public void setPrecio(Dinero precio) { this.precio = precio; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
