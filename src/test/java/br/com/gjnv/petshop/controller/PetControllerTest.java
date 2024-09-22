//package br.com.gjnv.petshop.controller;
//
//import br.com.gjnv.petshop.dto.PetDto;
//import br.com.gjnv.petshop.model.Pet;
//import br.com.gjnv.petshop.service.PetService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class PetControllerTest {
//
//    @Mock
//    private PetService petService;
//
//    @InjectMocks
//    private PetController petController;
//
//    private Pet pet1;
//    private Pet pet2;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        pet1 = new Pet(1L, "Rex", 5, "Labrador", "João");
//        pet2 = new Pet(2L, "Max", 3, "Poodle", "Maria");
//    }
//
//    @Test
//    public void testGetAllPets() {
//        List<Pet> pets = Arrays.asList(pet1, pet2);
//        when(petService.findAll()).thenReturn(pets);
//
//        ResponseEntity<List<PetDto>> response = petController.getAllPets();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(2, response.getBody().size());
//        verify(petService, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetPetById_Found() {
//        when(petService.findById(1L)).thenReturn(Optional.of(pet1));
//
//        ResponseEntity<PetDto> response = petController.getPetById(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(pet1, response.getBody());
//        verify(petService, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testGetPetById_NotFound() {
//        when(petService.findById(1L)).thenReturn(Optional.empty());
//
//        ResponseEntity<PetDto> response = petController.getPetById(1L);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verify(petService, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testCreatePet() {
//        when(petService.save(pet1)).thenReturn(pet1);
//
//        ResponseEntity<Pet> response = petController.createPet(pet1);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(pet1, response.getBody());
//        verify(petService, times(1)).save(pet1);
//    }
//
//    @Test
//    public void testUpdatePetById_Found() {
//        when(petService.updateById(1L, pet2)).thenReturn(pet2);
//
//        ResponseEntity<Pet> response = petController.updatePetById(1L, pet2);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(pet2, response.getBody());
//        verify(petService, times(1)).updateById(1L, pet2);
//    }
//
//    @Test
//    public void testUpdatePetById_NotFound() {
//        when(petService.updateById(1L, pet2)).thenReturn(null);
//
//        ResponseEntity<Pet> response = petController.updatePetById(1L, pet2);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verify(petService, times(1)).updateById(1L, pet2);
//    }
//
//    @Test
//    public void testDeletePetById_Success() {
//        when(petService.delete(1L)).thenReturn(true);
//
//        ResponseEntity<Boolean> response = petController.deletePetById(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.getBody());
//        verify(petService, times(1)).delete(1L);
//    }
//
//    @Test
//    public void testDeletePetById_Failure() {
//        doThrow(new RuntimeException()).when(petService).delete(1L);
//
//        ResponseEntity<Boolean> response = petController.deletePetById(1L);
//
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//        verify(petService, times(1)).delete(1L);
//    }
//}