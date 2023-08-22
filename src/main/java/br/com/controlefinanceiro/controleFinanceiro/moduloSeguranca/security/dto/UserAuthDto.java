package br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.dto;

import java.util.UUID;

public record UserAuthDto(
        String token,
        UUID id
) {
    public UserAuthDto(UUID id, String token) {
        this(token, id);
    }
}
