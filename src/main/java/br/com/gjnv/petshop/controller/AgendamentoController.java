package br.com.gjnv.petshop.controller;
import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody AgendamentoDto agendamentoDTO) {
        Agendamento agendamento = agendamentoService.createAgendamento(agendamentoDTO);
        return new ResponseEntity<>(agendamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> updateAgendamento(@PathVariable int id, @RequestBody AgendamentoDto agendamentoDTO) {
        Agendamento agendamento = agendamentoService.updateAgendamento(id, agendamentoDTO);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable int id) {
        agendamentoService.deleteAgendamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> getAgendamentoById(@PathVariable int id) {
        Agendamento agendamento = agendamentoService.getAgendamentoById(id);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }
}
