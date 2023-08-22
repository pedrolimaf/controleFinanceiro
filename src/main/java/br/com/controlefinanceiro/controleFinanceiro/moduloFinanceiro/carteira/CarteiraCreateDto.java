package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CarteiraCreateDto(
        @NotBlank
        String nome
) {
}
