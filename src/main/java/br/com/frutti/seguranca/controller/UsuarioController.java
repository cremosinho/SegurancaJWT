package br.com.frutti.seguranca.controller;

import br.com.frutti.seguranca.model.Usuario;
import br.com.frutti.seguranca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/list")
    public List<Usuario> listarUsuarios(){
        return usuarioService.buscarTodos();
    }
}
