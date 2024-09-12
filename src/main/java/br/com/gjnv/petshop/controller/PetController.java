package br.com.gjnv.petshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/pet")
@RestController
public class PetController {

    @GetMapping("/")
    public ResponseEntity<String> getAllPets(){
        return ResponseEntity.ok("devo retornar todos os pets");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getPetById(@PathVariable Long id){
        return ResponseEntity.ok("devo retornar o pet "+id+" se encontrado ou informar que não achei");
    }

    @PostMapping
    public ResponseEntity<String> createPet(@RequestBody String oPetPraSalvar){
        return ResponseEntity.status(HttpStatus.CREATED).body("devo salvar o pet "+oPetPraSalvar+" ou informar que não salvei");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePetById(@PathVariable Long id){
        return ResponseEntity.ok("devo atualizar o pet "+id+" se encontrado ou informar que não achei");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id){
        return ResponseEntity.ok("devo matar o pet "+id+" se encontrado ou informar que não achei");
    }
 }
