package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetControllerTest {

    @Mock
    private PetService petService;

    @InjectMocks
    private PetController petController;

    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pet = new Pet();
        pet.setId(1L);
        pet.setNome("Rex");
    }

    @Test
    void testGetAllPets() {
        when(petService.findAll()).thenReturn(Arrays.asList(pet));

        ResponseEntity<List<Pet>> response = petController.getAllPets();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        assertEquals(1, response.getBody().size());
        verify(petService, times(1)).findAll();
    }

    @Test
    void testGetPetById_Success() {
        when(petService.findById(1L)).thenReturn(Optional.of(pet));

        ResponseEntity<Pet> response = petController.getPetById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).findById(1L);
    }

    @Test
    void testGetPetById_NotFound() {
        when(petService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Pet> response = petController.getPetById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(petService, times(1)).findById(1L);
    }

    @Test
    void testCreatePet_Success() {
        when(petService.save(any(Pet.class), anyLong())).thenReturn(pet);

        ResponseEntity<Pet> response = petController.createPet(pet, 1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).save(any(Pet.class), anyLong());
    }

    @Test
    void testCreatePet_BadRequest() {
        when(petService.save(any(Pet.class), anyLong())).thenThrow(new IllegalArgumentException());

        ResponseEntity<Pet> response = petController.createPet(pet, 1L);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(petService, times(1)).save(any(Pet.class), anyLong());
    }

    @Test
    void testUpdatePetById_Success() {
        when(petService.updateById(1L, pet)).thenReturn(pet);

        ResponseEntity<Pet> response = petController.updatePetById(1L, pet);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
        verify(petService, times(1)).updateById(1L, pet);
    }

    @Test
    void testUpdatePetById_NotFound() {
        when(petService.updateById(1L, pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.updatePetById(1L, pet);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(petService, times(1)).updateById(1L, pet);
    }

    @Test
    void testDeletePetById_Success() {
        // Mocking the service
        when(petService.delete(1L)).thenReturn(true);

        ResponseEntity<Boolean> response = petController.deletePetById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
        verify(petService, times(1)).delete(1L);
    }

    @Test
    void testDeletePetById_NotFound() {
        // Mocking the service to throw an exception
        when(petService.delete(1L)).thenThrow(new RuntimeException());

        ResponseEntity<Boolean> response = petController.deletePetById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(petService, times(1)).delete(1L);
    }
}
