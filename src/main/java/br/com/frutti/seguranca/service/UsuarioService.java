package br.com.frutti.seguranca.service;

import br.com.frutti.seguranca.controller.form.RegisterForm;
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
        return user.orElse(null);
    }
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }
    public void salvar(Usuario usuario){
        this.usuarioRepository.save(usuario);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = buscarPorEmail(username);
        if(user.getNome() == null && user.getId() == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return buscarPorEmail(username);
    }
}
