package br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.repository;

import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto.UserDetaisDto;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetaisDto findByEmail(String email);
}
