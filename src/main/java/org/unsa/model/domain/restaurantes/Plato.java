// file: src/main/java/org/unsa/model/dominio/restaurantes/Plato.java
package org.unsa.model.domain.restaurantes;

import jakarta.persistence.*; // Importar todas las anotaciones de JPA
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un Plato ofrecido por un Restaurante.
 * Incluye atributos, constructores, getters, setters y metodos de comportamiento.
 */
@Entity // Marca esta clase como una entidad JPA
@Table(name = "platos") // Mapea esta entidad a la tabla "platos"
public class Plato {
    @Id // Marca 'idPlato' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el ID sera autoincremental por la DB
    private Integer idPlato;
    @Column(nullable = false) // El nombre no puede ser nulo
    private String nombre;
    private String descripcion;
    private boolean disponible; // Indica si el plato esta disponible para ser pedido

    @Embedded // Indica que Dinero es un componente incrustable
    @AttributeOverride(name = "valor", column = @Column(name = "precio_valor"))
    @AttributeOverride(name = "moneda", column = @Column(name = "precio_moneda"))
    private Dinero precio;




    @Transient // Indica que este campo no se mapeara a la base de datos
    private static final Logger logger = Logger.getLogger(Plato.class.getName());

    // Constantes para literales de String duplicados en toString()
    private static final String TO_STRING_PREFIX = "Plato{";
    private static final String ID_PLATO_FIELD = "idPlato="; // Cambiado para int
    private static final String NOMBRE_FIELD = ", nombre='";
    private static final String DESCRIPCION_FIELD = ", descripcion='";
    private static final String DISPONIBLE_FIELD = ", disponible=";
    private static final String PRECIO_FIELD = ", precio=";
    private static final String TO_STRING_SUFFIX = "}";
    private static final String SINGLE_QUOTE = "'";


    /**
     * Constructor vacío para la clase Plato.
     * Es necesario para JPA.
     */
    public Plato() {
        this.disponible = true; // Por defecto, disponible
        logger.info(() -> "Nuevo plato creado (constructor vacio). ID sera asignado por JPA.");
    }

    /**
     * Constructor para la clase Plato con parametros basicos.
     * El ID se pasa para simulacion, pero JPA lo gestionara en un entorno real.
     * @param idPlato Identificador unico del plato (int).
     * @param nombre Nombre del plato.
     * @param descripcion Descripcion del plato.
     * @param precio Objeto Dinero que representa el precio del plato.
     */
    public Plato(int idPlato, String nombre, String descripcion, Dinero precio) { // ID cambiado a int
        // En un entorno real con GenerationType.IDENTITY, el ID no se pasaria
        // en el constructor para nuevas entidades, se dejaria a la DB.
        // Lo mantenemos para compatibilidad con TestUsuarios.java por ahora.
        this.idPlato = idPlato; // Si idPlato es 0, JPA lo autogenerara
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.log(Level.SEVERE, () -> "Intento de crear Plato con nombre nulo o vacio.");
            throw new IllegalArgumentException("El nombre del plato no puede ser nulo o vacio.");
        }
        if (precio == null) {
            logger.log(Level.SEVERE, () -> "Intento de crear Plato con precio nulo.");
            throw new IllegalArgumentException("El precio del plato no puede ser nulo.");
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = true; // Por defecto, disponible
        logger.info(() -> "Plato creado (basico) con ID: " + this.idPlato + " y nombre: " + this.nombre);
    }

