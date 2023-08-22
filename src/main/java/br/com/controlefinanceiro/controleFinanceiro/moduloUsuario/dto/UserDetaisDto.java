package br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto;

import java.util.UUID;

public record UserDetaisDto(
        UUID id,
        String nome,
        String email,
        String senha
) {
}
