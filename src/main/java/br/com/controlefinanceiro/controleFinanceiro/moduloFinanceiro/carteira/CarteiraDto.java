package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import java.math.BigDecimal;
import java.util.UUID;

public record CarteiraDto(
        UUID id,
        String nome,
        UUID idUsuario,
        BigDecimal totalDebitos,
        BigDecimal totalCreditos
) {
    public CarteiraDto(Carteira carteiraCriada) {
        this(carteiraCriada.getId(), carteiraCriada.getNome(), carteiraCriada.getIdUsuario(), carteiraCriada.getTotalDebitos(), carteiraCriada.getTotalCreditos());
    }
}
