// file: src/main/java/org/unsa/model/servicios/impl/GestionPedidosService.java
package org.unsa.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unsa.model.domain.pedidos.Pedido;
import org.unsa.model.domain.pedidos.DatosPlatoPedido;
import org.unsa.model.domain.pedidos.EstadoPedido;
import org.unsa.model.domain.usuarios.Direccion;
import org.unsa.model.service.Interfaces.IPedidoServicio;
import org.unsa.model.repository.PedidoRepository;
import org.unsa.model.repository.ClienteRepository;
import org.unsa.model.repository.RestauranteRepository;
import org.unsa.model.repository.PlatoRepository;
import org.unsa.model.repository.RepartidorRepository;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de servicio para la gestion de pedidos.
 * Contiene la logica de negocio relacionada con la creacion, actualizacion y consulta de pedidos.
 */
@Service
public class GestionPedidosService implements IPedidoServicio {

    private static final Logger logger = LoggerFactory.getLogger(GestionPedidosService.class);
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final RestauranteRepository restauranteRepository;
    private final PlatoRepository platoRepository;
    private final RepartidorRepository repartidorRepository;

    /**
     * Constructor por defecto para la clase GestionPedidosService.
     * Inicializa el PedidoManager.
     */
    @Autowired
    public GestionPedidosService(PedidoRepository pedidoRepository,
                                 RepartidorRepository repartidorRepository,
                                 ClienteRepository clienteRepository,
                                 RestauranteRepository restauranteRepository,
                                 PlatoRepository platoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.repartidorRepository = repartidorRepository;
        this.clienteRepository = clienteRepository;
        this.restauranteRepository = restauranteRepository;
        this.platoRepository = platoRepository;
        logger.info("Servicio de GestionPedidosService inicializado con repositorios.");
    }

    /**
     * Crea un nuevo pedido en el sistema, encapsulando la logica del PedidoManager.
     * @param idCliente Id del cliente que realiza el pedido.
     * @param idRestaurante Id del restaurante al que se hace el pedido.
     * @param itemsCarrito Lista de datos de platos y cantidades para el pedido.
     * @param direccionEntrega Direccion de entrega del pedido.
     * @param instruccionesEspeciales Instrucciones adicionales para el pedido (puede ser nulo).
     * @return El objeto Pedido recien creado.
     * @throws IllegalArgumentException Si los datos proporcionados son invalidos.
     */
    @Override
    @Transactional // Esto es muy importante para métodos que modifican la DB
    public Pedido crearPedido(Integer idCliente, Integer idRestaurante, List<DatosPlatoPedido> itemsCarrito, Direccion direccionEntrega, String instruccionesEspeciales) {
        logger.info("Solicitud para crear un nuevo pedido para cliente {} en restaurante {}", idCliente, idRestaurante);
        try {
            var cliente = clienteRepository.findById(idCliente)
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + idCliente));
            var restaurante = restauranteRepository.findById(idRestaurante)
                    .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado con ID: " + idRestaurante));
            Pedido nuevoPedido = new Pedido(cliente, restaurante);
            nuevoPedido.setDireccionEntrega(direccionEntrega); // Asumiendo que Pedido tiene este setter
            nuevoPedido.setInstruccionesEspeciales(instruccionesEspeciales); // Asumiendo que Pedido tiene este setter
            // Establecer fecha y estado iniciales si no se hacen en el constructor de Pedido
            // nuevoPedido.setFecha(new Date());
            // nuevoPedido.setEstado(EstadoPedido.PENDIENTE);

            // 3. Procesar los items del carrito y añadirlos al pedido
            // Esto implica crear ItemPedido y relacionarlos con el Pedido
            for (DatosPlatoPedido itemDto : itemsCarrito) {
                var plato = platoRepository.findById(itemDto.getIdPlato())
                        .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado con ID: " + itemDto.getIdPlato()));
                // Aquí necesitas crear un ItemPedido y añadirlo al nuevoPedido
                // Asumiendo que tienes un constructor o setter adecuado en ItemPedido
                // ItemPedido itemPedido = new ItemPedido(plato, itemDto.getCantidad());
                // nuevoPedido.agregarItem(itemPedido); // Método que agrega el item y establece la relación bidireccional
            }
            // Asegúrate de que tu método agregarItem en Pedido actualice el total del pedido

