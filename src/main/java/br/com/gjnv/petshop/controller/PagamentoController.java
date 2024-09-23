package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Gerencia os pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @Autowired
    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/pix")
    @Operation(summary = "Realiza um pagamento via PIX")
    public ResponseEntity<String> realizarPagamentoPix(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoPix(valor);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/dinheiro")
    @Operation(summary = "Realiza um pagamento em dinheiro")
    public ResponseEntity<String> realizarPagamentoDinheiro(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoDinheiro(valor);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/credito")
    @Operation(summary = "Realiza um pagamento no cartão de crédito")
    public ResponseEntity<String> realizarPagamentoCredito(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoCredito(valor);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/debito")
    @Operation(summary = "Realiza um pagamento no cartão de débito")
    public ResponseEntity<String> realizarPagamentoDebito(@RequestParam double valor) {
        String resultado = pagamentoService.pagamentoDebito(valor);
        return ResponseEntity.ok(resultado);
    }
}
