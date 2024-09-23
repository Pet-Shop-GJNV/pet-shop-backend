package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.AtendenteFacade;
import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AtendenteControllerTest {

    @Mock
    private AtendenteFacade atendenteFacade;

    @InjectMocks
    private AtendenteController atendenteController;

    private UUID atendenteId;
    private Atendente atendente;
    private Cliente cliente;
    private Pet pet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        atendenteId = UUID.randomUUID();
        atendente = new Atendente();
        cliente = new Cliente();
        pet = new Pet();
    }

    @Test
    void getAllAtendentes_DeveRetornarListaDeAtendentes() {
        // Arrange
        List<Atendente> atendentes = Arrays.asList(new Atendente(), new Atendente());
        when(atendenteFacade.getAllAtendentes()).thenReturn(atendentes);

        // Act
        List<Atendente> response = atendenteController.getAllAtendentes();

        // Assert
        assertEquals(atendentes, response);
    }

    @Test
    void getAtendenteById_DeveRetornarAtendente() {
        // Arrange
        when(atendenteFacade.getAtendenteById(atendenteId)).thenReturn(Optional.of(atendente));

        // Act
        ResponseEntity<Atendente> response = atendenteController.getAtendenteById(atendenteId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atendente, response.getBody());
    }

    @Test
    void getAtendenteById_DeveRetornarNotFound() {
        // Arrange
        when(atendenteFacade.getAtendenteById(atendenteId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Atendente> response = atendenteController.getAtendenteById(atendenteId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createAtendente_DeveCriarAtendente() {
        // Arrange
        when(atendenteFacade.createAtendente(atendente)).thenReturn(atendente);

        // Act
        Atendente response = atendenteController.createAtendente(atendente);

        // Assert
        assertEquals(atendente, response);
    }

    @Test
    void updateAtendente_DeveRetornarAtendenteAtualizado() {
        // Arrange
        when(atendenteFacade.updateAtendente(atendenteId, atendente)).thenReturn(Optional.of(atendente));

        // Act
        ResponseEntity<Atendente> response = atendenteController.updateAtendente(atendenteId, atendente);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(atendente, response.getBody());
    }

    @Test
    void updateAtendente_DeveRetornarNotFound() {
        // Arrange
        when(atendenteFacade.updateAtendente(atendenteId, atendente)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Atendente> response = atendenteController.updateAtendente(atendenteId, atendente);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteAtendente_DeveDeletarAtendenteComSucesso() {
        // Arrange
        when(atendenteFacade.deleteAtendente(atendenteId)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = atendenteController.deleteAtendente(atendenteId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteAtendente_DeveRetornarNotFound() {
        // Arrange
        when(atendenteFacade.deleteAtendente(atendenteId)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = atendenteController.deleteAtendente(atendenteId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void atualizarStatusServico_DeveAtualizarStatusComSucesso() {
        // Arrange
        doNothing().when(atendenteFacade).atualizarStatusServico(atendenteId);

        // Act
        ResponseEntity<Void> response = atendenteController.atualizarStatusServico(atendenteId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(atendenteFacade, times(1)).atualizarStatusServico(atendenteId);
    }

    @Test
    void cadastrarCliente_DeveCadastrarNovoCliente() {
        // Arrange
        when(atendenteFacade.cadastrarCliente(cliente)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> response = atendenteController.cadastrarCliente(cliente);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void consultarCliente_DeveRetornarCliente() {
        // Arrange
        Long clienteId = 1L;
        when(atendenteFacade.consultarCliente(clienteId)).thenReturn(cliente);

        // Act
        ResponseEntity<Cliente> response = atendenteController.consultarCliente(clienteId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void excluirCliente_DeveExcluirClienteComSucesso() {
        // Arrange
        Long clienteId = 1L;
        doNothing().when(atendenteFacade).excluirCliente(clienteId);

        // Act
        ResponseEntity<Void> response = atendenteController.excluirCliente(clienteId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void cadastrarPet_DeveCadastrarNovoPet() {
        // Arrange
        Long clienteId = 1L;
        when(atendenteFacade.cadastrarPet(clienteId, pet)).thenReturn(pet);

        // Act
        ResponseEntity<Pet> response = atendenteController.cadastrarPet(clienteId, pet);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
    }

    @Test
    void consultarPet_DeveRetornarPet() {
        // Arrange
        Long petId = 1L;
        when(atendenteFacade.consultarPet(petId)).thenReturn(pet);

        // Act
        ResponseEntity<Pet> response = atendenteController.consultarPet(petId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pet, response.getBody());
    }

    @Test
    void excluirPet_DeveExcluirPetComSucesso() {
        // Arrange
        Long petId = 1L;
        doNothing().when(atendenteFacade).excluirPet(petId);

        // Act
        ResponseEntity<Void> response = atendenteController.excluirPet(petId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
