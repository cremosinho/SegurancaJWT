package br.com.frutti.seguranca.security;

import br.com.frutti.seguranca.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String tokenHeader = request.getHeader("Autorization");
       String username = null;
       String token = null;

       if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
           token = tokenHeader.substring(7);
           try{
               username = jwtTokenProvider.getUsernameFromToken(token);
           }catch (IllegalArgumentException e) {
               System.out.println("Unable to get JWT Token");
           } catch (ExpiredJwtException e) {
               System.out.println("JWT Token has expired");
           }
       } else {
           System.out.println("Bearer String not found in token");
       }
       if(null != username && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails = usuarioService.loadUserByUsername(username);
           if(jwtTokenProvider.validateToken(token, userDetails)){
               UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }
       filterChain.doFilter(request, response);
    }
}
