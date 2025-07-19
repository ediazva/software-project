// file: src/main/java/org/unsa/model/dominio/pedidos/Pedido.java
package org.unsa.model.domain.pedidos;

import jakarta.persistence.*; // Importar todas las anotaciones de JPA
import org.unsa.model.domain.usuarios.Direccion;
import org.unsa.model.domain.restaurantes.Dinero;
import org.unsa.model.domain.restaurantes.Restaurante;
import org.unsa.model.domain.usuarios.Cliente; // Importar Cliente para la relacion ManyToOne
import org.unsa.model.domain.usuarios.Repartidor; // Importar Repartidor para la relacion ManyToOne

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa un Pedido en el sistema SueldoMinimo App.
 * Contiene detalles del pedido, cliente, repartidor, restaurante y los items.
 */
@Entity
@Table(name = "pedidos") // Mapea esta entidad a la tabla "pedidos"
public class Pedido {
    @Id // Marca 'id' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido; // ID de tipo int

    @ManyToOne // Relacion muchos a uno con Cliente
    @JoinColumn(name = "cliente_id", nullable = false) // Columna para la clave foranea
    private Cliente cliente; // Referencia al objeto Cliente

    @ManyToOne // Relacion muchos a uno con Repartidor (opcional)
    @JoinColumn(name = "repartidor_id") // Columna para la clave foranea, puede ser nula
    private Repartidor repartidor; // Referencia al objeto Repartidor

    @ManyToOne // Relacion muchos a uno con Restaurante
    @JoinColumn(name = "restaurante_id", nullable = false) // Columna para la clave foranea
    private Restaurante restaurante; // Referencia al objeto Restaurante

    @Temporal(TemporalType.TIMESTAMP) // Almacena la fecha y hora completa
    @Column(nullable = false)
    private Date fechaHoraCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Embedded // Incrusta el objeto Dinero
    @AttributeOverride(name = "valor", column = @Column(name = "monto_total_valor"))
    @AttributeOverride(name = "moneda", column = @Column(name = "monto_total_moneda"))
    private Dinero montoTotal;

    private String instruccionesEspeciales;

    @Embedded // Incrusta el objeto Direccion

