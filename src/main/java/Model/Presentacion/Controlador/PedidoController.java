package Model.Presentacion.Controlador;

import Model.Dominio.Pedidos.Pedido;
import Model.Servicios.GestionPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private GestionPedidosService pedidoServicio;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoServicio.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido verDetalle(@PathVariable Long id) {
        return pedidoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido nuevo) {
        return pedidoServicio.crear(nuevo);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelarPedido(@PathVariable Long id) {
        pedidoServicio.cancelar(id);
    }

    @PutMapping("/{id}/confirmar-entrega")
    public void confirmarEntrega(@PathVariable Long id) {
        pedidoServicio.confirmarEntrega(id);
    }
}
