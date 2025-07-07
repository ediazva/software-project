package Model.Dominio.Restaurantes;

import java.math.BigDecimal;
import java.util.Objects;

public class Dinero {
    private final BigDecimal cantidad;
    private final String moneda;

    public Dinero(double cantidad, String moneda) {
        if (moneda == null || moneda.isBlank    ()) {
            throw new IllegalArgumentException("La moneda no puede ser nula o vacía.");
        }
        this.cantidad = BigDecimal.valueOf(cantidad);
        this.moneda = moneda.toUpperCase();
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public String getMoneda() {
        return moneda;
    }

    public Dinero sumar(Dinero otraCantidad) {
        validarMoneda(otraCantidad);
        return new Dinero(this.cantidad.add(otraCantidad.cantidad).doubleValue(), moneda);
    }

    public Dinero restar(Dinero otraCantidad) {
        validarMoneda(otraCantidad);
        return new Dinero(this.cantidad.subtract(otraCantidad.cantidad).doubleValue(), moneda);
    }

    public boolean esMayorQue(Dinero otraCantidad) {
        validarMoneda(otraCantidad);
        return this.cantidad.compareTo(otraCantidad.cantidad) > 0;
    }

    private void validarMoneda(Dinero otraCantidad) {
        if (!this.moneda.equals(otraCantidad.moneda)) {
            throw new IllegalArgumentException("Las monedas no coinciden.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dinero)) return false;
        Dinero otro = (Dinero) obj;
        return this.cantidad.compareTo(otro.cantidad) == 0 && this.moneda.equals(otro.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidad.stripTrailingZeros(), moneda);
    }

    @Override
    public String toString() {
        return cantidad + " " + moneda;
    }
}
