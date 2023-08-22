package br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.token.service;

import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.model.User;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.repository.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(User user) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Api Controle Financeiro")
                    .withSubject(user.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }

    public String getSubject(String tokenJWT){
        System.out.println(tokenJWT);
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            var jtoken =  JWT.require(algoritmo)
                    .withIssuer("Api Controle Financeiro")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

            return jtoken;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    public String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

    public UUID recuperarIdUsuario(HttpServletRequest request){
        var token = recuperarToken(request);
        var email = getSubject(token);
        var userId = userRepository.findByEmail(email).id();
        return userId;
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(23).toInstant(ZoneOffset.of("-03:00"));
    }
}
