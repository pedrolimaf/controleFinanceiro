package br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.controller;

import br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.dto.UserLoginDto;
import br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.token.service.TokenService;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserLoginDto loginDto){
        var token = new UsernamePasswordAuthenticationToken(loginDto.email(),loginDto.senha());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((User) authentication.getPrincipal()));
    }

}
