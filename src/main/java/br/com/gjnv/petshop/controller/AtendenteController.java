package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.AtendenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/atendentes")
@Tag(name = "Atendente", description = "Gerencia os atendentes")
public class AtendenteController {

    private final AtendenteService atendenteService;

    @Autowired
    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os atendentes")
    public List<Atendente> getAllAtendentes() {
        return atendenteService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um atendente especificado pelo ID")
    public ResponseEntity<Atendente> getAtendenteById(@PathVariable UUID id) {
        Optional<Atendente> atendente = atendenteService.findById(id);
        return atendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo atendente")
    public Atendente createAtendente(@RequestBody Atendente atendente) {
        return atendenteService.save(atendente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um atendente existente")
    public ResponseEntity<Atendente> updateAtendente(@PathVariable UUID id, @RequestBody Atendente atendenteDetails) {
        Optional<Atendente> updatedAtendente = atendenteService.update(id, atendenteDetails);
        return updatedAtendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um atendente existente")
    public ResponseEntity<Void> deleteAtendente(@PathVariable UUID id) {
        boolean deleted = atendenteService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/atualizar-status-servico")
    @Operation(summary = "Atualiza o status de um serviço existente")
    public ResponseEntity<Void> atualizarStatusServico(@PathVariable UUID id) {
        atendenteService.atualizarStatusServico(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clientes")
    @Operation(summary = "Cadastra um novo cliente")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        atendenteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/clientes/{id}")
    @Operation(summary = "Consulta um cliente pelo ID")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable Long id) {
        Cliente cliente = atendenteService.consultarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    @Operation(summary = "Exclui um cliente pelo ID")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        atendenteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clientes/{id}")
    @Operation(summary = "Atualiza um cliente pelo ID")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        atendenteService.atualizarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/pets")
    @Operation(summary = "Cadastra um novo pet")
    public ResponseEntity<Pet> cadastrarPet(@RequestParam Long clienteId, @RequestBody Pet pet) {
        atendenteService.cadastrarPet(clienteId, pet);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/pets/{id}")
    @Operation(summary = "Consulta um pet pelo ID")
    public ResponseEntity<Pet> consultarPet(@PathVariable Long id) {
        Pet pet = atendenteService.consultarPet(id);
        return ResponseEntity.ok(pet);
    }

    @DeleteMapping("/pets/{id}")
    @Operation(summary = "Exclui um pet pelo ID")
    public ResponseEntity<Void> excluirPet(@PathVariable Long id) {
        atendenteService.excluirPet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pets/{petId}")
    @Operation(summary = "Atualiza um pet pelo ID")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Long petId, @RequestBody Pet pet) {
        pet.setId(petId);
        atendenteService.atualizarPet(pet);
        return ResponseEntity.ok(pet);
    }
}