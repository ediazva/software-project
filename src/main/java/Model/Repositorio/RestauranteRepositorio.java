package Model.Repositorio;

import Model.Dominio.Restaurantes.Restaurante;

import java.util.List;

/**
 * 
 */
public class RestauranteRepositorio {

    /**
     * Default constructor
     */
    public RestauranteRepositorio() {
    }

    /**
     * @param restaurante 
     * @return
     */
    public void crear(Restaurante restaurante) {
        // TODO implement here
    }

    /**
     * @param id 
     * @return
     */
    public Restaurante buscar(Long id) throws Exception {
        // TODO implement here
        throw new Exception("Restaurante no encontrado");
    }

    public List<Restaurante> obtenerTodos() {
        return null;
    }

    /**
     * @param id 
     * @return
     */
    public void eliminar(int id) {
        // TODO implement here
    }

    public void actualizar(int id, Restaurante restaurante) {
    }

}