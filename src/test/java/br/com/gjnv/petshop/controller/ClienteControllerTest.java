package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.ClienteAgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.facade.ClienteFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ClienteControllerTest {

    @Mock
    private ClienteFacade clienteFacade;

    @InjectMocks
    private ClienteController clienteController;

    private ClienteDto clienteDto;
    private ClienteAgendamentoDto clienteAgendamentoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteDto = new ClienteDto();
        clienteAgendamentoDto = new ClienteAgendamentoDto();
    }

    @Test
    void listarClientes_DeveRetornarListaDeClientes() {
        // Arrange
        List<ClienteDto> clientes = Arrays.asList(new ClienteDto(), new ClienteDto());
        when(clienteFacade.listarClientes()).thenReturn(clientes);

        // Act
        ResponseEntity<List<ClienteDto>> response = clienteController.listarClientes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    void consultarCliente_DeveRetornarClientePorId() {
        // Arrange
        Long clienteId = 1L;
        when(clienteFacade.consultarCliente(clienteId)).thenReturn(clienteDto);

        // Act
        ResponseEntity<ClienteDto> response = clienteController.consultarCliente(clienteId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDto, response.getBody());
    }

    @Test
    void criarCliente_DeveRetornarNovoCliente() {
        // Arrange
        when(clienteFacade.criarCliente(clienteDto)).thenReturn(clienteDto);

        // Act
        ResponseEntity<ClienteDto> response = clienteController.criarCliente(clienteDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDto, response.getBody());
    }

    @Test
    void editarCliente_DeveRetornarClienteAtualizado() {
        // Arrange
        Long clienteId = 1L;
        when(clienteFacade.editarCliente(clienteId, clienteDto)).thenReturn(clienteDto);

        // Act
        ResponseEntity<ClienteDto> response = clienteController.editarCliente(clienteId, clienteDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clienteDto, response.getBody());
    }

    @Test
    void deletarCliente_DeveDeletarClienteComSucesso() {
        // Arrange
        Long clienteId = 1L;
        doNothing().when(clienteFacade).deletarCliente(clienteId);

        // Act
        ResponseEntity<Void> response = clienteController.deletarCliente(clienteId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void solicitarServico_DeveRetornarMensagemDeSucesso() {
        // Arrange
        String mensagem = "Serviço solicitado com sucesso!";
        when(clienteFacade.solicitarServico(clienteAgendamentoDto)).thenReturn(mensagem);

        // Act
        ResponseEntity<String> response = clienteController.solicitarServico(clienteAgendamentoDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagem, response.getBody());
    }

    @Test
    void cancelarServico_DeveRetornarMensagemDeCancelamento() {
        // Arrange
        Long servicoId = 1L;
        String mensagem = "Serviço cancelado com sucesso!";
        when(clienteFacade.cancelarServico(servicoId)).thenReturn(mensagem);

        // Act
        ResponseEntity<String> response = clienteController.cancelarServico(servicoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagem, response.getBody());
    }
}
