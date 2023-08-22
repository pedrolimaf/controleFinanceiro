package br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.repository;

import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    UserDetails findByEmail(String username);
}
