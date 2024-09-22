package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.ClienteService;
import br.com.gjnv.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/pet")
@RestController
public class PetController {

    @Autowired
    public PetService petService;

    @Autowired
    public ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<PetDto>> getAllPets() {
        List<Pet> pets = petService.findAll();
        List<PetDto> petDTOs = pets.stream().map(pet -> {
            PetDto dto = new PetDto();
            dto.setNome(pet.getNome());
            dto.setIdade(pet.getIdade());
            dto.setRaca(pet.getRaca());
            dto.getClienteId();
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(petDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petService.findById(id);
        if (pet.isPresent()) {
            PetDto dto = new PetDto();
            dto.setNome(pet.get().getNome());
            dto.setIdade(pet.get().getIdade());
            dto.setRaca(pet.get().getRaca());
            dto.setClienteId(id);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody PetDto petDto) {
        try {
            Optional<Cliente> optionalCliente = clienteService.findById(petDto.getId());
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
    public ResponseEntity<Pet> updatePetById(@PathVariable Long id, @RequestBody Pet petParaEditar){
        Pet pet = petService.updateById(id, petParaEditar);
            if(pet != null) {
                return ResponseEntity.ok(pet);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pet);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePetById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(petService.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
 }
