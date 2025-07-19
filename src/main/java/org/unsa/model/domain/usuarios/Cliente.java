// file: src/main/java/org/unsa/model/dominio/usuarios/Cliente.java
package org.unsa.model.domain.usuarios;

import jakarta.persistence.Entity; // Importar la anotacion Entity
import jakarta.persistence.PrimaryKeyJoinColumn; // Para herencia JOINED
import jakarta.persistence.Transient; // Para campos no persistentes

import java.util.logging.Logger;

/**
 * Clase que representa a un Cliente en el sistema SueldoMinimo App.
 * Extiende de Usuario y puede incluir atributos especificos de cliente.
 */
@Entity // Marca esta clase como una entidad JPA
@PrimaryKeyJoinColumn(name = "id") // Especifica la columna de union con la tabla padre
public class Cliente extends Usuario {

    private String preferenciasAlimentarias; // Ejemplo de atributo especifico de Cliente
    @Transient // Indica que este campo no se mapeara a la base de datos
    private static final Logger logger = Logger.getLogger(Cliente.class.getName());

    /**
     * Constructor vacío para JPA.
     */
    public Cliente() {
        super();
    }

    /**
     * Constructor para la clase Cliente.
     * @param id Identificador unico del cliente (int).
     * @param nombre Nombre completo del cliente.
     * @param email Correo electronico del cliente.
     * @param telefono Numero de telefono del cliente.
     * @param preferenciasAlimentarias Preferencias dieteticas o alimentarias del cliente (puede ser nulo).
     */
    public Cliente(int id, String nombre, String email, String telefono, String preferenciasAlimentarias) { // ID cambiado a int
        super(id, nombre, email, telefono); // Llama al constructor de la clase padre Usuario
        this.preferenciasAlimentarias = preferenciasAlimentarias;
        logger.info(() -> "Cliente creado con ID: " + getId() + ", nombre: " + getNombre());
    }

    // --- Getters y Setters especificos de Cliente ---
    public String getPreferenciasAlimentarias() {
        return preferenciasAlimentarias;
    }

    public void setPreferenciasAlimentarias(String preferenciasAlimentarias) {
        this.preferenciasAlimentarias = preferenciasAlimentarias;
        logger.info(() -> "Preferencias alimentarias actualizadas para cliente " + getId());
    }

    /**
     * Representacion en cadena del objeto Cliente para depuracion.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() + // Cambiado para int
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", fechaRegistro=" + getFechaRegistro() +
                ", activo=" + isActivo() +
                ", preferenciasAlimentarias='" + (preferenciasAlimentarias != null ? preferenciasAlimentarias : "N/A") + '\'' +
                '}';
    }
}
