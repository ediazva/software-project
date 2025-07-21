// src/main/java/org/unsa/model/repository/UsuarioRepository.java
package org.unsa.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.unsa.model.domain.usuarios.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 * Extiende JpaRepository para proporcionar operaciones CRUD y más.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar un usuario por su email
    Optional<Usuario> findByEmail(String email);

    // Buscar todos los usuarios activos
    List<Usuario> findByActivoTrue();

    // Buscar usuarios por nombre (ignorando mayúsculas/minúsculas)
    List<Usuario> findByNombreIgnoreCase(String nombre);

    // Buscar usuarios cuyo nombre contenga una palabra clave
    List<Usuario> findByNombreContainingIgnoreCase(String keyword);
}