            // 4. Guardar el pedido (que cascadeará los ItemPedido si configurado)
            Pedido pedidoGuardado = pedidoRepository.save(nuevoPedido);

            logger.info("Pedido {} creado exitosamente.", pedidoGuardado.getId());
            return pedidoGuardado;
        } catch (IllegalArgumentException e) {
            logger.error("Fallo al crear pedido: {}", e.getMessage(), e); // Usa error para excepciones de negocio
            throw e;
        } catch (Exception e) {
            logger.error("Error inesperado al crear pedido: {}", e.getMessage(), e); // Usa error para excepciones inesperadas
            throw new RuntimeException("Error al crear pedido", e); // Envuelve en RuntimeException
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<Pedido> obtenerPedidosPorCliente(Integer idCliente) {
        logger.info("Obteniendo pedidos para cliente con ID: {}", idCliente);
        return pedidoRepository.findByCliente_Id(idCliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> obtenerPedidosPorCliente(Integer idCliente) {
        logger.info("Obteniendo pedidos para cliente con ID: {}", idCliente);

        return pedidoRepository.findByCliente_Id(idCliente);
    }

    @Override
    @Transactional
    public void actualizarEstadoPedido(Integer idPedido, EstadoPedido nuevoEstado) {
        logger.info("Actualizando estado del pedido {} a: {}", idPedido, nuevoEstado);
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido con ID " + idPedido + " no encontrado."));
        pedido.actualizarEstado(nuevoEstado); // Asume que este método en Pedido valida la transición
        pedidoRepository.save(pedido); // Guarda los cambios
        logger.info("Estado de pedido {} actualizado a {} .", idPedido, nuevoEstado);
    }

    @Override
    @Transactional
    public void asignarRepartidorAPedido(Integer idPedido, Integer idRepartidor) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);
        Optional<Repartidor> repartidorOpt = repartidorRepository.findById(idRepartidor);

        if (pedidoOpt.isEmpty()) {
            throw new IllegalArgumentException("Pedido con ID " + idPedido + " no encontrado.");
        }

        if (repartidorOpt.isEmpty()) {
            throw new IllegalArgumentException("Repartidor con ID " + idRepartidor + " no encontrado.");
        }

        Pedido pedido = pedidoOpt.get();
        Repartidor repartidor = repartidorOpt.get();

        pedido.setRepartidor(repartidor);
        pedidoRepository.save(pedido);
    }

    @Override
    @Transactional
    public void cancelarPedido(Integer idPedido, Integer idUsuario) {
        logger.info("Cancelando pedido {} por usuario {}", idPedido, idUsuario);
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido con ID " + idPedido + " no encontrado para cancelar."));
        if (pedido.getCliente() == null) {
            throw new IllegalStateException("El pedido con ID " + idPedido + " no tiene un cliente asociado.");
        }
        if (!pedido.getCliente().getId().equals(idUsuario)) {
            throw new IllegalArgumentException("El usuario " + idUsuario + " no tiene permiso para cancelar el pedido " + idPedido + " (no es el cliente asociado).");
        }
        pedido.actualizarEstado(EstadoPedido.CANCELADO);
        pedidoRepository.save(pedido);
        logger.info("Pedido {} cancelado por usuario {}.", idPedido, idUsuario);
    }

    @Override
    @Transactional
    public void confirmarEntrega(Integer idPedido) {
        logger.info("Confirmando entrega del pedido {}", idPedido);
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido con ID " + idPedido + " no encontrado para confirmar entrega."));
        pedido.actualizarEstado(EstadoPedido.ENTREGADO); // Asume que este método en Pedido actualiza a ENTREGADO
        pedidoRepository.save(pedido);
        logger.info("Pedido {} confirmado como ENTREGADO.", idPedido);
    }
}
