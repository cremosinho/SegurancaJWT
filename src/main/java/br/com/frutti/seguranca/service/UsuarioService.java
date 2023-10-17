package br.com.frutti.seguranca.service;

import br.com.frutti.seguranca.model.Usuario;
import br.com.frutti.seguranca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorEmail(String email){
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }else{
            return new Usuario();
        }
    }
}
