package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/pet")
@RestController
public class PetController {



    @Autowired
    public PetService petService;


    @GetMapping()
    public ResponseEntity <List<Pet>> getAllPets(){
        return ResponseEntity.ok(petService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id){
            Optional<Pet> pet = petService.findById(id);
            if(pet.isPresent()){
                return ResponseEntity.ok(pet.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }


    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet petPraSalvar, @RequestParam Long clienteId){
        try{
            Pet novoPet = petService.save(petPraSalvar, clienteId);
            return ResponseEntity.ok(novoPet);
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
