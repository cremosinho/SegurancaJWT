package br.com.frutti.seguranca.controller.form;

import br.com.frutti.seguranca.model.Perfil;

public record RegisterForm(String name, String surname, String username, String password, Perfil role) {
}
