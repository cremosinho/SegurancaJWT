package br.com.frutti.seguranca.security;

import br.com.frutti.seguranca.model.Usuario;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private String secret = "meuSegredo";
    private int expiration = 300000;

    public String generateToken(Authentication authentication, Usuario usuario){
        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }

    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            // log para a assinatura inválida do token
        } catch (MalformedJwtException e) {
            // log para o token inválido
        } catch (ExpiredJwtException e) {
            // log para o token expirado
        } catch (UnsupportedJwtException e) {
            // log para o token JWT não suportado
        } catch (IllegalArgumentException e) {
            // log para a string de reivindicação do token vazia
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove o prefixo "Bearer "
        }
        return null; // Retorna null se não houver token no cabeçalho
    }
}
