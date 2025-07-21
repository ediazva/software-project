// file: src/main/java/org/unsa/model/dtos/ActualizarUsuarioRequest.java
package org.unsa.model.dtos;

import org.unsa.model.domain.usuarios.Direccion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

/**
 * DTO para la actualizacion de un usuario existente.
 * Los campos son opcionales ya que no todos se actualizaran siempre.
 */
public class ActualizarUsuarioRequest {
    private String nombre;
    private String apellido;

    @Email(message = "El formato del email no es válido")
    private String email;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    private Direccion direccionPrincipal; // Para actualizar la dirección

    private Boolean activo; // Para activar/desactivar el usuario

    // Constructor, Getters y Setters
    public ActualizarUsuarioRequest() {}

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

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return "ActualizarUsuarioRequest{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", activo=" + activo +
                '}';
    }
}