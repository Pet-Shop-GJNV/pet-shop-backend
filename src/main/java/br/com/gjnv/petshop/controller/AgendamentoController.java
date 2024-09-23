package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.facade.AgendamentoFacade;
import br.com.gjnv.petshop.model.Agendamento;
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

    private final AgendamentoFacade agendamentoFacade;

    @Autowired
    public AgendamentoController(AgendamentoFacade agendamentoFacade) {
        this.agendamentoFacade = agendamentoFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoFacade.listarAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um agendamento especificado pelo ID")
    public ResponseEntity<Agendamento> buscarAgendamentoPorId(@PathVariable int id) {
        Agendamento agendamento = agendamentoFacade.buscarAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    @Operation(summary = "Cria um novo agendamento")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody AgendamentoDto agendamentoDto) {
        Agendamento novoAgendamento = agendamentoFacade.criarAgendamento(agendamentoDto);
        return ResponseEntity.ok(novoAgendamento);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um agendamento existente")
    public ResponseEntity<Agendamento> atualizarAgendamento(@PathVariable int id, @RequestBody AgendamentoDto agendamentoDto) {
        Agendamento agendamentoAtualizado = agendamentoFacade.atualizarAgendamento(id, agendamentoDto);
        return ResponseEntity.ok(agendamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um agendamento existente")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable int id) {
        agendamentoFacade.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }
}
