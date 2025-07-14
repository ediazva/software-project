package com.tuapp.presentacion.controlador;

import Model.Dominio.Restaurantes.Plato;
import com.tuapp.aplicacion.servicios.PlatilloServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platillos")
public class PlatilloController {

    @Autowired
    private PlatilloServicio platilloServicio;

    @GetMapping
    public List<Platillo> listarPlatillos() {
        return platilloServicio.listarTodos();
    }

    @GetMapping("/{id}")
    public Platillo verDetalle(@PathVariable Long id) {
        return platilloServicio.obtenerPorId(id);
    }

    @GetMapping("/por-restaurante/{restauranteId}")
    public List<Platillo> listarPorRestaurante(@PathVariable Long restauranteId) {
        return platilloServicio.listarPorRestaurante(restauranteId);
    }

    @PostMapping
    public Platillo crearPlatillo(@RequestBody Platillo nuevo) {
        return platilloServicio.crear(nuevo);
    }

    @PutMapping("/{id}")
    public Platillo actualizarPlatillo(@PathVariable Long id,
                                       @RequestBody Platillo actualizado) {
        actualizado.setId(id);
        return platilloServicio.actualizar(actualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarPlatillo(@PathVariable Long id) {
        platilloServicio.eliminar(id);
    }
}
