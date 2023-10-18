package br.com.frutti.seguranca.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioForm {
    private String username;
    private String password;

    public UsuarioForm(String username, String senha) {
        this.username = username;
        this.password = senha;
    }

    public UsuarioForm() {
    }
}
