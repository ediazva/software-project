
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.pedidos.ItemPedido; // <<--- ¡Asegúrate de que esta ruta sea correcta!

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    // Puedes añadir métodos de búsqueda personalizados aquí.
    // Por ejemplo, para encontrar todos los ítems de un pedido específico:
    // List<ItemPedido> findByPedidoId(Integer pedidoId); // Si ItemPedido tiene un campo 'pedidoId'
    // O si ItemPedido tiene una relación @ManyToOne con Pedido llamada 'pedido':
    // List<ItemPedido> findByPedido_Id(Integer pedidoId);
    //
    // También podrías buscar ítems por el plato asociado:
    // List<ItemPedido> findByPlato_Id(Integer platoId); // Si ItemPedido tiene una relación @ManyToOne con Plato llamada 'plato'
}