package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.facade.GerenteFacade;
import br.com.gjnv.petshop.model.Gerente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/gerentes")
@Tag(name = "Gerente", description = "Gerencia os gerentes")
public class GerenteController {

    private final GerenteFacade gerenteFacade;

    @Autowired
    public GerenteController(GerenteFacade gerenteFacade) {
        this.gerenteFacade = gerenteFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os gerentes")
    public List<Gerente> getAllGerentes() {
        return gerenteFacade.getAllGerentes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um gerente especificado pelo ID")
    public ResponseEntity<Gerente> getGerenteById(@PathVariable UUID id) {
        Optional<Gerente> gerente = gerenteFacade.getGerenteById(id);
        return gerente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cria um novo gerente")
    public ResponseEntity<Gerente> createGerente(@RequestBody GerenteDto gerenteDto) {
        Gerente gerente = gerenteFacade.createGerente(gerenteDto);
        return ResponseEntity.ok(gerente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um gerente existente")
    public ResponseEntity<Gerente> updateGerente(@PathVariable UUID id, @RequestBody GerenteDto gerenteDto) {
        Optional<Gerente> updatedGerente = gerenteFacade.updateGerente(id, gerenteDto);
        return updatedGerente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um gerente existente")
    public ResponseEntity<Void> deleteGerente(@PathVariable UUID id) {
        if (gerenteFacade.deleteGerente(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
