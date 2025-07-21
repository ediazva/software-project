// file: src/main/java/org/unsa/model/dtos/CrearUsuarioRequest.java
package org.unsa.model.dtos;

import org.unsa.model.domain.usuarios.Direccion;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * DTO para la creacion de un nuevo usuario.
 */
public class CrearUsuarioRequest {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos") // Ejemplo para 9 dígitos
    private String telefono;

    // Puedes añadir mas campos si tu usuario tiene otros atributos
    // private String tipoUsuario; // "CLIENTE", "REPARTIDOR", "ADMIN"

    // Si la dirección se crea con el usuario principal
    @NotNull(message = "La dirección principal no puede ser nula")
    private Direccion direccionPrincipal;


    // Constructor, Getters y Setters
    public CrearUsuarioRequest() {}

    public CrearUsuarioRequest(String nombre, String apellido, String email, String telefono, Direccion direccionPrincipal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.direccionPrincipal = direccionPrincipal;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Direccion getDireccionPrincipal() { return direccionPrincipal; }
    public void setDireccionPrincipal(Direccion direccionPrincipal) { this.direccionPrincipal = direccionPrincipal; }

    @Override
    public String toString() {
        return "CrearUsuarioRequest{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}