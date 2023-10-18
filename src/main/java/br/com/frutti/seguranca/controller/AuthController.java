package br.com.frutti.seguranca.controller;

import br.com.frutti.seguranca.controller.dto.ResponseDto;
import br.com.frutti.seguranca.controller.form.UsuarioForm;
import br.com.frutti.seguranca.security.CustomAuthenticationManager;
import br.com.frutti.seguranca.security.JwtTokenProvider;
import br.com.frutti.seguranca.service.AuthenticationService;
import br.com.frutti.seguranca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/login")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private JwtTokenProvider tokenManager;
    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;


    @PostMapping
    public ResponseEntity<ResponseDto> createToken(@RequestBody UsuarioForm user) throws Exception{
        try{
            Authentication auth = customAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        System.out.println("Hello world");
        final UserDetails usu = usuarioService.loadUserByUsername(user.getUsername());
        final String token = tokenManager.generateToken(usu);
        return ResponseEntity.ok(new ResponseDto(token));
    }


}
