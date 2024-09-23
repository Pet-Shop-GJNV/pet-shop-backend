package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.AtendenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendenteControllerTest {

    @Mock
    private AtendenteService atendenteService;

    @InjectMocks
    private AtendenteController atendenteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAtendentes() {
        List<Atendente> atendentes = Arrays.asList(new Atendente(), new Atendente());
        when(atendenteService.findAll()).thenReturn(atendentes);

        List<Atendente> result = atendenteController.getAllAtendentes();

        assertEquals(2, result.size());
        verify(atendenteService, times(1)).findAll();
    }

    @Test
    void getAtendenteById() {
        UUID id = UUID.randomUUID();
        Atendente atendente = new Atendente();
        when(atendenteService.findById(id)).thenReturn(Optional.of(atendente));

        ResponseEntity<Atendente> response = atendenteController.getAtendenteById(id);

        assertEquals(ResponseEntity.ok(atendente), response);
        verify(atendenteService, times(1)).findById(id);
    }

    @Test
    void createAtendente() {
        Atendente atendente = new Atendente();
        when(atendenteService.save(atendente)).thenReturn(atendente);

        Atendente result = atendenteController.createAtendente(atendente);

        assertEquals(atendente, result);
        verify(atendenteService, times(1)).save(atendente);
    }

    @Test
    void updateAtendente() {
        UUID id = UUID.randomUUID();
        Atendente atendenteDetails = new Atendente();
        Atendente updatedAtendente = new Atendente();
        when(atendenteService.update(id, atendenteDetails)).thenReturn(Optional.of(updatedAtendente));

        ResponseEntity<Atendente> response = atendenteController.updateAtendente(id, atendenteDetails);

        assertEquals(ResponseEntity.ok(updatedAtendente), response);
        verify(atendenteService, times(1)).update(id, atendenteDetails);
    }

    @Test
    void deleteAtendente() {
        UUID id = UUID.randomUUID();
        when(atendenteService.delete(id)).thenReturn(true);

        ResponseEntity<Void> response = atendenteController.deleteAtendente(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(atendenteService, times(1)).delete(id);
    }

    @Test
    void atualizarStatusServico() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = atendenteController.atualizarStatusServico(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(atendenteService, times(1)).atualizarStatusServico(id);
    }

    @Test
    void cadastrarCliente() {
        Cliente cliente = new Cliente();

        ResponseEntity<Cliente> response = atendenteController.cadastrarCliente(cliente);

        assertEquals(ResponseEntity.ok(cliente), response);
        verify(atendenteService, times(1)).cadastrarCliente(cliente);
    }

    @Test
    void consultarCliente() {
        Long id = 1L;
        Cliente cliente = new Cliente();
        when(atendenteService.consultarCliente(id)).thenReturn(cliente);

        ResponseEntity<Cliente> response = atendenteController.consultarCliente(id);

        assertEquals(ResponseEntity.ok(cliente), response);
        verify(atendenteService, times(1)).consultarCliente(id);
    }

    @Test
    void excluirCliente() {
        Long id = 1L;

        ResponseEntity<Void> response = atendenteController.excluirCliente(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(atendenteService, times(1)).excluirCliente(id);
    }

    @Test
    void atualizarCliente() {
        Cliente cliente = new Cliente();

        ResponseEntity<Cliente> response = atendenteController.atualizarCliente(cliente);

        assertEquals(ResponseEntity.ok(cliente), response);
        verify(atendenteService, times(1)).atualizarCliente(cliente);
    }

    @Test
    void cadastrarPet() {
        Long clienteId = 1L;
        Pet pet = new Pet();

        ResponseEntity<Pet> response = atendenteController.cadastrarPet(clienteId, pet);

        assertEquals(ResponseEntity.ok(pet), response);
        verify(atendenteService, times(1)).cadastrarPet(clienteId, pet);
    }

    @Test
    void consultarPet() {
        Long id = 1L;
        Pet pet = new Pet();
        when(atendenteService.consultarPet(id)).thenReturn(pet);

        ResponseEntity<Pet> response = atendenteController.consultarPet(id);

        assertEquals(ResponseEntity.ok(pet), response);
        verify(atendenteService, times(1)).consultarPet(id);
    }

    @Test
    void excluirPet() {
        Long id = 1L;

        ResponseEntity<Void> response = atendenteController.excluirPet(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(atendenteService, times(1)).excluirPet(id);
    }

    @Test
    void atualizarPet() {
        Pet pet = new Pet();

        ResponseEntity<Pet> response = atendenteController.atualizarPet(pet);

        assertEquals(ResponseEntity.ok(pet), response);
        verify(atendenteService, times(1)).atualizarPet(pet);
    }
}