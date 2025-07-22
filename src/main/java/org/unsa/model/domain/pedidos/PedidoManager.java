package org.unsa.model.domain.pedidos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.unsa.model.domain.usuarios.Cliente;
import org.unsa.model.domain.usuarios.Direccion;
import org.unsa.model.domain.usuarios.Repartidor;
import org.unsa.model.exceptions.ItemNotFoundException;
import org.unsa.model.domain.restaurantes.Restaurante;
import org.unsa.model.repository.ClienteRepository;
import org.unsa.model.repository.PedidoRepository;
import org.unsa.model.repository.RepartidorRepository;
import org.unsa.model.repository.RestauranteRepository;
import org.unsa.model.service.Interfaces.IPedidoServicio;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class PedidoManager implements IPedidoServicio {
    private static final Logger logger = Logger.getLogger(PedidoManager.class.getName());

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;
    private final RepartidorRepository repartidorRepository;

    @Override
    public Pedido crearPedido(PedidoData info) {
        return crearNuevoPedido(info);
    }

    public Pedido crearNuevoPedido(PedidoData info) {
        validarExistenciaCliente(info.getCliente().getId());
        validarExistenciaRestaurante(info.getRestaurante().getId());

        Pedido pedido = construirPedido(info);
        return guardarPedido(pedido);
    }

    private void validarExistenciaCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ItemNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }

    private void validarExistenciaRestaurante(Integer id) {
        if (!restauranteRepository.existsById(id)) {
            throw new ItemNotFoundException("Restaurante no encontrado con ID: " + id);
        }
    }

    private Pedido construirPedido(PedidoData info) {
        Pedido pedido = new Pedido();
        pedido.setInfo(info);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedido;
    }

    private Pedido guardarPedido(Pedido pedido) {
        Pedido guardado = pedidoRepository.save(pedido);
        logger.info("Pedido creado exitosamente con ID: " + guardado.getIdPedido());
        return guardado;
    }

    @Override
    public Pedido obtenerPedidoPorId(Integer idPedido) {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + idPedido));
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Integer idCliente) {
        return pedidoRepository.findByCliente_Id(idCliente);
    }

    @Override
    public void actualizarEstadoPedido(Integer idPedido, EstadoPedido nuevoEstado) {
        Pedido pedido = obtenerPedidoPorId(idPedido);
        pedido.setEstado(nuevoEstado);
        pedidoRepository.save(pedido);
    }

    @Override
    public void asignarRepartidorAPedido(Integer idPedido, Integer idRepartidor) {
        Pedido pedido = obtenerPedidoPorId(idPedido);
        Repartidor repartidor = repartidorRepository.findById(idRepartidor)
                .orElseThrow(() -> new RuntimeException("Repartidor no encontrado con ID: " + idRepartidor));
        pedido.setRepartidor(repartidor);
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
