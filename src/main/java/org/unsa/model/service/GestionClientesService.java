// file: src/main/java/org/unsa/service/clientes/GestionClientesService.java
package org.unsa.model.service;

import org.unsa.model.dominio.usuarios.Cliente; // Importación corregida para Cliente
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de servicio para la gestion de clientes.
 * Contiene la logica de negocio relacionada con las operaciones de clientes.
 */
public class GestionClientesService {

    private static final Logger logger = Logger.getLogger(GestionClientesService.class.getName());

    /**
     * Constructor por defecto para la clase GestionClientesService.
     */
    public GestionClientesService() {
        logger.info(() -> "Servicio de GestionClientesService inicializado.");
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * En una aplicacion real, esto interactuaria con un repositorio de clientes
     * para persistir el objeto Cliente en una base de datos.
     * @param cliente El objeto Cliente a registrar.
     * @throws IllegalArgumentException Si el objeto cliente es nulo.
     */
    public void registrarCliente(Cliente cliente) { // Nombre del metodo corregido a camelCase
        if (cliente == null) {
            logger.log(Level.SEVERE, () -> "Intento de registrar un cliente nulo.");
            throw new IllegalArgumentException("El cliente a registrar no puede ser nulo.");
        }
        // TODO: Implementar la logica de persistencia real (ej. usando un ClienteRepository.save(cliente))
        logger.info(() -> "Cliente registrado exitosamente (simulacion): " + cliente.getNombre() + " (ID: " + cliente.getId() + ")");
    }

    // Otros metodos de gestion de clientes podrian ir aqui, por ejemplo:
    // public Cliente obtenerClientePorId(String idCliente) { ... }
    // public void actualizarCliente(Cliente cliente) { ... }
    // public void eliminarCliente(String idCliente) { ... }
}
