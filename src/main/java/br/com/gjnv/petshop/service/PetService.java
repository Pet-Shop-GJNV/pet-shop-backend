package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    public PetRepository petRepository;

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id){
            return petRepository.findById(id);
    }

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public Pet updateById(Long id, Pet novoPet){
        Optional<Pet> petAntigo = findById(id);
        if(petAntigo.isPresent()) {
            Pet p = petAntigo.get();
            p.setCliente(novoPet.getCliente());
            p.setIdade(novoPet.getIdade());
            p.setRaca(novoPet.getRaca());
            p.setNome(novoPet.getNome());
            petRepository.save(p);
            return p;
        }
        return null;
    }

    public boolean delete(Long id){
        Optional<Pet> pet = petRepository.findById(id);
        if(pet.isPresent()){
            petRepository.delete(pet.get());
            return true;
        }

        return false;
    }

}
