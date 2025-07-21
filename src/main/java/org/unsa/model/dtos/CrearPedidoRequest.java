// file: src/main/java/org/unsa/dto/pedidos/CrearPedidoRequest.java
package org.unsa.model.dtos;

import org.unsa.model.domain.usuarios.Direccion;
import org.unsa.model.domain.pedidos.DatosPlatoPedido; // Asegurarse de importar DatosPlatoPedido
import java.util.List;

/**
 * DTO para la solicitud de creacion de un nuevo pedido.
 * Contiene los datos necesarios para crear un pedido desde el cliente.
 */
public class CrearPedidoRequest {
    private Integer idCliente; // Cambiado a int
    private Integer idRestaurante; // Cambiado a int
    private List<DatosPlatoPedido> itemsCarrito;
    private Direccion direccionEntrega;
    private String instruccionesEspeciales;

    // Constructor vacio necesario para deserializacion JSON
    public CrearPedidoRequest() {}

    public CrearPedidoRequest(Integer idCliente, Integer idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales) { // IDs int
        this.idCliente = idCliente;
        this.idRestaurante = idRestaurante;
        this.itemsCarrito = itemsCarrito;
        this.direccionEntrega = direccionEntrega;
        this.instruccionesEspeciales = instruccionesEspeciales;
    }

    // Getters y Setters
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; } // Cambiado a int
    public Integer getIdRestaurante() { return idRestaurante; } // Cambiado a int
    public void setIdRestaurante(Integer idRestaurante) { this.idRestaurante = idRestaurante; }
    public List<DatosPlatoPedido> getItemsCarrito() { return itemsCarrito; }
    public void setItemsCarrito(List<DatosPlatoPedido> itemsCarrito) { this.itemsCarrito = itemsCarrito; }
    public Direccion getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(Direccion direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public String getInstruccionesEspeciales() { return instruccionesEspeciales; }
    public void setInstruccionesEspeciales(String instruccionesEspeciales) { this.instruccionesEspeciales = instruccionesEspeciales; }
}
