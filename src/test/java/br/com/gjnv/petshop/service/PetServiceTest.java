package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private ClienteRepository clienteRepository;

    private Pet pet;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando Cliente e Pet
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setCpf("123456789");
        cliente.setTelefone("987654321");

        pet = new Pet();
        pet.setId(1L);
        pet.setNome("Rex");
        pet.setIdade(3);
        pet.setRaca("Golden Retriever");
        pet.setCliente(cliente);
    }

    @Test
    void testFindAll() {
        when(petRepository.findAll()).thenReturn(Collections.singletonList(pet));

        List<Pet> pets = petService.findAll();
        assertEquals(1, pets.size());
        assertEquals("Rex", pets.get(0).getNome());
    }

    @Test
    void testFindById() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));

        Optional<Pet> foundPet = petService.findById(1L);
        assertTrue(foundPet.isPresent());
        assertEquals("Rex", foundPet.get().getNome());
    }

    @Test
    void testFindByIdNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pet> foundPet = petService.findById(1L);
        assertFalse(foundPet.isPresent());
    }

    @Test
    void testSave() {
        when(petRepository.save(pet)).thenReturn(pet);

        Pet savedPet = petService.save(pet);
        assertNotNull(savedPet);
        assertEquals("Rex", savedPet.getNome());
    }

    @Test
    void testUpdateById() {
        Pet novoPet = new Pet();
        novoPet.setNome("Max");
        novoPet.setIdade(4);
        novoPet.setRaca("Labrador");
        novoPet.setCliente(cliente);

        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        when(petRepository.save(any(Pet.class))).thenReturn(novoPet);

        Pet updatedPet = petService.updateById(1L, novoPet);

        assertNotNull(updatedPet);
        assertEquals("Max", updatedPet.getNome());
        assertEquals(4, updatedPet.getIdade());
        assertEquals("Labrador", updatedPet.getRaca());
    }

    @Test
    void testUpdateByIdNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        Pet novoPet = new Pet();
        Pet updatedPet = petService.updateById(1L, novoPet);

        assertNull(updatedPet);
    }

    @Test
    void testDelete() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        doNothing().when(petRepository).delete(pet);

        boolean deleted = petService.delete(1L);
        assertTrue(deleted);
        verify(petRepository, times(1)).delete(pet);
    }

    @Test
    void testDeleteNotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        boolean deleted = petService.delete(1L);
        assertFalse(deleted);
        verify(petRepository, never()).delete(any(Pet.class));
    }
}