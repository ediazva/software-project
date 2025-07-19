// file: src/main/java/org/unsa/dto/platillos/CrearPlatilloRequest.java
package org.unsa.model.dtos;

import org.unsa.model.domain.restaurantes.Dinero;

/**
 * DTO para la solicitud de creacion de un nuevo platillo.
 */
public class CrearPlatilloRequest {
    private String nombre;
    private String descripcion;
    private Dinero precio; // Usar el objeto Dinero

    public CrearPlatilloRequest() {}

    public CrearPlatilloRequest(String nombre, String descripcion, Dinero precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Dinero getPrecio() { return precio; }
    public void setPrecio(Dinero precio) { this.precio = precio; }
}
