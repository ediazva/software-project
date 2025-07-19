// src/main/java/org/unsa/softwareproject/repository/PedidoRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.pedidos.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Puedes añadir métodos personalizados aquí, por ejemplo:
    // List<Pedido> findByClienteId(Integer clienteId);
    // List<Pedido> findByEstado(EstadoPedido estado);
}