package br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.service;


import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto.UserCreateDto;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto.UserDetaisDto;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.dto.UserDto;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.model.User;
import br.com.controlefinanceiro.controleFinanceiro.moduloUsuario.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.token.service.TokenService;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    private String encoderPassword(String password) {
        return encoder.encode(password);
    }

    public UserCreateDto createUser(UserDto userDto){
        var userNoBanco = buscarPorEmail(userDto.email());
        if(userNoBanco != null){
            throw new RuntimeException("Já existe um usuário com esse e-mail cadastrado no sistema.");
        }
        var user = new User(userDto, encoderPassword(userDto.senha()));
        var userCriado = userRepository.save(user);
        var token = tokenService.gerarToken(userCriado);
        return new UserCreateDto(userCriado, token);

    }

    public UserDetaisDto buscarPorEmail(String email){
        return userRepository.findByEmail(email);
    }

}
