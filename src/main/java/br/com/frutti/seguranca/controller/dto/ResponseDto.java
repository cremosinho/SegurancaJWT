package br.com.frutti.seguranca.controller.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    private final String token;
    public ResponseDto(String token) {
        this.token = token;
    }
}
