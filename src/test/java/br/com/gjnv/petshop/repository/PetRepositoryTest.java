package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Pet pet;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        clienteRepository.save(cliente);

        pet = new Pet();
        pet.setNome("Rex");
        pet.setCliente(cliente);
    }

    @Test
    @Rollback(value = false)
    void testSave() {
        Pet savedPet = petRepository.save(pet);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals("Rex", savedPet.getNome());
        assertEquals(cliente.getId(), savedPet.getCliente().getId());
    }

    @Test
    void testFindById() {
        Pet savedPet = petRepository.save(pet);
        Optional<Pet> foundPet = petRepository.findById(savedPet.getId());

        assertTrue(foundPet.isPresent());
        assertEquals(savedPet.getNome(), foundPet.get().getNome());
        assertEquals(savedPet.getCliente().getId(), foundPet.get().getCliente().getId());
    }

    @Test
    void testDelete() {
        Pet savedPet = petRepository.save(pet);
        petRepository.delete(savedPet);

        Optional<Pet> foundPet = petRepository.findById(savedPet.getId());
        assertFalse(foundPet.isPresent());
    }

    @Test
    void testFindAll() {
        petRepository.save(pet);
        Pet anotherPet = new Pet();
        anotherPet.setNome("Max");
        anotherPet.setCliente(cliente);
        petRepository.save(anotherPet);

        List<Pet> pets = petRepository.findAll();
        assertNotNull(pets);
        assertEquals(2, pets.size());
    }
}
