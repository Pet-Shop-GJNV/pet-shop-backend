package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private Pet pet1;
    private Pet pet2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pet1 = new Pet(1L, "Rex", 5, "Labrador", "João");
        pet2 = new Pet(2L, "Max", 3, "Poodle", "Maria");
    }

    @Test
    public void testFindAll() {
        List<Pet> pets = Arrays.asList(pet1, pet2);
        when(petRepository.findAll()).thenReturn(pets);

        List<Pet> result = petService.findAll();

        assertEquals(2, result.size());
        assertEquals(pet1, result.get(0));
        assertEquals(pet2, result.get(1));
        verify(petRepository, times(1)).findAll();
    }

    @Test
    public void testFindById_Found() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet1));

        Optional<Pet> result = petService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(pet1, result.get());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pet> result = petService.findById(1L);

        assertFalse(result.isPresent());
        verify(petRepository, times(1)).findById(1L);
    }

    @Test
    public void testSave() {
        when(petRepository.save(pet1)).thenReturn(pet1);

        Pet result = petService.save(pet1);

        assertEquals(pet1, result);
        verify(petRepository, times(1)).save(pet1);
    }

    @Test
    public void testUpdateById_Found() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet1));
        when(petRepository.save(pet1)).thenReturn(pet1);

        Pet updatedPet = new Pet(1L, "Buddy", 6, "Golden Retriever", "Carlos");
        Pet result = petService.updateById(1L, updatedPet);

        assertEquals("Buddy", result.getNome());
        assertEquals(6, result.getIdade());
        assertEquals("Golden Retriever", result.getRaca());
        assertEquals("Carlos", result.getCliente());
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(1)).save(pet1);
    }

    @Test
    public void testUpdateById_NotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        Pet updatedPet = new Pet(1L, "Buddy", 6, "Golden Retriever", "Carlos");
        Pet result = petService.updateById(1L, updatedPet);

        assertNull(result);
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(0)).save(any(Pet.class));
    }

    @Test
    public void testDelete_Found() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet1));

        boolean result = petService.delete(1L);

        assertTrue(result);
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(1)).delete(pet1);
    }

    @Test
    public void testDelete_NotFound() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = petService.delete(1L);

        assertFalse(result);
        verify(petRepository, times(1)).findById(1L);
        verify(petRepository, times(0)).delete(any(Pet.class));
    }
}
