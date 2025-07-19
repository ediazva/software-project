// file: src/main/java/org/unsa/dto/usuarios/ActualizarUsuarioRequest.java
package org.unsa.model.dtos;

/**
 * DTO para la solicitud de actualizacion de un usuario existente.
 */
public class ActualizarUsuarioRequest {
    private String nombre;
    private String email;
    private String telefono;
    private boolean activo;

    public ActualizarUsuarioRequest() {}

    public ActualizarUsuarioRequest(String nombre, String email, String telefono, boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
