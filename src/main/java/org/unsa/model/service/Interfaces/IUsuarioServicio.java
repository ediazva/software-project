// file: src/main/java/org/unsa/model/service/Interfaces/IUsuarioServicio.java
package org.unsa.model.service.Interfaces;

import org.unsa.model.domain.usuarios.Usuario; // O tu entidad base de usuario
import org.unsa.model.dtos.CrearUsuarioRequest; // Suponiendo estos DTOs
import org.unsa.model.dtos.ActualizarUsuarioRequest;

import java.util.List;

public interface IUsuarioServicio {
    Usuario crearUsuario(CrearUsuarioRequest request);
    Usuario obtenerUsuarioPorId(Integer id);
    List<Usuario> obtenerTodosLosUsuarios();
    Usuario actualizarUsuario(Integer id, ActualizarUsuarioRequest request);
    void eliminarUsuario(Integer id);
    // Puedes añadir métodos específicos para Cliente, Repartidor, Admin si es necesario
    // Ejemplo: Cliente registrarCliente(CrearClienteRequest request);
}