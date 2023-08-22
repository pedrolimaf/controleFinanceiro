package br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDto(
        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha
) {
}
