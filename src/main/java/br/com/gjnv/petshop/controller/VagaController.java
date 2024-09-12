package br.com.gjnv.petshop.controller;
import br.com.gjnv.petshop.dto.VagaDto;
import br.com.gjnv.petshop.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping("/verificarDisponibilidade")
    public ResponseEntity<Boolean> verificarDisponibilidade(
            @RequestParam LocalTime horario,
            @RequestParam LocalDate data) {
        return ResponseEntity.ok(vagaService.verificarDisponibilidade(horario, data) != null);
    }

    @PostMapping("/agendar")
    public ResponseEntity<Void> agendarHorario(
            @RequestParam Integer id,
            @RequestParam LocalTime horario,
            @RequestParam LocalDate data) {
        vagaService.agendarHorario(id, horario, data);
        return ResponseEntity.ok().build();
    }
}
