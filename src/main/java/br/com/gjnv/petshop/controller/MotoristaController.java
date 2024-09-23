package br.com.gjnv.petshop.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.service.MotoristaService;

@RestController
@RequestMapping("/motoristas")
@Tag(name = "Motorista", description = "Gerencia atividades do motorista")
public class MotoristaController {

    private final MotoristaService motoristaService;

    @Autowired
    public MotoristaController(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os motoristas")
    public List<Motorista> getAllMotoristas() {
        return motoristaService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um motorista especificado pelo ID")
    public ResponseEntity<Motorista> getMotoristaById(@PathVariable UUID id) {
        Optional<Motorista> motorista = motoristaService.findById(id);
        return motorista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um motorista existente")
    public ResponseEntity<Motorista> updateMotorista(@PathVariable UUID id, @RequestBody Motorista motoristaDetails) {
        Optional<Motorista> updatedMotorista = motoristaService.update(id, motoristaDetails);
        return updatedMotorista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo motorista")
    public ResponseEntity<Motorista> createMotorista(@RequestBody Motorista motorista) {
        try {
            Motorista savedMotorista = motoristaService.save(motorista);
            return ResponseEntity.ok(savedMotorista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um motorista existente")
    public ResponseEntity<Void> deleteMotorista(@PathVariable UUID id) {
        if (motoristaService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/coleta")
    @Operation(summary = "Cadastra um endereço de coleta para um motorista")
    public ResponseEntity<Void> realizarColeta(@PathVariable UUID id, @RequestBody Endereco endereco) {
        if (motoristaService.realizarColeta(id, endereco)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/entrega")
    @Operation(summary = "Cadastra um endereço de entrega para um motorista")
    public ResponseEntity<Void> realizarEntrega(@PathVariable UUID id, @RequestBody Endereco endereco) {
        if (motoristaService.realizarEntrega(id, endereco)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}