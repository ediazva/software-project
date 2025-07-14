package Model.Presentacion.Controlador;

import Model.Dominio.Restaurantes.Restaurante;
import Model.Servicios.CatalogoRestaurantesService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private CatalogoRestaurantesService restauranteServicio;

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        try {
            Restaurante restaurante = restauranteServicio.buscarPorId(id);
            model.addAttribute("restaurante", restaurante);
            return "restaurante";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public Restaurante crearRestaurante(@RequestBody Restaurante nuevo) {
        return restauranteServicio.crear(nuevo);
    }

    @PutMapping("/{id}")
    public Restaurante actualizarRestaurante(@PathVariable Long id,
                                             @RequestBody Restaurante actualizado) {
        try {
            return restauranteServicio.actualizar(id, actualizado);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String eliminarRestaurante(@PathVariable Long id) {
        try {
            restauranteServicio.eliminar(id);
            return "Restaurante eliminado correctamente";
        } catch (Exception e) {
            return "No se pudo eliminar el restaurante: " + e.getMessage();
        }
    }
}
