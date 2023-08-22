package br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.config;

import br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.security.repository.AuthRepository;
import br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.token.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = tokenService.recuperarToken(request);

        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            var usuario = repository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request,response);
    }

}
