package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import br.com.controlefinanceiro.controleFinanceiro.moduloSeguranca.token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository repository;
    @Autowired
    private TokenService tokenService;

    public List<CarteiraResponseDto> buscarCarteiras(HttpServletRequest request){
        var userId = tokenService.recuperarIdUsuario(request);
        var carteiras = repository.findAllByIdUsuario(userId);
        return carteiras;
    }

    public CarteiraResponseDto criarCarteira(CarteiraCreateDto carteiraCreateDto, HttpServletRequest request) {
        var userId = tokenService.recuperarIdUsuario(request);
        var carteira = new Carteira(carteiraCreateDto, userId);
        var carteiraCriada = repository.save(carteira);
        return new CarteiraResponseDto(carteiraCriada);
    }

    public CarteiraResponseDto buscarCarteira(HttpServletRequest request, UUID id) {
        var userId = tokenService.recuperarIdUsuario(request);
        var carteira = repository.findById(id);
        System.out.println(carteira.get().getIdUsuario() + "   =   " + userId);
        if(!carteira.get().getIdUsuario().equals(userId)){
            throw new RuntimeException("Essa carteira não pertence ao usuário logado.");
        }
        return new CarteiraResponseDto(carteira.get());
    }
}
