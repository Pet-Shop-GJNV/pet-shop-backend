package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.ServicoFacade;
import br.com.gjnv.petshop.model.Servico;
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

    private final ServicoFacade servicoFacade;

    @Autowired
    public ServicoController(ServicoFacade servicoFacade) {
        this.servicoFacade = servicoFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os serviços")
    public List<Servico> consultarServico() {
        return servicoFacade.listarTodosServicos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um serviço especificado pelo ID")
    public ResponseEntity<Servico> consultarServico(@PathVariable int id) {
        Optional<Servico> servico = servicoFacade.consultarServicoPorId(id);
        return servico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um serviço existente")
    public ResponseEntity<String> cancelarServico(@PathVariable int id) {
        try {
            servicoFacade.cancelarServico(id);
            return ResponseEntity.ok("Serviço cancelado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Serviço não encontrado.");
        }
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adiciona um serviço")
    public ResponseEntity<Void> adicionarServico(@RequestBody Servico servico) {
        try {
            servicoFacade.adicionarServico(servico);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
