package org.unsa.model.domain.pedidos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.unsa.model.domain.restaurantes.Restaurante;
import org.unsa.model.domain.usuarios.Cliente;
import org.unsa.model.domain.usuarios.Direccion;

import java.util.ArrayList;
import java.util.List;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PedidoData {
    private static final String CLIENTE_FIELD = ", cliente=";
    private static final String INSTRUCCIONES_ESPECIALES_FIELD = ", instruccionesEspeciales='";
    private static final String DIRECCION_ENTREGA_FIELD = ", direccionEntrega=";
    private static final String RESTAURANTE_FIELD = ", restaurante=";
    private static final String ITEMS_FIELD = ", items=";
    private static final String SINGLE_QUOTE = "'";

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;

    @Embedded
    @AttributeOverride(name = "calle", column = @Column(name = "direccion_calle"))
    @AttributeOverride(name = "numero", column = @Column(name = "direccion_numero"))
    @AttributeOverride(name = "ciudad", column = @Column(name = "direccion_ciudad"))
    @AttributeOverride(name = "codigoPostal", column = @Column(name = "direccion_codigo_postal"))
    @AttributeOverride(name = "referencia", column = @Column(name = "direccion_referencia"))
    private Direccion direccionEntrega;

    private String instruccionesEspeciales;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", orphanRemoval = true)
    private List<ItemPedido> items = new ArrayList<>();

    @Override
    public String toString() {
        return
                CLIENTE_FIELD + (cliente != null ? cliente.getId() : "N/A") +
                INSTRUCCIONES_ESPECIALES_FIELD + (instruccionesEspeciales != null ? instruccionesEspeciales : "N/A") + SINGLE_QUOTE +
                DIRECCION_ENTREGA_FIELD + direccionEntrega +
                RESTAURANTE_FIELD + (restaurante != null ? restaurante.getId() : "N/A") +
                ITEMS_FIELD + items;
    }
}
