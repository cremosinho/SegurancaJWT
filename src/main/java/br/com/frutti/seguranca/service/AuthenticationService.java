package br.com.frutti.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication authenticate(String username, String password) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
