// file: src/main/java/org/unsa/softwareproject/repository/RestauranteRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.restaurantes.Restaurante; // Asegúrate de que esta ruta sea correcta
import org.unsa.model.domain.restaurantes.TipoCocina; // Si quieres buscar por tipo de cocina

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
    // Métodos específicos para Restaurante, ej:
    // List<Restaurante> findByTipoCocina(TipoCocina tipoCocina);
    // Optional<Restaurante> findByNombre(String nombre);
}