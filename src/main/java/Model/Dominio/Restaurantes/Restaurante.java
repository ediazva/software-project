package Model.Dominio.Restaurantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Restaurante {
    private final int id;
    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private TipoCocina tipoCocina;
    private double calificacionPromedio;
    private HorarioAtencion horarioAtencion;
    private List<Plato> platos;

    public Restaurante(int id, String nombre, String descripcion, String direccion, String telefono,
                       TipoCocina tipoCocina, HorarioAtencion horarioAtencion) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        if (direccion == null || direccion.isBlank()) throw new IllegalArgumentException("La dirección no puede ser nula o vacía.");
        if (telefono == null || telefono.isBlank()) throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío.");
        if (tipoCocina == null) throw new IllegalArgumentException("El tipo de cocina no puede ser nulo.");
        if (horarioAtencion == null) throw new IllegalArgumentException("El horario no puede ser nulo.");

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCocina = tipoCocina;
        this.horarioAtencion = horarioAtencion;
        this.platos = new ArrayList<>();
        this.calificacionPromedio = 0.0;
    }

    public void addPlato(Plato plato) {
        if (plato == null) throw new IllegalArgumentException("El plato no puede ser nulo.");
        platos.add(plato);
    }

    public void eliminarPlato(int idPlato) {
        platos.removeIf(p -> p.getId() == idPlato);
    }

    public void actualizarHorario(HorarioAtencion nuevoHorario) {
        if (nuevoHorario == null) throw new IllegalArgumentException("El horario no puede ser nulo.");
        this.horarioAtencion = nuevoHorario;
    }

    public boolean estaDisponible() {
        java.time.LocalTime ahora = java.time.LocalTime.now();
        java.time.DayOfWeek dia = java.time.LocalDate.now().getDayOfWeek();
        return horarioAtencion.estaAbiertoAhora(ahora.toString().substring(0, 5), dia.name().toLowerCase());
    }

    // Getters necesarios
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<Plato> getPlatos() {
        return new ArrayList<>(platos); // defensivo
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        if (calificacionPromedio < 0 || calificacionPromedio > 5)
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 5.");
        this.calificacionPromedio = calificacionPromedio;
    }

    @Override
    public String toString() {
        return String.format("Restaurante #%d - %s\nDirección: %s\nTel: %s\nTipo: %s\nHorario: %s\nDisponible: %s",
                id, nombre, direccion, telefono, tipoCocina, horarioAtencion, estaDisponible() ? "Sí" : "No");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurante)) return false;
        Restaurante that = (Restaurante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}