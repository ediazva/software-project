// file: src/main/java/org/unsa/softwareproject/repository/AdministradorRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.usuarios.Administrador; // Asegúrate de que esta ruta sea correcta

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    // Métodos específicos para Administrador si los necesitas, ej:
    // List<Administrador> findByDepartamento(String departamento);
}