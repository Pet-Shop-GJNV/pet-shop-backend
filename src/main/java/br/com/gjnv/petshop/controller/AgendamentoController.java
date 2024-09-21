package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable int id) {
        Agendamento agendamento = agendamentoService.getAgendamentoById(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDto agendamentoDto) {
        Agendamento novoAgendamento = agendamentoService.createAgendamento(agendamentoDto);
        return ResponseEntity.ok(novoAgendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable int id, @RequestBody AgendamentoDto agendamentoDto) {
        Agendamento agendamentoAtualizado = agendamentoService.updateAgendamento(id, agendamentoDto);
        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable int id) {
        agendamentoService.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