            @AttributeOverride(name = "calle", column = @Column(name = "direccion_calle"))
            @AttributeOverride(name = "numero", column = @Column(name = "direccion_numero"))
            @AttributeOverride(name = "ciudad", column = @Column(name = "direccion_ciudad"))
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "direccion_codigo_postal"))
            @AttributeOverride(name = "referencia", column = @Column(name = "direccion_referencia"))

    @Column(nullable = false)
    private Direccion direccionEntrega;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Un pedido tiene muchos items, cascada completa
    @JoinColumn(name = "pedido_id") // Clave foranea en la tabla items_pedido que apunta a este pedido
    private List<ItemPedido> items = new ArrayList<>(); // Inicializar para evitar NullPointerException

    @Transient // Indica que este campo no se mapeara a la base de datos
    private static final Logger logger = Logger.getLogger(Pedido.class.getName());

    // Constantes para literales de String duplicados en toString()
    private static final String TO_STRING_PREFIX = "Pedido{";
    private static final String ID_FIELD = "id=";
    private static final String CLIENTE_FIELD = ", cliente=";
    private static final String REPARTIDOR_FIELD = ", repartidor=";
    private static final String RESTAURANTE_FIELD = ", restaurante=";
    private static final String FECHA_HORA_CREACION_FIELD = ", fechaHoraCreacion=";
    private static final String ESTADO_FIELD = ", estado=";
    private static final String MONTO_TOTAL_FIELD = ", montoTotal=";
    private static final String INSTRUCCIONES_ESPECIALES_FIELD = ", instruccionesEspeciales='";
    private static final String DIRECCION_ENTREGA_FIELD = ", direccionEntrega=";
    private static final String ITEMS_FIELD = ", items=";
    private static final String TO_STRING_SUFFIX = "}";
    private static final String SINGLE_QUOTE = "'";

    /**
     * Constructor vacío para la clase Pedido.
     * Es necesario para JPA.
     */
    public Pedido() {
        this.fechaHoraCreacion = new Date();
        this.estado = EstadoPedido.PENDIENTE;
        this.montoTotal = new Dinero(0.0, "PEN"); // O la moneda por defecto
        logger.info(() -> "Nuevo pedido creado (constructor vacio). ID sera asignado por JPA.");
    }

    /**
     * Constructor completo para la clase Pedido.
     * Los IDs de Cliente, Repartidor y Restaurante se reemplazan por los objetos reales.
     * @param idPedido Identificador unico del pedido (int).
     * @param cliente Cliente que realiza el pedido.
     * @param restaurante Restaurante del pedido.
     * @param direccionEntrega Direccion de entrega del pedido.
     * @param items Lista de items del pedido.
     * @param instruccionesEspeciales Instrucciones adicionales para el pedido (puede ser nulo).
     */
    public Pedido(Integer idPedido, Cliente cliente, Restaurante restaurante, Direccion direccionEntrega, List<ItemPedido> items, String instruccionesEspeciales) { // ID cambiado a int
        // En un entorno real con GenerationType.IDENTITY, el ID no se pasaria
        // en el constructor para nuevas entidades, se dejaria a la DB.
        // Lo mantenemos para compatibilidad con TestUsuarios.java por ahora.
        this.idPedido = idPedido;

        if (cliente == null) throw new IllegalArgumentException("El cliente no puede ser nulo.");
        if (restaurante == null) throw new IllegalArgumentException("El restaurante no puede ser nulo.");
        if (direccionEntrega == null) throw new IllegalArgumentException("La direccion de entrega no puede ser nula.");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("La lista de items no puede ser nula o vacia.");

        this.cliente = cliente;
        this.restaurante = restaurante;
        this.direccionEntrega = direccionEntrega;
        this.items.addAll(items); // Añadir todos los items
        this.instruccionesEspeciales = instruccionesEspeciales;
        this.fechaHoraCreacion = new Date();
        this.estado = EstadoPedido.PENDIENTE;
        this.montoTotal = calcularMontoTotal(); // Calcular monto total al crear
        this.repartidor = null; // Inicializa repartidor como nulo (no asignado)
        logger.info(() -> "Pedido creado (completo) con ID: " + this.idPedido + " para cliente: " + this.cliente.getId());
    }

    // --- Getters ---
    public Integer getIdPedido() { return idPedido; }
    public Cliente getCliente() { return cliente; }
    public Repartidor getRepartidor() { return repartidor; }
    public Restaurante getRestaurante() { return restaurante; }
    public Date getFechaHoraCreacion() { return fechaHoraCreacion; }
    public EstadoPedido getEstado() { return estado; }
    public Dinero getMontoTotal() { return montoTotal; }
    public String getInstruccionesEspeciales() { return instruccionesEspeciales; }
    public Direccion getDireccionEntrega() { return direccionEntrega; }
    public List<ItemPedido> getItems() { return new ArrayList<>(items); } // Retorna copia defensiva

    // --- Setters ---
    public void setIdPedido(Integer idPedido) {
        if (idPedido <= 0 && idPedido != 0) { // Permitir 0 para que JPA lo autogenere
            logger.log(Level.WARNING, () -> "Intento de establecer ID de pedido invalido: " + idPedido);
            throw new IllegalArgumentException("El ID del pedido debe ser positivo o 0 para autogeneracion.");
        }
        this.idPedido = idPedido;
        logger.info(() -> "ID de pedido actualizado a: " + idPedido);
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer cliente nulo para pedido " + this.idPedido);
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        this.cliente = cliente;
        logger.info(() -> "Cliente actualizado a: " + cliente.getId() + " para pedido " + this.idPedido);
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor; // Puede ser nulo
        logger.info(() -> "Repartidor actualizado a: " + (repartidor != null ? repartidor.getId() : "N/A") + " para pedido " + this.idPedido);
    }

    public void setRestaurante(Restaurante restaurante) {
        if (restaurante == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer restaurante nulo para pedido " + this.idPedido);
            throw new IllegalArgumentException("El restaurante no puede ser nulo.");
        }
        this.restaurante = restaurante;
        logger.info(() -> "Restaurante para pedido " + this.idPedido+ " actualizado a: " + restaurante.getNombre());
    }

    public void setFechaHoraCreacion(Date fechaHoraCreacion) {
        if (fechaHoraCreacion == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer fecha de creacion nula para pedido " + this.idPedido);
            throw new IllegalArgumentException("La fecha de creacion no puede ser nula.");
        }
        this.fechaHoraCreacion = fechaHoraCreacion;
        logger.info(() -> "Fecha/hora de creacion actualizada para pedido " + this.idPedido + " a: " + fechaHoraCreacion);
    }

    public void setEstado(EstadoPedido estado) {
        if (estado == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer estado nulo para pedido " + this.idPedido);
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        this.estado = estado;
        logger.info(() -> "Estado de pedido " + this.idPedido + " actualizado a: " + estado);
    }

    public void setMontoTotal(Dinero montoTotal) {
        if (montoTotal == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer monto total nulo para pedido " + this.idPedido);
            throw new IllegalArgumentException("El monto total no puede ser nulo.");
        }
        this.montoTotal = montoTotal;
        logger.info(() -> "Monto total de pedido " + this.idPedido + " actualizado a: " + montoTotal);
    }

    public void setInstruccionesEspeciales(String instruccionesEspeciales) {
        this.instruccionesEspeciales = instruccionesEspeciales; // Puede ser nulo o vacio
        logger.info(() -> "Instrucciones especiales para pedido " + this.idPedido + " actualizadas.");
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        if (direccionEntrega == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer direccion de entrega nula para pedido " + this.idPedido);
            throw new IllegalArgumentException("La direccion de entrega no puede ser nula.");
        }
        this.direccionEntrega = direccionEntrega;
        logger.info(() -> "Direccion de entrega para pedido " + this.idPedido + " actualizada.");
    }

    public void setItems(List<ItemPedido> items) {
        if (items == null) {
            logger.log(Level.WARNING, () -> "Intento de establecer lista de items nula para pedido " + this.idPedido);
            throw new IllegalArgumentException("La lista de items no puede ser nula.");
        }
        this.items.clear(); // Limpiar items existentes
        this.items.addAll(items); // Añadir nuevos items
        this.montoTotal = calcularMontoTotal(); // Recalcular monto total al cambiar items
        logger.info(() -> "Items para pedido " + this.idPedido + " actualizados. Nuevo monto total: " + this.montoTotal);
    }

    // --- Métodos de Comportamiento ---

    /**
     * Calcula el monto total del pedido sumando los subtotales de todos los items.
     * @return El monto total del pedido.
     */
    @Transient // Este metodo no es persistente directamente
    private Dinero calcularMontoTotal() {
        if (items == null || items.isEmpty()) {
            return new Dinero(0.0, "PEN"); // O la moneda por defecto
        }
        // Asegurarse de que el primer item tenga un subtotal con moneda para inicializar
        String monedaBase = items.getFirst().getSubtotal() != null ? items.getFirst().getSubtotal().getMoneda() : "PEN";
        Dinero total = new Dinero(0.0, monedaBase);
        for (ItemPedido item : items) {
            if (item.getSubtotal() != null) {
                total = total.sumar(item.getSubtotal());
            } else {
                logger.log(Level.WARNING, () -> "Item con subtotal nulo encontrado en pedido " + this.idPedido + ". Se ignorara.");
            }
        }
        return total;
    }

    /**
     * Actualiza el estado del pedido.
     * @param nuevoEstado El nuevo estado del pedido.
     */
    @Transient // Este metodo no es persistente directamente
    public void actualizarEstado(EstadoPedido nuevoEstado) {
        setEstado(nuevoEstado); // Reutiliza el setter con su validacion y log
    }

    // --- Métodos Esenciales para Objetos (equals y hashCode) ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return idPedido == pedido.idPedido; // Pedidos son iguales si tienen el mismo ID (int)
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido); // Hash basado en int ID
    }

    // --- Método toString() ---
    @Override
    public String toString() {
        return TO_STRING_PREFIX +
                ID_FIELD + idPedido +
                CLIENTE_FIELD + (cliente != null ? cliente.getId() : "N/A") +
                REPARTIDOR_FIELD + (repartidor != null ? repartidor.getId() : "N/A") +
                RESTAURANTE_FIELD + (restaurante != null ? restaurante.getId() : "N/A") +
                FECHA_HORA_CREACION_FIELD + fechaHoraCreacion +
                ESTADO_FIELD + estado +
                MONTO_TOTAL_FIELD + montoTotal +
                INSTRUCCIONES_ESPECIALES_FIELD + (instruccionesEspeciales != null ? instruccionesEspeciales : "N/A") + SINGLE_QUOTE +
                DIRECCION_ENTREGA_FIELD + direccionEntrega +
                ITEMS_FIELD + items +
                TO_STRING_SUFFIX;
    }
}
