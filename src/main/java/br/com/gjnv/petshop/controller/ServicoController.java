package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
@Tag(name = "Serviços", description = "Gerencia os serviços")
public class ServicoController {


    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os serviços")
    public List<Servico> consultarServico() {
        return servicoService.listarServicos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um serviço especificado pelo ID")
    public Optional<Servico> consultarServico(@PathVariable int id) {
        return servicoService.consultarServico(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um serviço existente")
    public ResponseEntity<String> cancelarServico(@PathVariable int id) {
        try {
            servicoService.cancelarServico(id);
            return ResponseEntity.ok("Serviço cancelado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Serviço não encontrado.");
        }
    }

    @PostMapping("/adicionar/{id}")
    @Operation(summary = "Adiciona um serviço a um endereço")
    public void adicionarServico(@RequestBody Servico servico) {
        servicoService.adicionarServico(servico);
    }
}
