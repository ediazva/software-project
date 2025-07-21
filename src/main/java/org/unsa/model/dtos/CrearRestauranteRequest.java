// file: src/main/java/org/unsa/model/dtos/CrearRestauranteRequest.java
package org.unsa.model.dtos;

import org.unsa.model.domain.restaurantes.TipoCocina;
import org.unsa.model.domain.usuarios.Direccion; // Asegúrate de la ruta de Direccion
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CrearRestauranteRequest {
    @NotBlank(message = "El nombre del restaurante no puede estar vacío.")
    private String nombre;
    private String descripcion;
    @NotNull(message = "La dirección no puede ser nula.")
    private Direccion direccion; // Asumiendo que Direccion es un objeto de valor/embeddable
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos.")
    private String telefono;
    @NotNull(message = "El tipo de cocina no puede ser nulo.")
    private TipoCocina tipoCocina;

    // Constructores, Getters y Setters
    public CrearRestauranteRequest() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public TipoCocina getTipoCocina() { return tipoCocina; }
    public void setTipoCocina(TipoCocina tipoCocina) { this.tipoCocina = tipoCocina; }
}