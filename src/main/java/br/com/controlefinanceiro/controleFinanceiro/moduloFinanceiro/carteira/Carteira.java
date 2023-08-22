package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="cfCarteiras")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private UUID idUsuario;
    private BigDecimal totalDebitos;
    private BigDecimal totalCreditos;

    public Carteira(CarteiraCreateDto carteiraCreateDto, UUID userId) {
        this.nome = carteiraCreateDto.nome();
        this.idUsuario = userId;
        this.totalDebitos = BigDecimal.ZERO;
        this.totalCreditos = BigDecimal.ZERO;
    }
}
