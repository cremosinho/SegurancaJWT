package br.com.frutti.seguranca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GeneralController {
    @GetMapping
    public String sayHello(){
        return "hello world";
    }

    @GetMapping("/hello")
    public String sayHello2(){
        return "hello world 2";
    }
}
