package br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto;

import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.model.User;

import java.util.UUID;

public record UserCreateDto(
        UUID id,
        String nome,
        String email,
        String token
) {

    public UserCreateDto(User createUser, String token) {
        this(createUser.getId(), createUser.getNome(), createUser.getEmail(), token);
    }
}
