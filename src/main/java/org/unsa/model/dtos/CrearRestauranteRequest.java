// file: src/main/java/org/unsa/dto/restaurantes/CrearRestauranteRequest.java
package org.unsa.dto.restaurantes;

/**
 * DTO para la solicitud de creacion de un nuevo restaurante.
 */
public class CrearRestauranteRequest {
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipoCocina; // El tipo de cocina como String, se convertira a TipoCocina en el servicio

    public CrearRestauranteRequest() {}

    public CrearRestauranteRequest(String nombre, String direccion, String telefono, String tipoCocina) {
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
