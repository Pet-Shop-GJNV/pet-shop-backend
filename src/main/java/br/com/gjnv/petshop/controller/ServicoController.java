package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.ServicoDto;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/{id}")
    public List<Servico> consultarServico(@PathVariable int id) {
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

    @PostMapping("/agendar/{id}")
    public ResponseEntity<String> agendarServico(@PathVariable int id, @RequestParam boolean vagaDisponivel) {
        try {
            servicoService.agendarServico(id, vagaDisponivel);
            return ResponseEntity.ok("Serviço agendado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao agendar o serviço.");
        }
    }
}
