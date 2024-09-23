package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/pix")
    public ResponseEntity<String> realizarPagamentoPix(@RequestParam double valor, @RequestBody Servico servico) {
        try {
            String resultado = pagamentoService.pagamentoPix(valor, servico);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Valor inválido.");
        }
    }

    @PostMapping("/dinheiro")
    public ResponseEntity<String> realizarPagamentoDinheiro(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoDinheiro(valor);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/credito")
    public ResponseEntity<String> realizarPagamentoCredito(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoCredito(valor);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/debito")
    public ResponseEntity<String> realizarPagamentoDebito(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoDebito(valor);
        return ResponseEntity.ok(resultado);
    }
}
