// file: src/main/java/org/unsa/dto/restaurantes/ActualizarRestauranteRequest.java
package org.unsa.model.dtos;

/**
 * DTO para la solicitud de actualizacion de un restaurante existente.
 */
public class ActualizarRestauranteRequest {
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipoCocina;

    public ActualizarRestauranteRequest() {}

    public ActualizarRestauranteRequest(String nombre, String direccion, String telefono, String tipoCocina) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCocina = tipoCocina;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getTipoCocina() { return tipoCocina; }
    public void setTipoCocina(String tipoCocina) { this.tipoCocina = tipoCocina; }
}
