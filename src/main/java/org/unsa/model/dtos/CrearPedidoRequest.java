// file: src/main/java/org/unsa/dto/pedidos/CrearPedidoRequest.java
package org.unsa.dto.pedidos;

import org.unsa.model.dominio.usuarios.Direccion;
import org.unsa.model.dominio.pedidos.DatosPlatoPedido; // Asegurarse de importar DatosPlatoPedido
import java.util.List;

/**
 * DTO para la solicitud de creacion de un nuevo pedido.
 * Contiene los datos necesarios para crear un pedido desde el cliente.
 */
public class CrearPedidoRequest {
    private int idCliente; // Cambiado a int
    private int idRestaurante; // Cambiado a int
    private List<DatosPlatoPedido> itemsCarrito;
    private Direccion direccionEntrega;
    private String instruccionesEspeciales;

    // Constructor vacio necesario para deserializacion JSON
    public CrearPedidoRequest() {}

    public CrearPedidoRequest(int idCliente, int idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales) { // IDs int
        this.idCliente = idCliente;
        this.idRestaurante = idRestaurante;
        this.itemsCarrito = itemsCarrito;
        this.direccionEntrega = direccionEntrega;
        this.instruccionesEspeciales = instruccionesEspeciales;
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; } // Cambiado a int
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; } // Cambiado a int
    public int getIdRestaurante() { return idRestaurante; } // Cambiado a int
    public void setIdRestaurante(int idRestaurante) { this.idRestaurante = idRestaurante; } // Cambiado a int
    public List<DatosPlatoPedido> getItemsCarrito() { return itemsCarrito; }
    public void setItemsCarrito(List<DatosPlatoPedido> itemsCarrito) { this.itemsCarrito = itemsCarrito; }
    public Direccion getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(Direccion direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public String getInstruccionesEspeciales() { return instruccionesEspeciales; }
    public void setInstruccionesEspeciales(String instruccionesEspeciales) { this.instruccionesEspeciales = instruccionesEspeciales; }
}
