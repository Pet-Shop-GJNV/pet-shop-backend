package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteAgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarClientes() {
        List<ClienteDto> clientes = Arrays.asList(new ClienteDto(), new ClienteDto());
        when(clienteService.listarClientes()).thenReturn(clientes);

        ResponseEntity<List<ClienteDto>> response = clienteController.listarClientes();

        assertEquals(ResponseEntity.ok(clientes), response);
        verify(clienteService, times(1)).listarClientes();
    }

    @Test
    void consultarCliente() {
        Long id = 1L;
        ClienteDto cliente = new ClienteDto();
        when(clienteService.consultarCliente(id)).thenReturn(cliente);

        ResponseEntity<ClienteDto> response = clienteController.consultarCliente(id);

        assertEquals(ResponseEntity.ok(cliente), response);
        verify(clienteService, times(1)).consultarCliente(id);
    }

    @Test
    void criarCliente() {
        ClienteDto clienteDto = new ClienteDto();
        when(clienteService.criarCliente(clienteDto)).thenReturn(clienteDto);

        ResponseEntity<ClienteDto> response = clienteController.criarCliente(clienteDto);

        assertEquals(ResponseEntity.ok(clienteDto), response);
        verify(clienteService, times(1)).criarCliente(clienteDto);
    }

    @Test
    void editarCliente() {
        Long id = 1L;
        ClienteDto clienteDto = new ClienteDto();
        when(clienteService.editarCliente(id, clienteDto)).thenReturn(clienteDto);

        ResponseEntity<ClienteDto> response = clienteController.editarCliente(id, clienteDto);

        assertEquals(ResponseEntity.ok(clienteDto), response);
        verify(clienteService, times(1)).editarCliente(id, clienteDto);
    }

    @Test
    void deletarCliente() {
        Long id = 1L;

        ResponseEntity<Void> response = clienteController.deletarCliente(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(clienteService, times(1)).deletarCliente(id);
    }

    @Test
    void solicitarServico() {
        ClienteAgendamentoDto clienteAgendamentoDto = new ClienteAgendamentoDto();
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setClientId(clienteAgendamentoDto.getClientId());
        agendamentoDto.setServicoId(clienteAgendamentoDto.getServicoId());
        agendamentoDto.setFuncionarioId(clienteAgendamentoDto.getFuncionarioId());
        agendamentoDto.setVagaDisponivel(false);

        String mensagem = "Serviço solicitado com sucesso";
        when(clienteService.solicitarServico(anyInt(), anyInt(), any(AgendamentoDto.class))).thenReturn(mensagem);

        ResponseEntity<String> response = clienteController.solicitarServico(clienteAgendamentoDto);

        assertEquals(ResponseEntity.ok(mensagem), response);
        verify(clienteService, times(1)).solicitarServico(anyInt(), anyInt(), any(AgendamentoDto.class));
    }

    @Test
    void cancelarServico() {
        Long servicoId = 1L;
        String mensagem = "Serviço cancelado com sucesso";
        when(clienteService.cancelarServico(servicoId)).thenReturn(mensagem);

        ResponseEntity<String> response = clienteController.cancelarServico(servicoId);

        assertEquals(ResponseEntity.ok(mensagem), response);
        verify(clienteService, times(1)).cancelarServico(servicoId);
    }
}