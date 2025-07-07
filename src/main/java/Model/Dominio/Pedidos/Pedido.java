package Model.Dominio.Pedidos;

import Model.Dominio.Restaurantes.Dinero;
import Model.Dominio.Restaurantes.Restaurante;

import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private int idCliente;
    private int idRestaurante;
    private Date fechaHoraCreacion;
    private EstadoPedido estado;
    private Dinero montoTotal;
    private String instruccionesEspeciales;
    private Direccion direccionEntrega;
    public Restaurante restaurante;
    public List<ItemPedido> items;

    public void añadirItem(ItemPedido item) {
        // TODO implement here
    }

    public void actualizarEstado(EstadoPedido nuevoEstado) {
        // TODO implement here
    }

    public Dinero calcularMontoTotal() {
        // TODO implement here
        return null;
    }

    public void asignarRepartidor(int idRepartidor) {
        // TODO implement here
    }

    public void cancelar() {
        // TODO implement here
    }

    public List<ItemPedido> obtenerItems() {
        // TODO implement here
        return null;
    }

}