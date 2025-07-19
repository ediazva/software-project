package org.unsa.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unsa.model.domain.pedidos.DatosPlatoPedido;
import org.unsa.model.domain.pedidos.EstadoPedido;
import org.unsa.model.domain.pedidos.Pedido;
import org.unsa.model.domain.usuarios.Direccion;
import org.unsa.model.repository.PedidoRepository;
import org.unsa.model.repository.ClienteRepository;
import org.unsa.model.repository.RepartidorRepository;
import org.unsa.model.service.Interfaces.IPedidoServicio;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PedidoManager implements IPedidoServicio {

    private static final Logger logger = Logger.getLogger(PedidoManager.class.getName());

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final RepartidorRepository repartidorRepository;

    @Autowired
    public PedidoManager(PedidoRepository pedidoRepository,
                         ClienteRepository clienteRepository,
                         RepartidorRepository repartidorRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.repartidorRepository = repartidorRepository;
    }

    @Override
    public Pedido crearNuevoPedido(Integer idCliente, Integer idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales) {
        try {
            Pedido pedido = new Pedido();
            pedido.setIdCliente(idCliente);
            pedido.setIdRestaurante(idRestaurante);
            pedido.setItems(itemsCarrito);
            pedido.setDireccionEntrega(direccionEntrega);
            pedido.setInstruccionesEspeciales(instruccionesEspeciales);
            pedido.setEstado(EstadoPedido.PENDIENTE);
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear el pedido: " + e.getMessage(), e);
            throw new RuntimeException("No se pudo crear el pedido.");
        }
    }

    @Override
    public Pedido obtenerPedidoPorId(Integer idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + idPedido));
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Integer idCliente) {
        return pedidoRepository.findByIdCliente(idCliente);
    }

    @Override
    public void actualizarEstadoPedido(Integer idPedido, EstadoPedido nuevoEstado) {
        Optional<Pedido> optional = pedidoRepository.findById(idPedido);
        if (optional.isPresent()) {
            Pedido pedido = optional.get();
            pedido.setEstado(nuevoEstado);
            pedidoRepository.save(pedido);
        } else {
            throw new RuntimeException("Pedido no encontrado con ID: " + idPedido);
        }
    }

    @Override
    public void asignarRepartidorAPedido(Integer idPedido, Integer idRepartidor) {
        Pedido pedido = obtenerPedidoPorId(idPedido);
        pedido.setIdRepartidor(idRepartidor);
        pedido.setEstado(EstadoPedido.EN_CAMINO);
        pedidoRepository.save(pedido);
    }

    @Override
    public void cancelarPedido(Integer idPedido, Integer idUsuario) {
        Pedido pedido = obtenerPedidoPorId(idPedido);
        pedido.setEstado(EstadoPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }

    @Override
    public void confirmarEntrega(Integer idPedido) {
        Pedido pedido = obtenerPedidoPorId(idPedido);
        pedido.setEstado(EstadoPedido.ENTREGADO);
        pedidoRepository.save(pedido);
    }
}
