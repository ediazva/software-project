package Model.Presentacion.Controlador;

import Model.Servicios.CatalogoRestaurantesService;
import Model.Dominio.Restaurantes.Restaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    static final String HOME_URL = "home";

    private CatalogoRestaurantesService catalogoRestaurantesService;

    @Autowired
    public HomeController(CatalogoRestaurantesService catalogoRestaurantesService) {
        this.catalogoRestaurantesService = catalogoRestaurantesService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Restaurante> restaurantes = obtenerRestaurantes();
        prepararModelo(model, restaurantes);
        return vistaHome();
    }

    private List<Restaurante> obtenerRestaurantes() {
        return catalogoRestaurantesService.obtenerTodos();
    }

    private void prepararModelo(Model model, List<Restaurante> restaurantes) {
        model.addAttribute("restaurantes", restaurantes);
    }

    private String vistaHome() {
        return HOME_URL;
    }
}
