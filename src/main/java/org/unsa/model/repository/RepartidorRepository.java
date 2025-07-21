// file: src/main/java/org/unsa/softwareproject/repository/RepartidorRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.usuarios.Repartidor;
import java.util.Optional;
@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Integer> {
    // Métodos específicos para Repartidor, ej:
    // List<Repartidor> findByTipoVehiculo(String tipoVehiculo);
    // List<Repartidor> findByDisponibleParaEntregasTrue();
    Optional<Repartidor> findById(Integer id);
}