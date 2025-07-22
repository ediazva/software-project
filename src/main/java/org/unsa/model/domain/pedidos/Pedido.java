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
import lombok.*;

/**
 * Clase que representa un Pedido en el sistema SueldoMinimo App.
 * Contiene detalles del pedido, cliente, repartidor, restaurante y los items.
 */
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id // Marca 'id' como la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido; // ID de tipo int

    @ManyToOne // Relacion muchos a uno con Repartidor (opcional)
    @JoinColumn(name = "repartidor_id") // Columna para la clave foranea, puede ser nula
    private Repartidor repartidor; // Referencia al objeto Repartidor

    @Temporal(TemporalType.TIMESTAMP) // Almacena la fecha y hora completa
    @Column(nullable = false)
    private Date fechaHoraCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Embedded
    private PedidoData info;

    @Embedded // Incrusta el objeto Dinero
    @AttributeOverride(name = "valor", column = @Column(name = "monto_total_valor"))
    @AttributeOverride(name = "moneda", column = @Column(name = "monto_total_moneda"))
    private Dinero montoTotal;

    @Transient // Indica que este campo no se mapeara a la base de datos
    private static final Logger logger = Logger.getLogger(Pedido.class.getName());

    // Constantes para literales de String duplicados en toString()
    private static final String TO_STRING_PREFIX = "Pedido{";
    private static final String ID_FIELD = "id=";
    private static final String REPARTIDOR_FIELD = ", repartidor=";
    private static final String FECHA_HORA_CREACION_FIELD = ", fechaHoraCreacion=";
    private static final String ESTADO_FIELD = ", estado=";
    private static final String MONTO_TOTAL_FIELD = ", montoTotal=";
    private static final String TO_STRING_SUFFIX = "}";

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
     *                  En un entorno real con GenerationType.IDENTITY, el ID no se pasaria
     *                  en el constructor para nuevas entidades, se dejaria a la DB.
     *                 Lo mantenemos para compatibilidad con TestUsuarios.java por ahora.
     */
    public Pedido(PedidoData info, Integer idPedido) { // ID cambiado a int
        this.idPedido = idPedido;
        this.info = info;
        this.fechaHoraCreacion = new Date();
        this.estado = EstadoPedido.PENDIENTE;
        this.montoTotal = calcularMontoTotal(); // Calcular monto total al crear
        this.repartidor = null; // Inicializa repartidor como nulo (no asignado)
        logger.info(() -> "Pedido creado (completo) con ID: " + this.idPedido + " para cliente: " + this.info.getCliente().getId());
    }

    // --- Getters ---
    // Retorna copia defensiva

    // --- Setters ---
    // --- Métodos de Comportamiento ---

    /**
     * Calcula el monto total del pedido sumando los subtotales de todos los items.
     * @return El monto total del pedido.
     */
    @Transient // Este metodo no es persistente directamente
    private Dinero calcularMontoTotal() {
        var items = info.getItems();
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
                REPARTIDOR_FIELD + (repartidor != null ? repartidor.getId() : "N/A") +
                FECHA_HORA_CREACION_FIELD + fechaHoraCreacion +
                ESTADO_FIELD + estado +
                MONTO_TOTAL_FIELD + montoTotal +
                TO_STRING_SUFFIX;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
}
