// src/main/java/org/unsa/model/controller/MainController.java
package org.unsa.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.unsa.model.domain.usuarios.Usuario;
import org.unsa.model.repository.UsuarioRepository;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UsuarioRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
        Usuario n = new Usuario();
        n.setNombre(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Usuario> getAllUsers() {
        return userRepository.findAll();
    }
}
