package Model.Dominio.Pedidos;

import java.util.Date;

public class Entrega {
    private int id;
    private int idPedido;
    private int idRepartidor;
    private EstadoEntrega estado;
    private Date fechaHoraAsignacion;
    private Date fechaHoraRecojo;
    private Date fechaHoraEntrega;
    private String ubicacionActualRepartidor;


    public void asignarRepartidor(String idRepartidor) {
        // TODO implement here
    }

    public void actualizarEstado(EstadoEntrega nuevoEstado) {
        // TODO implement here
    }

    public void registrarRecojo() {
        // TODO implement here
    }

    public void actualizarUbicacion(String coordenadas) {
        // TODO implement here
    }

}