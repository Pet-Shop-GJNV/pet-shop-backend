package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {

    @Autowired
    private AtendenteService atendenteService;

    @GetMapping
    public List<Atendente> getAllAtendentes() {
        return atendenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atendente> getAtendenteById(@PathVariable UUID id) {
        Optional<Atendente> atendente = atendenteService.findById(id);
        return atendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Atendente createAtendente(@RequestBody Atendente atendente) {
        return atendenteService.save(atendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atendente> updateAtendente(@PathVariable UUID id, @RequestBody Atendente atendenteDetails) {
        Optional<Atendente> updatedAtendente = atendenteService.update(id, atendenteDetails);
        return updatedAtendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendente(@PathVariable UUID id) {
        boolean deleted = atendenteService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/atualizar-status-servico")
    public ResponseEntity<Void> atualizarStatusServico(@PathVariable UUID id) {
        atendenteService.atualizarStatusServico(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        atendenteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable Long id) {
        Cliente cliente = atendenteService.consultarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        atendenteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        atendenteService.atualizarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> cadastrarPet(@RequestParam Long clienteId, @RequestBody Pet pet) {
        atendenteService.cadastrarPet(clienteId, pet);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/pets/{id}")
    public ResponseEntity<Pet> consultarPet(@PathVariable Long id) {
        Pet pet = atendenteService.consultarPet(id);
        return ResponseEntity.ok(pet);
    }

    @DeleteMapping("/pets/{id}")
    public ResponseEntity<Void> excluirPet(@PathVariable Long id) {
        atendenteService.excluirPet(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pets/{petId}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Long petId, @RequestBody Pet pet) {
        pet.setId(petId);
        atendenteService.atualizarPet(pet);
        return ResponseEntity.ok(pet);
    }
}