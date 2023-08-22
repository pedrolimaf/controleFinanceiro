package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import java.math.BigDecimal;
import java.util.UUID;

public record CarteiraResponseDto(
        UUID id,
        String nome,
        BigDecimal totalDebitos,
        BigDecimal totalCreditos
) {

    public CarteiraResponseDto(Carteira carteira) {
        this(carteira.getId(), carteira.getNome(), carteira.getTotalDebitos(), carteira.getTotalCreditos());
    }
}
