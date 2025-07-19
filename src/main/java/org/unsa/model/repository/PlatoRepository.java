// file: src/main/java/org/unsa/softwareproject/repository/PlatoRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.restaurantes.Plato; // Asegúrate de que esta ruta sea correcta

import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {
    // Métodos específicos para Plato, ej:
    // List<Plato> findByRestauranteId(Integer restauranteId); // Si Plato tiene una relación con Restaurante
    // List<Plato> findByDisponibleTrue();
    // List<Plato> findByNombreContainingIgnoreCase(String nombre);
}