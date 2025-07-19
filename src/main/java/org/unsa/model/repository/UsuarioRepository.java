// file: src/main/java/org/unsa/softwareproject/repository/UsuarioRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.usuarios.Usuario; // Asegúrate de que esta ruta sea correcta

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Puedes añadir métodos de búsqueda personalizados aquí, por ejemplo:
    // Optional<Usuario> findByEmail(String email);
    // List<Usuario> findByActivoTrue();
}