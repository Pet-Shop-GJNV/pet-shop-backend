package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Agendamentos", description = "Gerencia os atendimentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um agendamento especificado pelo ID")
    public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable int id) {
        Agendamento agendamento = agendamentoService.getAgendamentoById(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    @Operation(summary = "Cria um novo agendamento")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDto agendamentoDto) {
        Agendamento novoAgendamento = agendamentoService.createAgendamento(agendamentoDto);
        return ResponseEntity.ok(novoAgendamento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um agendamento existente")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable int id, @RequestBody AgendamentoDto agendamentoDto) {
        Agendamento agendamentoAtualizado = agendamentoService.updateAgendamento(id, agendamentoDto);
        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um agendamento existente")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable int id) {
        agendamentoService.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
