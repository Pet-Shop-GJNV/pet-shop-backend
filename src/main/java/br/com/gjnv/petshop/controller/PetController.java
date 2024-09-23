package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.facade.PetFacade;
import br.com.gjnv.petshop.model.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/pet")
@RestController
@Tag(name = "Pet", description = "Gerencia os pets")
public class PetController {

    private final PetFacade petFacade;

    @Autowired
    public PetController(PetFacade petFacade) {
        this.petFacade = petFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna todos os pets")
    public ResponseEntity<List<PetDto>> getAllPets() {
        List<PetDto> petDTOs = petFacade.listarTodosPets();
        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um pet especifico")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id) {
        Optional<PetDto> pet = petFacade.buscarPetPorId(id);
        return pet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping
    @Operation(summary = "Cria um novo pet")
    public ResponseEntity<Pet> createPet(@RequestBody PetDto petDto) {
        try {
            Optional<Pet> novoPet = petFacade.criarPet(petDto);
            return novoPet.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pet existente")
    public ResponseEntity<Pet> updatePetById(@PathVariable Long id, @RequestBody PetDto petDto) {
        Optional<Pet> updatedPet = petFacade.atualizarPet(id, petDto);
        return updatedPet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pet existente")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        if (petFacade.deletarPet(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
