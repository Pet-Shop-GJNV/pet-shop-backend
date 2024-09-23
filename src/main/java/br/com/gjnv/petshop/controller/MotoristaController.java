package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.MotoristaFacade;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/motoristas")
@Tag(name = "Motorista", description = "Gerencia atividades do motorista")
public class MotoristaController {

    private final MotoristaFacade motoristaFacade;

    @Autowired
    public MotoristaController(MotoristaFacade motoristaFacade) {
        this.motoristaFacade = motoristaFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os motoristas")
    public List<Motorista> getAllMotoristas() {
        return motoristaFacade.listarTodosMotoristas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um motorista especificado pelo ID")
    public ResponseEntity<Motorista> getMotoristaById(@PathVariable UUID id) {
        Optional<Motorista> motorista = motoristaFacade.buscarMotoristaPorId(id);
        return motorista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo motorista")
    public ResponseEntity<Motorista> createMotorista(@RequestBody Motorista motorista) {
        try {
            Motorista savedMotorista = motoristaFacade.criarMotorista(motorista);
            return ResponseEntity.ok(savedMotorista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um motorista existente")
    public ResponseEntity<Motorista> updateMotorista(@PathVariable UUID id, @RequestBody Motorista motoristaDetails) {
        Optional<Motorista> updatedMotorista = motoristaFacade.atualizarMotorista(id, motoristaDetails);
        return updatedMotorista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um motorista existente")
    public ResponseEntity<Void> deleteMotorista(@PathVariable UUID id) {
        if (motoristaFacade.excluirMotorista(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/coleta")
    @Operation(summary = "Cadastra um endereço de coleta para um motorista")
    public ResponseEntity<Void> realizarColeta(@PathVariable UUID id, @RequestBody Endereco endereco) {
        if (motoristaFacade.realizarColeta(id, endereco)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/entrega")
    @Operation(summary = "Cadastra um endereço de entrega para um motorista")
    public ResponseEntity<Void> realizarEntrega(@PathVariable UUID id, @RequestBody Endereco endereco) {
        if (motoristaFacade.realizarEntrega(id, endereco)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
