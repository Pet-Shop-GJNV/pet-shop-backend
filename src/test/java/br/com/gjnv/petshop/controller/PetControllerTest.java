package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.ClienteRepository;
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

    @InjectMocks
    private PetController petController;

    @Mock
    private PetService petService;

    @Mock
    private ClienteRepository clienteRepository;

    private Pet pet;
    private Cliente cliente;
    private PetDto petDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando Cliente e Pet
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");

        pet = new Pet();
        pet.setId(1L);
        pet.setNome("Rex");
        pet.setIdade(3);
        pet.setRaca("Golden Retriever");
        pet.setCliente(cliente);

        petDto = new PetDto();
        petDto.setId(1L);
        petDto.setNome("Rex");
        petDto.setIdade(3);
        petDto.setRaca("Golden Retriever");
        petDto.setClienteId(1L);
    }

    @Test
    void testGetAllPets() {
        // Simulando o retorno de uma lista de pets
        when(petService.findAll()).thenReturn(Arrays.asList(pet));

        ResponseEntity<List<PetDto>> response = petController.getAllPets();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Rex", response.getBody().get(0).getNome());
    }

    @Test
    void testGetPetById() {
        // Simulando o retorno de um Pet por ID
        when(petService.findById(1L)).thenReturn(Optional.of(pet));

        ResponseEntity<PetDto> response = petController.getPetById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Rex", response.getBody().getNome());
    }

    @Test
    void testGetPetByIdNotFound() {
        // Simulando o caso em que o Pet não é encontrado
        when(petService.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<PetDto> response = petController.getPetById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testCreatePet() {
        // Simulando o cliente encontrado e a criação de um Pet
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(petService.save(any(Pet.class))).thenReturn(pet);

        ResponseEntity<Pet> response = petController.createPet(petDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Rex", response.getBody().getNome());
    }

    @Test
    void testCreatePetClienteNotFound() {
        // Simulando o caso em que o cliente não é encontrado
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Pet> response = petController.createPet(petDto);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testUpdatePetById() {
        // Simulando a atualização de um Pet
        when(petService.updateById(1L, pet)).thenReturn(pet);

        ResponseEntity<Pet> response = petController.updatePetById(1L, pet);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Rex", response.getBody().getNome());
    }

    @Test
    void testUpdatePetByIdNotFound() {
        // Simulando o caso em que o Pet não é encontrado para atualização
        when(petService.updateById(1L, pet)).thenReturn(null);

        ResponseEntity<Pet> response = petController.updatePetById(1L, pet);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeletePetById() {
        // Simulando a exclusão de um Pet
        when(petService.delete(1L)).thenReturn(true);

        ResponseEntity<Void> response = petController.deletePetById(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeletePetByIdNotFound() {
        // Simulando o caso em que o Pet não é encontrado para exclusão
        when(petService.delete(1L)).thenReturn(false);

        ResponseEntity<Void> response = petController.deletePetById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeletePetByIdInternalServerError() {
        // Simulando uma exceção durante a exclusão
        when(petService.delete(1L)).thenThrow(new RuntimeException());

        ResponseEntity<Void> response = petController.deletePetById(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