    /**
     * Constructor completo para la clase Plato.
     * El ID se pasa para simulacion, pero JPA lo gestionara en un entorno real.
     * @param idPlato Identificador unico del plato (int).
     * @param nombre Nombre del plato.
     * @param descripcion Descripcion del plato.
     * @param disponible Estado de disponibilidad del plato.
     * @param precio Objeto Dinero que representa el precio del plato.
     */
    public Plato(int idPlato, String nombre, String descripcion, boolean disponible, Dinero precio) { // ID cambiado a int
        // En un entorno real con GenerationType.IDENTITY, el ID no se pasaria
        // en el constructor para nuevas entidades, se dejaria a la DB.
        // Lo mantenemos para compatibilidad con TestUsuarios.java por ahora.
        this.idPlato = idPlato; // Si idPlato es 0, JPA lo autogenerara
        if (nombre == null || nombre.trim().isEmpty()) {
            logger.log(Level.SEVERE, () -> "Intento de crear Plato con nombre nulo o vacio.");
            throw new IllegalArgumentException("El nombre del plato no puede ser nulo o vacio.");
        }
        if (precio == null) {
            logger.log(Level.SEVERE, () -> "Intento de crear Plato con precio nulo.");
            throw new IllegalArgumentException("El precio del plato no puede ser nulo.");
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.precio = precio;
        logger.info(() -> "Plato creado (completo) con ID: " + this.idPlato + " y nombre: " + this.nombre);
    }

    // --- Getters ---
    // Mantener getId() para compatibilidad con llamadas existentes que esperen "id"
    public Integer getId() { // Ahora devuelve Integer
        return idPlato;
    }
    public void setId(Integer id) { // Ahora acepta Integer
        if (id != null && id <= 0) { // Permitir null para autogeneracion
            logger.log(Level.WARNING, () -> "Intento de establecer ID de plato invalido: " + id);
            throw new IllegalArgumentException("El ID del plato debe ser positivo o nulo para autogeneracion.");
        }
        this.idPlato = id;
        logger.info(() -> "ID de plato actualizado a: " + id);
    }
    public String getNombre(){
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public boolean isDisponible(){ // Getter para booleanos es 'is'
        return disponible;
    }

    public Dinero getPrecio(){
        return precio;
    }

    // --- Setters ---
    public void setIdPlato(int idPlato) { // Cambiado a int
        if (idPlato <= 0 && idPlato != 0) { // Permitir 0 para que JPA lo autogenere
            logger.log(Level.WARNING, () -> "Intento de establecer ID de plato invalido: " + idPlato);
            throw new IllegalArgumentException("El ID del plato debe ser positivo o 0 para autogeneracion.");
        }
        this.idPlato = idPlato;
        logger.info(() -> "ID de plato actualizado a: " + idPlato);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setPrecio(Dinero precio) {
        this.precio = precio;
    }


    // --- Implementación de Métodos de Comportamiento ---

    /**
     * Marca el plato como disponible o no disponible.
     * @param disponible true para disponible, false para no disponible.
     */
    @Transient // Este metodo no es persistente directamente
    public void marcarComoDisponible(boolean disponible) {
        this.disponible = disponible;
        logger.info(() -> "Plato '" + nombre + "' (ID: " + idPlato + ") marcado como " + (disponible ? "disponible" : "no disponible") + ".");
    }

    /**
     * Actualiza el precio del plato.
     * @param nuevoPrecio El nuevo objeto Dinero que representa el precio.
     */
    @Transient // Este metodo no es persistente directamente
    public void actualizarPrecio(Dinero nuevoPrecio) {
        this.precio = nuevoPrecio;
        logger.info(() -> "Precio de '" + nombre + "' (ID: " + idPlato + ") actualizado a " + nuevoPrecio.toString() + ".");
    }

    /**
     * Representacion en cadena del objeto Plato para depuracion.
     */
    @Override
    public String toString() {
        return TO_STRING_PREFIX +
                ID_PLATO_FIELD + idPlato +
                NOMBRE_FIELD + nombre + SINGLE_QUOTE +
                DESCRIPCION_FIELD + (descripcion != null ? descripcion : "N/A") + SINGLE_QUOTE +
                DISPONIBLE_FIELD + disponible +
                PRECIO_FIELD + precio +
                TO_STRING_SUFFIX;
    }

    /**
     * Compara si este objeto Plato es igual a otro objeto.
     * Dos objetos Plato se consideran iguales si tienen el mismo ID.
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return idPlato == plato.idPlato; // Comparacion de int ID
    }

    /**
     * Genera un codigo hash para este objeto Plato.
     * Es consistente con el metodo equals().
     * @return El codigo hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idPlato); // Hash basado en int ID
    }
}
