package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.dto.ShowDaFe;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/pet")
@RestController
@Tag(name = "Pet", description = "Gerencia os pets")
public class PetController {

    public final PetService petService;
    public final ClienteRepository clienteRepository;

    @Autowired
    public PetController(PetService petService, ClienteRepository clienteRepository) {
        this.petService = petService;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping()
    @Operation(summary = "Retorna todos os pets")
    public ResponseEntity<List<PetDto>> getAllPets() {
        List<Pet> pets = petService.findAll();
        List<PetDto> petDTOs = pets.stream().map(pet -> {
            PetDto dto = new PetDto();
            dto.setId(pet.getId());
            dto.setNome(pet.getNome());
            dto.setIdade(pet.getIdade());
            dto.setRaca(pet.getRaca());
            if (pet.getCliente() != null) {
                dto.setClienteId(pet.getCliente().getId());
            }
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um pet especifico")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.findById(id);
        if (pet.isPresent()) {
            PetDto dto = new PetDto();
            dto.setId(pet.get().getId());
            dto.setNome(pet.get().getNome());
            dto.setIdade(pet.get().getIdade());
            dto.setRaca(pet.get().getRaca());
            dto.setClienteId(id);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    @Operation(summary = "Cria um novo pet")
    public ResponseEntity<Pet> createPet(@RequestBody PetDto petDto) {
        try {
            Optional<Cliente> optionalCliente = clienteRepository.findById(petDto.getClienteId());
            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                Pet pet = new Pet();
                pet.setNome(petDto.getNome());
                pet.setIdade(petDto.getIdade());
                pet.setRaca(petDto.getRaca());
                pet.setCliente(cliente);
                Pet novoPet = petService.save(pet);
                return ResponseEntity.ok(novoPet);
            } else {
                throw new IllegalArgumentException("Cliente não encontrado");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um pet existente")
    public ResponseEntity<Pet> updatePetById(@PathVariable Long id, @RequestBody ShowDaFe petParaEditar) {
        Optional<Cliente> cliente = clienteRepository.findById(petParaEditar.clienteId());
        if (cliente.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        Pet petEditado = new Pet(petParaEditar.nome(), petParaEditar.idade(), petParaEditar.raca(), cliente.get());
        Pet pet = petService.updateById(id, petEditado);
        if (pet != null) {
            return ResponseEntity.ok(pet);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pet);

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um pet existente")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        try {
            if (petService.delete(id)) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}