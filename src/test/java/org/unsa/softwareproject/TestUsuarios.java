// file: src/test/java/org/unsa/softwareproject/TestUsuarios.java
package org.unsa.softwareproject;

import org.unsa.model.dominio.restaurantes.HorarioAtencion;
import org.unsa.model.dominio.restaurantes.Plato;
import org.unsa.model.dominio.restaurantes.Restaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase de prueba que actua como un "Cookbook" para demostrar el uso de las clases
 */
public class TestUsuarios {

    public static void main(String[] args) {
        System.out.println("--- INICIO DEL COOKBOOK DE USUARIOS Y ADMINISTRADORES ---");

        // --- RECETA 1: Creacion y Uso Basico de Usuario (Estilo Things) ---
        System.out.println("\n--- RECETA 1: Creacion y Uso Basico de Usuario ---");
        try {
            // Paso 1.1: Crear un Usuario valido
            Usuario usuario1 = new Usuario("user-001", "Ana Garcia", "ana.g@example.com", "912345678");
            System.out.println("Usuario 1 creado: " + usuario1.toString());

            // Paso 1.2: Actualizar datos de contacto (demuestra Error/Exception Handling)
            usuario1.actualizarDatosContacto("ana.nueva@example.com", "998765432");
            System.out.println("Usuario 1 despues de actualizar: " + usuario1.toString());

            // Paso 1.3: Desactivar y activar cuenta
            usuario1.desactivarCuenta();
            System.out.println("Usuario 1 despues de desactivar: " + usuario1.toString());
            usuario1.activarCuenta();
            System.out.println("Usuario 1 despues de activar: " + usuario1.toString());

            // Paso 1.4: Intentar crear un usuario con email invalido (demuestra Error/Exception Handling)
            System.out.println("\nIntentando crear usuario con email invalido...");
            Usuario usuarioInvalido = null;
            try {
                usuarioInvalido = new Usuario("user-002", "Pedro Lopez", "pedro.lopez.com", "987654321"); // Email sin '@'
                System.out.println("Usuario invalido creado: " + usuarioInvalido.toString()); // Esto no deberia ejecutarse
            } catch (IllegalArgumentException e) {
                System.err.println("Capturada excepcion al crear usuario: " + e.getMessage());
            }

            // Paso 1.5: Intentar actualizar email con valor invalido
            System.out.println("\nIntentando actualizar email con valor invalido...");
            try {
                usuario1.setEmail("ana.invalido.com");
            } catch (IllegalArgumentException e) {
                System.err.println("Capturada excepcion al actualizar email: " + e.getMessage());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado en Receta 1", e);
        }


        // --- RECETA 2: Creacion y Uso Basico de Administrador (Estilo Things) ---
        System.out.println("\n--- RECETA 2: Creacion y Uso Basico de Administrador ---");
        try {
            Administrador admin1 = new Administrador("admin-001", "Luis Gerente", "luis.g@empresa.com", "955443322", "Operaciones");
            System.out.println("Administrador 1 creado: " + admin1.toString());

            // Usar un metodo especifico de Administrador: registrarRestaurante
            Restaurante restauranteNuevo = new Restaurante("rest-001", "Pizzeria La Delicia");
            admin1.registrarRestaurante(restauranteNuevo);

            // Usar añadirPlatoARestaurante
            Plato platoNuevo = new Plato("plato-001", "Pizza Pepperoni");
            admin1.añadirPlatoARestaurante(restauranteNuevo.getId(), platoNuevo);

            // Usar eliminarPlatoDeRestaurante
            admin1.eliminarPlatoDeRestaurante(restauranteNuevo.getId(), platoNuevo.getId());

            // Usar actualizarHorarioRestaurante
            HorarioAtencion horarioNuevo = new HorarioAtencion("Lunes", "12:00", "22:00");
            admin1.actualizarHorarioRestaurante(restauranteNuevo.getId(), horarioNuevo);

            // Usar un metodo heredado de Usuario
            admin1.actualizarDatosContacto("luis.gerente.nuevo@empresa.com", "977665544");
            System.out.println("Administrador 1 despues de actualizar contacto: " + admin1.toString());

        } catch (Exception e) {
            System.err.println("Error inesperado en Receta 2: " + e.getMessage());
            e.printStackTrace();
        }

        // --- RECETA 3: Procesamiento de Usuarios con Streams (Estilo Lazy-Rivers) ---
        System.out.println("\n--- RECETA 3: Procesamiento de Usuarios con Streams (Lazy-Rivers) ---");
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario("user-003", "Maria Sol", "maria.s@example.com", "911223344"));
        listaUsuarios.add(new Usuario("user-004", "Juan Activo", "juan.a@example.com", "922334455"));
        Usuario inactivo = new Usuario("user-005", "Laura Paz", "laura.p@example.com", "933445566");
        inactivo.desactivarCuenta();
        listaUsuarios.add(inactivo);
        listaUsuarios.add(new Usuario("user-006", "Roberto Fuerte", "roberto.f@example.com", "944556677"));

        System.out.println("Usuarios en la lista:");
        listaUsuarios.forEach(u -> System.out.println("- " + u.getNombre() + " (Activo: " + u.isActivo() + ")"));

        // Filtrar usuarios activos y obtener sus nombres (Lazy Evaluation)
        List<String> nombresUsuariosActivos = listaUsuarios.stream()
                .filter(Usuario::isActivo) // Filtra solo si es activo
                .map(Usuario::getNombre)   // Mapea el objeto Usuario a su nombre
                .collect(Collectors.toList()); // Recolecta los nombres en una nueva lista

        System.out.println("\nNombres de usuarios activos (usando Streams):");
        nombresUsuariosActivos.forEach(System.out::println);

        // Contar usuarios con emails que contienen ".com"
        long countComEmails = listaUsuarios.stream()
                .filter(u -> u.getEmail() != null && u.getEmail().contains(".com"))
                .count();
        System.out.println("Cantidad de usuarios con emails .com: " + countComEmails);


        System.out.println("\n--- FIN DEL COOKBOOK ---");
    }
}