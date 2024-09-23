package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.service.GerenteService;
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

class GerenteControllerTest {

    @InjectMocks
    private GerenteController gerenteController;

    @Mock
    private GerenteService gerenteService;

    private Gerente gerente;
    private GerenteDto gerenteDto;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando os objetos sem construtores específicos
        gerente = new Gerente();
        gerente.setId(UUID.randomUUID());
        gerente.setNome("João");
        gerente.setCpf("123456789");
        gerente.setTelefone("987654321");

        gerenteDto = new GerenteDto();
        gerenteDto.setNome("João");
        gerenteDto.setCpf("123456789");
        gerenteDto.setTelefone("987654321");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria");
        cliente.setCpf("123456789");
        cliente.setTelefone("987654321");
    }

    @Test
    void testGetAllGerentes() {
        when(gerenteService.findAll()).thenReturn(Arrays.asList(gerente));

        List<Gerente> gerentes = gerenteController.getAllGerentes();
        assertEquals(1, gerentes.size());
        assertEquals("João", gerentes.get(0).getNome());
    }

    @Test
    void testGetGerenteById() {
        UUID id = gerente.getId();
        when(gerenteService.findById(id)).thenReturn(Optional.of(gerente));

        ResponseEntity<Gerente> response = gerenteController.getGerenteById(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(gerente, response.getBody());
    }

    @Test
    void testGetGerenteByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(gerenteService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Gerente> response = gerenteController.getGerenteById(id);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCreateGerente() {
        when(gerenteService.save(gerenteDto)).thenReturn(gerente);

        Gerente createdGerente = gerenteController.createGerente(gerenteDto);
        assertNotNull(createdGerente);
        assertEquals("João", createdGerente.getNome());
    }

    @Test
    void testUpdateGerente() {
        UUID id = gerente.getId();
        when(gerenteService.update(id, gerenteDto)).thenReturn(Optional.of(gerente));

        ResponseEntity<Gerente> response = gerenteController.updateGerente(id, gerenteDto);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(gerente, response.getBody());
    }

    @Test
    void testUpdateGerenteNotFound() {
        UUID id = UUID.randomUUID();
        when(gerenteService.update(id, gerenteDto)).thenReturn(Optional.empty());

        ResponseEntity<Gerente> response = gerenteController.updateGerente(id, gerenteDto);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testDeleteGerente() {
        UUID id = gerente.getId();
        when(gerenteService.delete(id)).thenReturn(true);

        ResponseEntity<Void> response = gerenteController.deleteGerente(id);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void testDeleteGerenteNotFound() {
        UUID id = UUID.randomUUID();
        when(gerenteService.delete(id)).thenReturn(false);

        ResponseEntity<Void> response = gerenteController.deleteGerente(id);
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testCadastrarCliente() {
        doNothing().when(gerenteService).cadastrarCliente(cliente);

        ResponseEntity<Cliente> response = gerenteController.cadastrarCliente(cliente);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testConsultarCliente() {
        when(gerenteService.consultarCliente(1L)).thenReturn(cliente);

        ResponseEntity<Cliente> response = gerenteController.consultarCliente(1L);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void testExcluirCliente() {
        doNothing().when(gerenteService).excluirCliente(1L);

        ResponseEntity<Void> response = gerenteController.excluirCliente(1L);
        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void atualizarCliente() {
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);

        doNothing().when(gerenteService).atualizarCliente(cliente);

        ResponseEntity<Cliente> response = gerenteController.atualizarCliente(clienteId, cliente);

        assertEquals(ResponseEntity.ok(cliente), response);
        verify(gerenteService, times(1)).atualizarCliente(cliente);
    }
}
