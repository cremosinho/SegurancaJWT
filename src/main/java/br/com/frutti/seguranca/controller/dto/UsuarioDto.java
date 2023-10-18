package br.com.frutti.seguranca.controller.dto;

import br.com.frutti.seguranca.model.Usuario;
import br.com.frutti.seguranca.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String profile;
    private boolean enabled;
    public UsuarioDto(Usuario usuario){
        this.username = usuario.getEmail();
        this.password = usuario.getPassword();
        this.name = usuario.getNome();
        this.surname = usuario.getSobrenome();
        this.profile = usuario.getPerfil().getNome();
        this.enabled = usuario.isAtivo();
    }
    public UsuarioDto() {
    }
}
