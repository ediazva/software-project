package Model.Servicios;

import Model.Repositorio.RestauranteRepositorio;
import Model.Dominio.Restaurantes.Restaurante;
import java.util.List;

public class CatalogoRestaurantesService {

    private RestauranteRepositorio restauranteRepo;

    public CatalogoRestaurantesService(RestauranteRepositorio restauranteRepo) {
        this.restauranteRepo = restauranteRepo;
    }

    public List<Restaurante> buscarPorTexto(String texto) {
        return restauranteRepo
                .obtenerTodos()
                .stream()
                .filter(r -> contiene(r.getNombre(), texto) || contiene(r.getDescripcion(), texto))
                .toList();
    }

    public Restaurante buscarPorId(Long id) throws Exception {
        return restauranteRepo.buscar(id);
    }

    private boolean contiene(String campo, String texto) {
        return campo != null && campo.toLowerCase().contains(texto.toLowerCase());
    }

    public List<Restaurante> obtenerTodos() {
        return restauranteRepo.obtenerTodos();
    }

    public Restaurante crear(Restaurante nuevo) {
        restauranteRepo.crear(nuevo);
        return nuevo;
    }

    public Restaurante actualizar(Long id, Restaurante actualizado) throws Exception {
        Restaurante existente = restauranteRepo.buscar(id); // Excepción si no existe
        restauranteRepo.actualizar(id.intValue(), actualizado);
        return actualizado;
    }

    public void eliminar(Long id) throws Exception {
        restauranteRepo.buscar(id); // Excepción si no existe
        restauranteRepo.eliminar(id.intValue());
    }
}
