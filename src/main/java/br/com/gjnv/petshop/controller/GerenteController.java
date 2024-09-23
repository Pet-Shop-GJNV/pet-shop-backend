package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public List<Gerente> getAllGerentes() {
        return gerenteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> getGerenteById(@PathVariable UUID id) {
        Optional<Gerente> gerente = gerenteService.findById(id);
        return gerente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gerente createGerente(@RequestBody GerenteDto gerenteDto) {
        return gerenteService.save(gerenteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> updateGerente(@PathVariable UUID id, @RequestBody GerenteDto gerenteDto) {
        Optional<Gerente> updatedGerente = gerenteService.update(id, gerenteDto);
        return updatedGerente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGerente(@PathVariable UUID id) {
        if (gerenteService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        gerenteService.cadastrarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> consultarCliente(@PathVariable Long id) {
        Cliente cliente = gerenteService.consultarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        gerenteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        gerenteService.atualizarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }
}