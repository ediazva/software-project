// file: src/main/java/org/unsa/model/dominio/usuarios/Administrador.java
package org.unsa.model.dominio.usuarios;

import jakarta.persistence.Entity; // Importar la anotacion Entity
import jakarta.persistence.PrimaryKeyJoinColumn; // Para herencia JOINED
import jakarta.persistence.Transient; // Para campos no persistentes
import org.unsa.model.dominio.restaurantes.HorarioAtencion;
import org.unsa.model.dominio.restaurantes.Plato;
import org.unsa.model.dominio.restaurantes.Restaurante;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un Administrador en el sistema.
 * Extiende de Usuario, demostrando Herencia (parte del estilo "Things").
 */
@Entity // Marca esta clase como una entidad JPA
@PrimaryKeyJoinColumn(name = "id") // Especifica la columna de union con la tabla padre
public class Administrador extends Usuario {
    @Transient // Indica que este campo no se mapeara a la base de datos
    private static final Logger logger = Logger.getLogger(Administrador.class.getName());

    // Constantes para literales de String duplicados en toString() y logs
    private static final String TO_STRING_PREFIX = "Administrador{";
    private static final String DEPARTAMENTO_FIELD = ", departamento='";
    private static final String TO_STRING_SUFFIX = "}";
    private static final String SINGLE_QUOTE = "'";
    private static final String LOG_PREFIX_ADMIN = "Administrador ";

    private String departamento;

    /**
     * Constructor vacío para JPA.
     */
    public Administrador() {
        super();
    }

    /**
     * Constructor para la clase Administrador.
     * @param id Identificador unico del administrador.
     * @param nombre Nombre completo del administrador.
     * @param email Correo electronico del administrador.
     * @param telefono Numero de telefono del administrador.
     * @param departamento Departamento al que pertenece el administrador.
     */
    public Administrador(int id, String nombre , String email, String telefono, String departamento){ // ID cambiado a int
        super(id,nombre,email,telefono);
        this.departamento = departamento;
        logger.info(() -> LOG_PREFIX_ADMIN + getNombre() + " creado con ID: " + getId());
    }

    // --- Getters y Setters específicos de Administrador ---
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    // --- Métodos de gestión de Restaurantes (parte del estilo "Things", responsabilidades del Admin) ---

    /**
     * Registra un nuevo restaurante en el sistema.
     * @param restaurante El objeto Restaurante a registrar.
     */
    @Transient // Este metodo no es persistente directamente, es logica de negocio
    public void registrarRestaurante(Restaurante restaurante) {
        if (restaurante == null) {
            logger.log(Level.SEVERE, () -> "Intento de registrar un restaurante nulo.");
            throw new IllegalArgumentException("El restaurante a registrar no puede ser nulo.");
        }
        logger.info(() -> LOG_PREFIX_ADMIN + getNombre() + " está registrando el restaurante: " + restaurante.getNombre() + " (ID: " + restaurante.getId() + ")");
        // TODO: En una aplicacion real, esto llamaria a un servicio/repositorio para guardar el restaurante.
    }

    /**
     * Añade un plato a un restaurante específico.
     * @param idRestaurante ID del restaurante (int).
     * @param plato El objeto Plato a añadir.
     */
    @Transient // Este metodo no es persistente directamente
    public void añadirPlatoARestaurante(int idRestaurante, Plato plato) { // ID cambiado a int
        if (idRestaurante <= 0) {
            logger.log(Level.SEVERE, () -> "ID de restaurante invalido al añadir plato: " + idRestaurante);
            throw new IllegalArgumentException("El ID del restaurante debe ser positivo.");
        }
        if (plato == null) {
            logger.log(Level.SEVERE, () -> "Plato nulo al añadir a restaurante " + idRestaurante);
            throw new IllegalArgumentException("El plato no puede ser nulo.");
        }
        logger.info(() -> LOG_PREFIX_ADMIN + getNombre() + " añadió el plato '" + plato.getNombre() +
                "' al restaurante con ID " + idRestaurante);
        // TODO: En una aplicacion real, esto llamaria a un servicio/repositorio para añadir el plato
    }

    /**
     * Elimina un plato de un restaurante específico.
     * @param idRestaurante ID del restaurante (int).
     * @param idPlato ID del plato a eliminar (int).
     */
    @Transient // Este metodo no es persistente directamente
    public void eliminarPlatoDeRestaurante(int idRestaurante, int idPlato) { // IDs cambiados a int
        if (idRestaurante <= 0 || idPlato <= 0) {
            logger.log(Level.SEVERE, () -> "IDs invalidos al eliminar plato: Restaurante ID " + idRestaurante + ", Plato ID " + idPlato);
            throw new IllegalArgumentException("Los IDs de restaurante y plato deben ser positivos.");
        }
        logger.info(() -> LOG_PREFIX_ADMIN + getNombre() + " eliminó el plato (ID): " + idPlato +
                " del restaurante (ID): " + idRestaurante);
        // TODO: En una aplicacion real, esto llamaria a un servicio/repositorio para eliminar el plato
    }

    /**
     * Actualiza el horario de atención de un restaurante específico.
     * @param idRestaurante ID del restaurante (int).
     * @param horario El nuevo objeto HorarioAtencion.
     */
    @Transient // Este metodo no es persistente directamente
    public void actualizarHorarioRestaurante(int idRestaurante, HorarioAtencion horario) { // ID cambiado a int
        if (idRestaurante <= 0) {
            logger.log(Level.SEVERE, () -> "ID de restaurante invalido al actualizar horario: " + idRestaurante);
            throw new IllegalArgumentException("El ID del restaurante debe ser positivo.");
        }
        if (horario == null) {
            logger.log(Level.SEVERE, () -> "Horario nulo al actualizar horario de restaurante " + idRestaurante);
            throw new IllegalArgumentException("El horario no puede ser nulo.");
        }
        logger.info(() -> LOG_PREFIX_ADMIN + getNombre() + " está actualizando el horario del restaurante (ID): " +
                idRestaurante + " a: " + horario.getDiaSemana() + " de " + horario.getHoraApertura() + " a " + horario.getHoraCierre());
        // TODO: En una aplicacion real, esto llamaria a un servicio/repositorio para actualizar el horario
    }

    /**
     * Representacion en cadena del objeto Administrador para depuracion.
     */
    @Override
    public String toString() {
        return TO_STRING_PREFIX +
                "id=" + getId() + // Cambiado para int
                ", nombre='" + getNombre() + SINGLE_QUOTE +
                ", email='" + getEmail() + SINGLE_QUOTE +
                ", telefono='" + getTelefono() + SINGLE_QUOTE +
                ", fechaRegistro=" + getFechaRegistro() +
                ", activo=" + isActivo() +
                DEPARTAMENTO_FIELD + departamento + SINGLE_QUOTE +
                TO_STRING_SUFFIX;
    }
}
