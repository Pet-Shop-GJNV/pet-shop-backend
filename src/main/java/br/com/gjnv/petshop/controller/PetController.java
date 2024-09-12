package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/pet")
@RestController
public class PetController {


    Pet pet1 = new Pet(1L, "dog", 10, "caramelo", "cliente");
    Pet pet2 = new Pet(2L, "cachorro", 6, "vira-lata", "cliente");
    List<Pet> pets = new ArrayList<>(Arrays.asList(pet1, pet2));


    @GetMapping()
    public ResponseEntity <List<Pet>> getAllPets(){
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id){
        try{
            Pet pet = pets.get(Integer.parseInt(String.valueOf(id)) - 1);
            return ResponseEntity.ok(pet);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet petPraSalvar){
        pets.add(petPraSalvar);
        return ResponseEntity.status(HttpStatus.CREATED).body(petPraSalvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePetById(@PathVariable Long id, @RequestBody Pet petParaEditar){
        try{
            pets.set(id.intValue()-1, petParaEditar);
            return ResponseEntity.ok(petParaEditar);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pet> deletePetById(@PathVariable Long id){
        try{
            Pet pet = pets.get(id.intValue()-1);
            pets.remove(pet);
            return ResponseEntity.ok().body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
 }
