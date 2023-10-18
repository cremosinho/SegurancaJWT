package br.com.frutti.seguranca.controller;

import br.com.frutti.seguranca.controller.dto.AuthResponseDto;
import br.com.frutti.seguranca.controller.form.AuthForm;
import br.com.frutti.seguranca.controller.form.RegisterForm;
import br.com.frutti.seguranca.model.Perfil;
import br.com.frutti.seguranca.model.Usuario;
import br.com.frutti.seguranca.service.TokenService;
import br.com.frutti.seguranca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity<?> criarToken(@RequestBody AuthForm user) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        final UserDetails userDetails = usuarioService.loadUserByUsername(user.username());
        final String token = tokenService.generateToken((Usuario) userDetails);
        return ResponseEntity.ok(new AuthResponseDto(token));

    }
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegisterForm register, UriComponentsBuilder uriComponentsBuilder) {
        String passwordEncoded = passwordEncoder.encode(register.password());
        Usuario usu = new Usuario(register.name(), register.surname(), register.username(), passwordEncoded, register.role());

        if(usuarioService.buscarPorEmail(register.username()) != null){
            return ResponseEntity.badRequest().build();

        }else{
            usuarioService.salvar(usu);
            URI uri = uriComponentsBuilder.path("/hello").build().toUri();
            return ResponseEntity.created(uri).build();
        }
    }


}
