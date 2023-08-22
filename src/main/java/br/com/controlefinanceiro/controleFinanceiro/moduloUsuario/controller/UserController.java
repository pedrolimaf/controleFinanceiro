package br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.controller;

import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.service.UserService;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity criarUsuario(@RequestBody @Valid UserDto userDto, UriComponentsBuilder uriComponentsBuilder){
        var user = userService.createUser(userDto);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    public ResponseEntity editarUsuario(){
        return ResponseEntity.ok("");
    }
    public ResponseEntity deletarUsuario(){
        return ResponseEntity.ok("");
    }

}
