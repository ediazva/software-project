// file: src/main/java/org/unsa/dto/usuarios/CrearUsuarioRequest.java
package org.unsa.dto.usuarios;

/**
 * DTO para la solicitud de creacion de un nuevo usuario.
 */
public class CrearUsuarioRequest {
    private String nombre;
    private String email;
    private String telefono;
    private String rol; // Podria ser "CLIENTE", "ADMINISTRADOR", "REPARTIDOR"

    public CrearUsuarioRequest() {}

    public CrearUsuarioRequest(String nombre, String email, String telefono, String rol) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
