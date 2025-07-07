package Model.Dominio.Restaurantes;

import java.util.Objects;

public class Plato {
    private final int id;
    private String nombre;
    private String descripcion;
    private boolean disponible;
    private Dinero precio;

    public Plato(int id, String nombre, String descripcion, Dinero precio) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del plato no puede ser nulo o vacío.");
        }
        if (descripcion == null) {
            descripcion = "";
        }
        if (precio == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo.");
        }

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = true; // Por defecto, un plato nuevo está disponible
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public Dinero getPrecio() {
        return precio;
    }

    public void marcarComoDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void actualizarPrecio(Dinero nuevoPrecio) {
        if (nuevoPrecio == null) {
            throw new IllegalArgumentException("El nuevo precio no puede ser nulo.");
        }
        if (!nuevoPrecio.getMoneda().equals(this.precio.getMoneda())) {
            throw new IllegalArgumentException("La moneda del nuevo precio debe coincidir con la actual.");
        }
        this.precio = nuevoPrecio;
    }

    @Override
    public String toString() {
        return String.format("Plato #%d - %s\nDescripción: %s\nPrecio: %s\nDisponible: %s",
                id, nombre, descripcion, precio, disponible ? "Sí" : "No");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Plato)) return false;
        Plato otro = (Plato) obj;
        return this.id == otro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
