package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.exception.Pet.PetIdadeInvalidaException;
import br.com.gjnv.petshop.exception.Pet.PetInvalidoException;
import br.com.gjnv.petshop.exception.Pet.PetNaoEncontradoException;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    public ClienteRepository clienteRepository;

    @Autowired
    public PetRepository petRepository;

    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
        //.orElseThrow(() -> new PetNaoEncontradoException(id));
        // TODO: Coloco aqui, pois leva alteração no Controller.
    }

    public Pet save(Pet pet) {
        if (pet.getNome() == null || pet.getNome().isEmpty()) {
            throw new PetInvalidoException();
        }
        if (pet.getIdade() < 0) {
            throw new PetIdadeInvalidaException();
        }
        if(pet.getRaca() == null || pet.getRaca().isEmpty()) {
            throw new PetInvalidoException();
        }
        return petRepository.save(pet);
    }

    public Pet updateById(Long id, Pet novoPet) {
        if (novoPet.getNome() == null || novoPet.getNome().isEmpty()) {
            throw new PetInvalidoException();
        }
        if (novoPet.getIdade() < 0) {
            throw new PetIdadeInvalidaException();
        }
        return petRepository.findById(id)
                .map(pet -> {
                    pet.setCliente(novoPet.getCliente());
                    pet.setIdade(novoPet.getIdade());
                    pet.setRaca(novoPet.getRaca());
                    pet.setNome(novoPet.getNome());
                    return petRepository.save(pet);
                })
                .orElseThrow(() -> new PetNaoEncontradoException(id));
    }

    public boolean delete(Long id) {
        return petRepository.findById(id)
                .map(pet -> {
                    petRepository.delete(pet);
                    return true;
                })
                .orElseThrow(() -> new PetNaoEncontradoException(id));
    }
}