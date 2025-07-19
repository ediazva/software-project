// file: src/main/java/org/unsa/model/dominio/restaurantes/Dinero.java
package org.unsa.model.domain.restaurantes;

import jakarta.persistence.Embeddable; // Importar la anotacion Embeddable
import jakarta.persistence.Transient; // Para campos no persistentes

import java.math.BigDecimal;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase para representar un valor monetario con su moneda.
 * Implementa operaciones basicas aritmeticas y de comparacion.
 */
@Embeddable // Marca esta clase como un componente incrustable en otras entidades
public class Dinero {
    private BigDecimal valor;
    private String moneda;

    @Transient // Indica que este campo no se mapeara a la base de datos
    private static final Logger logger = Logger.getLogger(Dinero.class.getName());

    /**
     * Constructor vacío para JPA.
     */
    public Dinero() {} // Necesario para JPA

    /**
     * Constructor para la clase Dinero.
     * @param valor El valor numerico del dinero.
     * @param moneda La moneda en la que esta expresado el valor (ej. "PEN", "USD").
     */
    public Dinero(double valor, String moneda) {
        if (valor < 0) {
            logger.log(Level.WARNING, () -> "Se intento crear un objeto Dinero con valor negativo: " + valor);
            throw new IllegalArgumentException("El valor del dinero no puede ser negativo.");
        }
        if (moneda == null || moneda.trim().isEmpty()) {
            logger.log(Level.WARNING, () -> "Se intento crear un objeto Dinero con moneda nula o vacia.");
            throw new IllegalArgumentException("La moneda no puede ser nula o vacia.");
        }
        this.valor = valor;
        this.moneda = moneda.trim().toUpperCase(); // Normalizar a mayusculas
        logger.info(() -> "Objeto Dinero creado: " + this.toString());
    }

    // --- Getters y Setters ---
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            logger.log(Level.WARNING, () -> "Se intento establecer un valor negativo para Dinero: " + valor);
            throw new IllegalArgumentException("El valor del dinero no puede ser negativo.");
        }
        this.valor = valor;
        logger.info(() -> "Valor de Dinero actualizado a: " + this.toString());
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        if (moneda == null || moneda.trim().isEmpty()) {
            logger.log(Level.WARNING, () -> "Intento de establecer una moneda nula o vacia para Dinero.");
            throw new IllegalArgumentException("La moneda no puede ser nula o vacia.");
        }
        this.moneda = moneda.trim().toUpperCase();
        logger.info(() -> "Moneda de Dinero actualizada a: " + this.toString());
    }

    // --- Métodos de Operación ---

    /**
     * Suma esta cantidad de dinero con otra cantidad.
     * @param otraCantidad La cantidad de dinero a sumar.
     * @return Un nuevo objeto Dinero con el resultado de la suma.
     * @throws IllegalArgumentException Si las monedas no coinciden.
     */
    @Transient // Este metodo no es persistente directamente
    public Dinero sumar(Dinero otraCantidad) {
        if (!this.moneda.equals(otraCantidad.getMoneda())) {
            logger.log(Level.SEVERE, () -> "Intento de sumar cantidades con monedas diferentes: " + this.moneda + " vs " + otraCantidad.getMoneda());
            throw new IllegalArgumentException("No se pueden sumar cantidades con monedas diferentes.");
        }
        double resultado = this.valor + otraCantidad.getValor();
        logger.info(() -> "Sumando " + this.toString() + " con " + otraCantidad.toString() + " = " + new Dinero(resultado, this.moneda).toString());
        return new Dinero(resultado, this.moneda);
    }

    /**
     * Resta otra cantidad de dinero de esta cantidad.
     * @param otraCantidad La cantidad de dinero a restar.
     * @return Un nuevo objeto Dinero con el resultado de la resta.
     * @throws IllegalArgumentException Si las monedas no coinciden o el resultado es negativo.
     */
    @Transient // Este metodo no es persistente directamente
    public Dinero restar(Dinero otraCantidad) {
        if (!this.moneda.equals(otraCantidad.getMoneda())) {
            logger.log(Level.SEVERE, () -> "Intento de restar cantidades con monedas diferentes: " + this.moneda + " vs " + otraCantidad.getMoneda());
            throw new IllegalArgumentException("No se pueden restar cantidades con monedas diferentes.");
        }
        double resultado = this.valor - otraCantidad.getValor();
        if (resultado < 0) {
            logger.log(Level.WARNING, () -> "Resultado negativo en resta de Dinero: " + this.toString() + " - " + otraCantidad.toString());
            throw new IllegalArgumentException("El resultado de la resta no puede ser negativo.");
        }
        logger.info(() -> "Restando " + this.toString() + " de " + otraCantidad.toString() + " = " + new Dinero(resultado, this.moneda).toString());
        return new Dinero(resultado, this.moneda);
    }

    /**
     * Compara si esta cantidad de dinero es mayor que otra.
     * @param otraCantidad La cantidad de dinero a comparar.
     * @return true si esta cantidad es mayor, false en caso contrario.
     * @throws IllegalArgumentException Si las monedas no coinciden.
     */
    @Transient // Este metodo no es persistente directamente
    public boolean esMayorQue(Dinero otraCantidad) {
        if (!this.moneda.equals(otraCantidad.getMoneda())) {
            logger.log(Level.SEVERE, () -> "Intento de comparar cantidades con monedas diferentes: " + this.moneda + " vs " + otraCantidad.getMoneda());
            throw new IllegalArgumentException("No se pueden comparar cantidades con monedas diferentes.");
        }
        boolean resultado = this.valor > otraCantidad.getValor();
        logger.info(() -> this.toString() + " es mayor que " + otraCantidad.toString() + "? " + resultado);
        return resultado;
    }

    // --- Métodos Esenciales para Objetos (equals y hashCode) ---

    /**
     * Compara si este objeto Dinero es igual a otro objeto.
     * Dos objetos Dinero se consideran iguales si tienen el mismo valor y la misma moneda.
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Misma instancia
        if (obj == null || getClass() != obj.getClass()) return false; // Nulo o de clase diferente
        Dinero dinero = (Dinero) obj; // Casteo seguro
        // Compara valor y moneda
        return Double.compare(dinero.valor, valor) == 0 &&
                moneda.equals(dinero.moneda);
    }

    /**
     * Genera un codigo hash para este objeto Dinero.
     * Es consistente con el metodo equals().
     * @return El codigo hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(valor, moneda); // Genera un hash basado en los campos relevantes
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return valor + " " + moneda;
    }
}
