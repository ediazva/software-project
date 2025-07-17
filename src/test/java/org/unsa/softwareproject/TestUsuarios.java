// file: src/test/java/org/unsa/softwareproject/TestUsuarios.java
package org.unsa.softwareproject;

import org.unsa.model.dominio.usuarios.Usuario;
import org.unsa.model.dominio.usuarios.Direccion;

import org.unsa.model.dominio.restaurantes.HorarioAtencion;
import org.unsa.model.dominio.restaurantes.Plato;
import org.unsa.model.dominio.restaurantes.Restaurante;
import org.unsa.model.dominio.restaurantes.TipoCocina;
import org.unsa.model.dominio.restaurantes.Dinero;

import org.unsa.model.dominio.pedidos.EstadoPedido;
import org.unsa.model.dominio.pedidos.ItemPedido;
import org.unsa.model.dominio.pedidos.Pedido;
import org.unsa.model.dominio.pedidos.DatosPlatoPedido;
import org.unsa.model.dominio.pedidos.PedidoManager;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Clase de prueba que actua como un "Cookbook" para demostrar el uso de las clases
 * Usuario y Administrador, y otros estilos de programacion.
 */
public class TestUsuarios {

    private static final Logger logger = Logger.getLogger(TestUsuarios.class.getName());

    public static void main(String[] args) {
        System.out.println("--- INICIO DEL COOKBOOK DE USUARIOS Y ADMINISTRADORES ---");

        // --- RECETA 1: Creacion y Uso Basico de Usuario (Estilo Things) ---
        System.out.println("\n--- RECETA 1: Creacion y Uso Basico de Usuario ---");
        try {
            // Paso 1.1: Crear un Usuario valido
            Usuario usuario1 = new Usuario(1, "Ana Garcia", "ana.g@example.com", "912345678");
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
            try {
                new Usuario(2, "Pedro Lopez", "pedro.lopez.com", "987654321"); // Email sin '@'
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al crear usuario con email invalido: " + e.getMessage());
            }

            // Paso 1.5: Intentar actualizar email con valor invalido
            System.out.println("\nIntentando actualizar email con valor invalido...");
            try {
                usuario1.setEmail("ana.invalido.com");
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al actualizar email con valor invalido: " + e.getMessage());
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
            Restaurante restauranteNuevo = new Restaurante("rest-001", "Pizzeria La Delicia", "Av. Principal 123", "987654321", TipoCocina.ITALIANA);
            admin1.registrarRestaurante(restauranteNuevo);

            // Usar añadirPlatoARestaurante
            Plato platoNuevo = new Plato(1, "Pizza Pepperoni", "Deliciosa pizza con queso y pepperoni", new Dinero(15.99, "PEN"));
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
            logger.log(Level.SEVERE, "Error inesperado en Receta 2", e);
        }

        // --- RECETA 3: Procesamiento de Usuarios con Streams (Estilo Lazy-Rivers) ---
        System.out.println("\n--- RECETA 3: Procesamiento de Usuarios con Streams (Lazy-Rivers) ---");
        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario(3, "Maria Sol", "maria.s@example.com", "911223344"));
        listaUsuarios.add(new Usuario(4, "Juan Activo", "juan.a@example.com", "922334455"));
        Usuario inactivo = new Usuario(5, "Laura Paz", "laura.p@example.com", "933445566");
        inactivo.desactivarCuenta();
        listaUsuarios.add(inactivo);
        listaUsuarios.add(new Usuario(6, "Roberto Fuerte", "roberto.f@example.com", "944556677"));

        System.out.println("Usuarios en la lista:");
        listaUsuarios.forEach(u -> System.out.println("- " + u.getNombre() + " (Activo: " + u.isActivo() + ")"));

        // Filtrar usuarios activos y obtener sus nombres (Lazy Evaluation)
        List<String> nombresUsuariosActivos = listaUsuarios.stream()
                .filter(Usuario::isActivo)
                .map(Usuario::getNombre)
                .collect(Collectors.toList());

        System.out.println("\nNombres de usuarios activos (usando Streams):");
        nombresUsuariosActivos.forEach(System.out::println);

        // Contar usuarios con emails que contienen ".com"
        long countComEmails = listaUsuarios.stream()
                .filter(u -> u.getEmail() != null && u.getEmail().contains(".com"))
                .count();
        System.out.println("Cantidad de usuarios con emails .com: " + countComEmails);

        // --- RECETA 4: Operaciones con Dinero (Estilo Things y Error Handling) ---
        System.out.println("\n--- RECETA 4: Operaciones con Dinero ---");
        try {
            Dinero monto1 = new Dinero(100.0, "PEN");
            Dinero monto2 = new Dinero(50.0, "PEN");
            Dinero monto3 = new Dinero(100.0, "USD"); // Moneda diferente para probar errores

            System.out.println("Monto 1: " + monto1);
            System.out.println("Monto 2: " + monto2);
            System.out.println("Monto 3: " + monto3);

            // Suma
            Dinero suma = monto1.sumar(monto2);
            System.out.println(monto1 + " + " + monto2 + " = " + suma);

            // Resta
            Dinero resta = monto1.restar(monto2);
            System.out.println(monto1 + " - " + monto2 + " = " + resta);

            // Comparacion
            System.out.println(monto1 + " es mayor que " + monto2 + "? " + monto1.esMayorQue(monto2));
            System.out.println(monto2 + " es mayor que " + monto1 + "? " + monto2.esMayorQue(monto1));

            // Equals y HashCode
            Dinero monto1Copia = new Dinero(100.0, "PEN");
            System.out.println(monto1 + " es igual a " + monto1Copia + "? " + monto1.equals(monto1Copia));
            System.out.println("Hash de " + monto1 + ": " + monto1.hashCode());
            System.out.println("Hash de " + monto1Copia + ": " + monto1Copia.hashCode());

            // Prueba de errores en Dinero (Error Handling)
            System.out.println("\nIntentando operaciones con errores...");
            try {
                monto1.sumar(monto3); // Sumar monedas diferentes
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al sumar monedas diferentes: " + e.getMessage());
            }

            try {
                monto2.restar(monto1); // Restar con resultado negativo
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al restar con resultado negativo: " + e.getMessage());
            }

            try {
                new Dinero(-50.0, "PEN"); // Crear con valor negativo
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al crear Dinero con valor negativo: " + e.getMessage());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado en Receta 4: " + e.getMessage(), e);
        }

        // --- RECETA 5: Creacion y Uso Basico de Pedido (Estilo Things y Error Handling) ---
        System.out.println("\n--- RECETA 5: Creacion y Uso Basico de Pedido ---");
        try {
            // Preparar datos para el pedido
            Usuario clientePedido = new Usuario(1, "Cliente Pedido", "cliente.p@example.com", "911111111");
            Restaurante restPedido = new Restaurante(2, "Antojitos Peruanos", "Calle Falsa 123", "999888777", TipoCocina.PERUANA);
            Direccion dirEntrega = new Direccion("Av. Los Incas", "456", "Arequipa", "04001", "Frente al parque");

            Plato ceviche = new Plato(2, "Ceviche Mixto", "Pescado y mariscos frescos", new Dinero(25.00, "PEN"));
            Plato lomoSaltado = new Plato(3, "Lomo Saltado", "Clasico plato peruano", new Dinero(30.00, "PEN"));

            List<ItemPedido> itemsPedido = new ArrayList<>();
            itemsPedido.add(new ItemPedido(ceviche, 1));
            itemsPedido.add(new ItemPedido(lomoSaltado, 2));

            // Crear el pedido usando el constructor directo (para compatibilidad con tests anteriores)
            Pedido pedido1 = new Pedido("ped-001", clientePedido.getId(), restPedido.getId(), dirEntrega, itemsPedido, "Sin aji por favor.");
            System.out.println("Pedido 1 creado: " + pedido1.toString());
            System.out.println("Monto Total del Pedido 1: " + pedido1.getMontoTotal());

            // Actualizar estado del pedido
            pedido1.actualizarEstado(EstadoPedido.EN_PREPARACION);
            System.out.println("Estado del Pedido 1 actualizado a: " + pedido1.getEstado());

            pedido1.actualizarEstado(EstadoPedido.EN_CAMINO);
            System.out.println("Estado del Pedido 1 actualizado a: " + pedido1.getEstado());

            // Asignar un repartidor (ID de repartidor es String)
            pedido1.setIdRepartidor("rep-001");
            System.out.println("Repartidor asignado al Pedido 1: " + pedido1.getIdRepartidor());

            // Prueba de errores en Pedido (Error Handling)
            System.out.println("\nIntentando crear pedido con errores...");
            try {
                // Pedido con items vacios
                new Pedido("ped-002", clientePedido.getId(), restPedido.getId(), dirEntrega, new ArrayList<>(), null);
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al crear pedido con items vacios: " + e.getMessage());
            }

            try {
                // Pedido con ID de cliente nulo
                new Pedido("ped-003", null, restPedido.getId(), dirEntrega, itemsPedido, null);
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al crear pedido con ID de cliente nulo: " + e.getMessage());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado en Receta 5: " + e.getMessage(), e);
        }

        // --- RECETA 6: Creacion de Pedido usando PedidoManager (Nuevo metodo) ---
        System.out.println("\n--- RECETA 6: Creacion de Pedido usando PedidoManager ---");
        try {
            PedidoManager pedidoManager = new PedidoManager(); // Instanciar el manager

            // Datos para el nuevo pedido a traves del manager
            String clienteId = "cli-002";
            String restauranteId = "rest-001"; // Pizzeria La Delicia

            List<DatosPlatoPedido> carritoItems = new ArrayList<>();
            carritoItems.add(new DatosPlatoPedido("plato-001", 2)); // 2 Pizzas Pepperoni
            carritoItems.add(new DatosPlatoPedido("plato-004", 1)); // Un plato que no existe en la simulacion

            Direccion dirManager = new Direccion("Calle del Sol", "789", "Arequipa", "04002", "Cerca a la plaza");

            Pedido pedido2 = pedidoManager.crearNuevoPedido(clienteId, restauranteId, carritoItems, dirManager, "Extra queso en la pizza.");
            System.out.println("Pedido 2 creado via Manager: " + pedido2.toString());
            System.out.println("Monto Total del Pedido 2: " + pedido2.getMontoTotal());

            // Prueba de errores en PedidoManager (Error Handling)
            System.out.println("\nIntentando crear pedido via Manager con errores...");
            try {
                // ID de cliente nulo
                pedidoManager.crearNuevoPedido(null, restauranteId, carritoItems, dirManager, null);
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al crear pedido via Manager con ID de cliente nulo: " + e.getMessage());
            }

            try {
                // Carrito vacio
                pedidoManager.crearNuevoPedido(clienteId, restauranteId, new ArrayList<>(), dirManager, null);
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Capturada excepcion al crear pedido via Manager con carrito vacio: " + e.getMessage());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado en Receta 6: " + e.getMessage(), e);
        }


        System.out.println("\n--- FIN DEL COOKBOOK ---");
    }
}
