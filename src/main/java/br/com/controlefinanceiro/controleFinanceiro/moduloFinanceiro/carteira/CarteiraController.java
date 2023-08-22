package br.com.controlefinanceiro.controleFinanceiro.moduloFinanceiro.carteira;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping
    public ResponseEntity buscarTodasCarteiras(HttpServletRequest request){
        var carteiras = carteiraService.buscarCarteiras(request);
        return ResponseEntity.ok(carteiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarUmaCarteira(@PathVariable @Valid UUID id, HttpServletRequest request){
        var carteira = carteiraService.buscarCarteira(request, id);
        return ResponseEntity.ok(carteira);
    }

    @PostMapping
    public ResponseEntity criarCarteira(@RequestBody @Valid CarteiraCreateDto carteiraCreateDto, HttpServletRequest request, UriComponentsBuilder uriComponentsBuilder){
        var carteira = carteiraService.criarCarteira(carteiraCreateDto, request);
        var uri = uriComponentsBuilder.path("/carteiras/{id}").buildAndExpand(carteira.id()).toUri();
        return ResponseEntity.created(uri).body(carteira);
    }

    public ResponseEntity editarCarteira(){
        return ResponseEntity.ok("");
    }

    public ResponseEntity deletarCarteira(){
        return ResponseEntity.ok("");
    }

}
