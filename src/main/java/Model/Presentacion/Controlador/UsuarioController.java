package Model.Presentacion.Controlador;

import Model.Dominio.Usuarios.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registro")
    public Usuario registrarUsuario(@RequestBody Usuario nuevo) {
        return usuarioServicio.registrar(nuevo);
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestBody Credenciales credenciales) {
        return usuarioServicio.iniciarSesion(credenciales);
    }

    @GetMapping("/{id}")
    public Usuario verPerfil(@PathVariable Long id) {
        return usuarioServicio.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizarPerfil(@PathVariable Long id,
                                    @RequestBody Usuario actualizado) {
        actualizado.setId(id);
        return usuarioServicio.actualizar(actualizado);
    }
}
