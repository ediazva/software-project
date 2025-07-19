// file: src/main/java/org/unsa/model/dominio/usuarios/Direccion.java
package org.unsa.model.domain.usuarios;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa una direccion fisica.
 */
public class Direccion {
    private String calle;
    private String numero;
    private String ciudad;
    private String codigoPostal;
    private String referencia; // Alguna referencia adicional

    private static final Logger logger = Logger.getLogger(Direccion.class.getName());

    // Constantes para literales de String duplicados en toString()
    private static final String TO_STRING_PREFIX = "Direccion{";
    private static final String CALLE_FIELD = "calle='";
    private static final String NUMERO_FIELD = ", numero='";
    private static final String CIUDAD_FIELD = ", ciudad='";
    private static final String CODIGO_POSTAL_FIELD = ", codigoPostal='";
    private static final String REFERENCIA_FIELD = ", referencia='";
    private static final String TO_STRING_SUFFIX = "}";
    private static final String SINGLE_QUOTE = "'";


    /**
     * Constructor para la clase Direccion.
     * @param calle Nombre de la calle.
     * @param numero Numero de la direccion.
     * @param ciudad Ciudad.
     * @param codigoPostal Codigo postal.
     * @param referencia Referencia adicional para la direccion (puede ser nulo).
     * @throws IllegalArgumentException Si la calle o la ciudad son nulas o vacias.
     */
    public Direccion(String calle, String numero, String ciudad, String codigoPostal, String referencia) {
        if (calle == null || calle.trim().isEmpty()) {
            logger.log(Level.WARNING, () -> "Calle no puede ser nula o vacia.");
            throw new IllegalArgumentException("Calle no puede ser nula o vacia.");
        }
        if (ciudad == null || ciudad.trim().isEmpty()) {
            logger.log(Level.WARNING, () -> "Ciudad no puede ser nula o vacia.");
            throw new IllegalArgumentException("Ciudad no puede ser nula o vacia.");
        }
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.referencia = referencia;
        logger.info(() -> "Direccion creada: " + this.toString());
    }

    // --- Getters ---
    public String getCalle() { return calle; }
    public String getNumero() { return numero; }
    public String getCiudad() { return ciudad; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getReferencia() { return referencia; }

    // --- Setters ---
    public void setCalle(String calle) {
        this.calle = calle;
        logger.info(() -> "Calle actualizada a: " + calle);
    }
    public void setNumero(String numero) {
        this.numero = numero;
        logger.info(() -> "Numero de direccion actualizado a: " + numero);
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
        logger.info(() -> "Ciudad actualizada a: " + ciudad);
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
        logger.info(() -> "Codigo postal actualizado a: " + codigoPostal);
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
        logger.info(() -> "Referencia de direccion actualizada.");
    }

    /**
     * Representacion en cadena del objeto Direccion para depuracion.
     */
    @Override
    public String toString() {
        return TO_STRING_PREFIX +
                CALLE_FIELD + calle + SINGLE_QUOTE +
                NUMERO_FIELD + (numero != null ? numero : "N/A") + SINGLE_QUOTE +
                CIUDAD_FIELD + ciudad + SINGLE_QUOTE +
                CODIGO_POSTAL_FIELD + (codigoPostal != null ? codigoPostal : "N/A") + SINGLE_QUOTE +
                REFERENCIA_FIELD + (referencia != null ? referencia : "N/A") + SINGLE_QUOTE +
                TO_STRING_SUFFIX;
    }

    /**
     * Compara si este objeto Direccion es igual a otro objeto.
     * Dos objetos Direccion se consideran iguales si todos sus atributos son iguales.
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(calle, direccion.calle) &&
                Objects.equals(numero, direccion.numero) &&
                Objects.equals(ciudad, direccion.ciudad) &&
                Objects.equals(codigoPostal, direccion.codigoPostal) &&
                Objects.equals(referencia, direccion.referencia);
    }

    /**
     * Genera un codigo hash para este objeto Direccion.
     * Es consistente con el metodo equals().
     * @return El codigo hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(calle, numero, ciudad, codigoPostal, referencia);
    }
}
