// file: src/main/java/org/unsa/service/impl/PlatilloServicioImpl.java
package org.unsa.model.service.impl;

import org.unsa.model.domain.restaurantes.Plato;
import org.unsa.model.domain.restaurantes.Dinero;
import org.unsa.model.repository.PlatoRepository;
import org.unsa.model.repository.RestauranteRepository;
import org.unsa.service.interfaces.IPlatilloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionPlatillosService implements IPlatilloServicio {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public Plato crearPlatillo(Integer idRestaurante, String nombre, String descripcion, Dinero precio) {
        var restaurante = restauranteRepository.findById(idRestaurante)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));
        var plato = new Plato(restaurante, nombre, descripcion, precio, true);
        return platoRepository.save(plato);
    }

    @Override
    public List<Plato> listarPlatillosPorRestaurante(Integer idRestaurante) {
        return platoRepository.findByRestauranteId(idRestaurante);
    }

    @Override
    public Plato verDetallePlatillo(Integer idPlatillo) {
        return platoRepository.findById(idPlatillo)
                .orElseThrow(() -> new IllegalArgumentException("Platillo no encontrado"));
    }

    @Override
    public Plato actualizarPlatillo(Integer idPlatillo, String nombre, String descripcion, Dinero precio, boolean disponible) {
        var plato = platoRepository.findById(idPlatillo)
                .orElseThrow(() -> new IllegalArgumentException("Platillo no encontrado"));
        plato.setNombre(nombre);
        plato.setDescripcion(descripcion);
        plato.setPrecio(precio);
        plato.setDisponible(disponible);
        return platoRepository.save(plato);
    }

    @Override
    public void eliminarPlatillo(Integer idPlatillo) {
        platoRepository.deleteById(idPlatillo);
    }

    @Override
    public void marcarPlatilloComoDisponible(Integer idPlatillo, boolean disponible) {
        var plato = platoRepository.findById(idPlatillo)
                .orElseThrow(() -> new IllegalArgumentException("Platillo no encontrado"));
        plato.setDisponible(disponible);
        platoRepository.save(plato);
    }
}
