package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.PagamentoFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
@Tag(name = "Pagamento", description = "Gerencia os pagamentos")
public class PagamentoController {

    private final PagamentoFacade pagamentoFacade;

    @Autowired
    public PagamentoController(PagamentoFacade pagamentoFacade) {
        this.pagamentoFacade = pagamentoFacade;
    }

    @PostMapping("/pix")
    @Operation(summary = "Realiza um pagamento via PIX")
    public ResponseEntity<String> realizarPagamentoPix(@RequestParam double valor, @RequestParam int servicoId) {
        // Valida o pagamento via PIX e compara o valor com o preço do serviço
        if (!pagamentoFacade.realizarPagamentoPix(valor, servicoId)) {
            return ResponseEntity.badRequest().body("Valor inválido ou serviço não encontrado.");
        }
        return ResponseEntity.ok("Pagamento realizado com sucesso via PIX.");
    }

    @PostMapping("/dinheiro")
    @Operation(summary = "Realiza um pagamento em dinheiro")
    public ResponseEntity<String> realizarPagamentoDinheiro(@RequestParam double valor, @RequestParam int servicoId) {
        String resultado = pagamentoFacade.realizarPagamentoDinheiro(valor, servicoId);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/credito")
    @Operation(summary = "Realiza um pagamento no cartão de crédito")
    public ResponseEntity<String> realizarPagamentoCredito(@RequestParam double valor, @RequestParam int servicoId) {
        String resultado = pagamentoFacade.realizarPagamentoCredito(valor, servicoId);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/debito")
    @Operation(summary = "Realiza um pagamento no cartão de débito")
    public ResponseEntity<String> realizarPagamentoDebito(@RequestParam double valor, @RequestParam int servicoId) {
        String resultado = pagamentoFacade.realizarPagamentoDebito(valor, servicoId);
        return ResponseEntity.ok(resultado);
    }
}
