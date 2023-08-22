package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CarteiraRepository extends JpaRepository<Carteira, UUID> {
    List<CarteiraResponseDto> findAllByIdUsuario(UUID userId);
}
