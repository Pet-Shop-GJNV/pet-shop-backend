package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PetService {



    Pet pet1 = new Pet(1L, "dog", 10, "caramelo", "cliente");
    Pet pet2 = new Pet(2L, "cachorro", 6, "vira-lata", "cliente");
    List<Pet> pets = new ArrayList<>(Arrays.asList(pet1, pet2));

    public List<Pet> findAll(){
        return pets;
    }

    public Pet findById(Long id){
        try{
            Pet pet = pets.get(Integer.parseInt(String.valueOf(id)) - 1);
            return pet;
        }catch (Exception e){
            return null;
        }
    }

    public Pet save(Pet pet){
        pets.add(pet);
        return pet;
    }

    public Pet updateById(Long id, Pet novoPet){
        Pet petAntigo = findById(id);
        if(petAntigo != null) {
            pets.set(id.intValue() - 1, novoPet);
            return novoPet;
        }
        return null;
    }

    public boolean delete(Long id){
        Pet pet = pets.get(id.intValue()-1);
        return pets.remove(pet);
    }

}
