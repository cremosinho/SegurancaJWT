package br.com.frutti.seguranca.service;

import br.com.frutti.seguranca.model.Usuario;
import br.com.frutti.seguranca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

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

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = buscarPorEmail(username);
        if(user.getNome() == null && user.getId() == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        System.out.println("usuario: " + user.getNome() + " username: " + user.getUsername());
        return buscarPorEmail(username);
    }
}
