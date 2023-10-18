package br.com.frutti.seguranca.model;

import br.com.frutti.seguranca.controller.form.RegisterForm;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usu", schema = "teste")
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    @ManyToOne
    @JoinColumn(name="perfil")
    private Perfil perfil;
    private String senha;
    private boolean ativo;

    public Usuario(RegisterForm register) {
        this.email = register.username();
        this.senha = register.password();
        this.perfil = register.role();
    }

    public Usuario(String nome, String sobrenome, String email, String senha, Perfil perfil) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.perfil = perfil;
        this.senha = senha;
        this.ativo = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.perfil != null){
            return List.of(new SimpleGrantedAuthority(this.perfil.getNome()));
        }else{
            return null;
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.ativo;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.ativo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.ativo;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }
}
