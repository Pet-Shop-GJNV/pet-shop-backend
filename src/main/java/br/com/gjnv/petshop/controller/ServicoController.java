package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.ServicoDto;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
public class ServicoController {


    @Autowired
    private ServicoService servicoService;

    @GetMapping("/consultar")
    public List<Servico> consultarServico() {
        return servicoService.listarServicos();
    }

    @GetMapping("/{id}")
    public Optional<Servico> consultarServico(@PathVariable int id) {
        return servicoService.consultarServico(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarServico(@PathVariable int id) {
        try {
            servicoService.cancelarServico(id);
            return ResponseEntity.ok("Serviço cancelado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Serviço não encontrado.");
        }
    }

    @PostMapping("/adicionar/{id}")
    public void adicionarServico(@RequestBody Servico servico) {
        servicoService.adicionarServico(servico);
    }
}
