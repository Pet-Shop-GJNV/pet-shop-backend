package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Pet pet;

    @BeforeEach
    void setUp() {
        pet = new Pet();
        pet.setNome("Rex");
        pet.setRaca("Cachorro");
        testEntityManager.persist(pet);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Pet> pets = petRepository.findAll();
        assertThat(pets).hasSize(1);
        assertThat(pets.get(0).getNome()).isEqualTo("Rex");
    }

    @Test
    void testSave() {
        Pet novoPet = new Pet();
        novoPet.setNome("Miau");
        novoPet.setRaca("Gato");

        petRepository.save(novoPet);
        List<Pet> pets = petRepository.findAll();
        assertThat(pets).hasSize(2);
        assertThat(pets.get(1).getNome()).isEqualTo("Miau");
    }

    @Test
    void testDelete() {
        petRepository.delete(pet);
        testEntityManager.flush();

        List<Pet> pets = petRepository.findAll();
        assertThat(pets).isEmpty();
    }

    @Test
    void testFindById() {
        Long id = pet.getId();
        Optional<Pet> foundPet = petRepository.findById(id);
        assertThat(foundPet).isPresent();
        assertThat(foundPet.get().getNome()).isEqualTo("Rex");
    }
}
