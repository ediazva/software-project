//file: src/main/java/org/unsa/model/User.java
package org.unsa.model.dominio.usuarios;

import org.unsa.model.dominio.restaurantes.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Administrador extends Usuario {
    private static final String NAME_ADMIN = "Administrador ";
    private static final Logger logger = Logger.getLogger(Administrador.class.getName());
    private String departamento;

    public Administrador(String id, String nombre , String email, String telefono, String departamento){
        super(id,nombre,email,telefono);
        this.departamento = departamento;
        if(logger.isLoggable(Level.INFO)){
            logger.info(NAME_ADMIN + getNombre() + " creado con ID: " + getId());
        }
    }


    public String getDepartamento() {
        return departamento;
    }
    public void  setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Registrar un nuevo restaurante
     * @param restaurante el objeto Restaurante a registrar.
     */
    public void registrarRestaurante(Restaurante restaurante) {
        if(logger.isLoggable(Level.INFO)) {
            logger.info(NAME_ADMIN + getNombre() + "esta registrando el restaurante " + restaurante.getNombre());
        }
        //falta implementar para la base de datos
    }


    /*
    @param //idRestaurante
    @param //idPlato
    */
    public void insetarPlatoARestaurante(String idRestaurante, Plato plato) {
        if(logger.isLoggable(Level.INFO)){
            logger.info(NAME_ADMIN + getNombre() + "añadio el plato "+ plato.getNombre() +
                        "del restaurante con ID "+ idRestaurante);
        }
    }
    /**
     * Elimina un plato de un restaurante específico usando su ID de tipo String.
     * @param idRestaurante Id del restaurante.
     * @param idPlato ID del plato a eliminar.
     */

    public void eliminarPlatoDeRestaurante(String idRestaurante, String idPlato) {
        if(logger.isLoggable(Level.INFO)) {
            logger.info(NAME_ADMIN + getNombre() + "elimino el plato " + plato.getNombre() +
                        "del restaurante con ID" + idRestaurante);
        }
    }

    /**
     * Actualiza el horario de atención de un restaurante específico usando su ID de tipo String.
     * @param idRestaurante Id del restaurante.
     * @param horario El nuevo objeto HorarioAtencion.
     */

    public void actualizarHorarioRestaurante(String idRestaurante, HorarioAtencion horario) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(NAME_ADMIN + getNombre() + "esta actualizando el horario del restaurante:" +
                    idRestaurante + "a:" + horario.getDiaSemana() + horario.getHoraApetura() + "-" +
                    horario.getHoraCierre());
        }
    }

    // Metodos  para añadir / eliminar / actializar plato
    public void añadirPlatoARestaurante(String id, Plato plato) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(NAME_ADMIN + getNombre() + " añadió el plato '" + plato.getNombre() +
                    "' al restaurante con ID: " + idRestaurante);
        }
    }

    public void eliminarPlatoDeRestaurante(String  idRestaurante, Plato idPlato) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(NAME_ADMIN + getNombre() + " eliminó el plato (ID): " + idPlato +
                    " del restaurante (ID): " + idRestaurante);
        }
    }

    public void actualizarHorarioRestaurante(String idRestaurante, HorarioAtencion horario) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(NAME_ADMIN + getNombre() + " actualizó el horario del restaurante (ID): " +
                    horario.getDiaSemana() + " de " + horario.getHoraApertura() + " a " + horario.getHoraCierre());
        }
    }

    //depuracion
    @Override
    public String toString() {
        return "Administrador{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", telefono='" + getTelefono() + '\'' +
                ", fechaRegistro=" + getFechaRegistro() +
                ", activo=" + isActivo() +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}

