package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.facade.AgendamentoFacade;
import br.com.gjnv.petshop.model.Agendamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class AgendamentoControllerTest {

    @Mock
    private AgendamentoFacade agendamentoFacade;

    @InjectMocks
    private AgendamentoController agendamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarAgendamentos_DeveRetornarListaDeAgendamentos() {
        // Arrange
        List<Agendamento> agendamentos = Arrays.asList(new Agendamento(), new Agendamento());
        when(agendamentoFacade.listarAgendamentos()).thenReturn(agendamentos);

        // Act
        ResponseEntity<List<Agendamento>> response = agendamentoController.listarAgendamentos();

        // Assert
        assertEquals("Status code incorreto", HttpStatus.OK, response.getStatusCode());
        assertEquals("Lista de agendamentos incorreta", agendamentos, response.getBody());
    }

    @Test
    void buscarAgendamentoPorId_DeveRetornarAgendamentoPorId() {
        // Arrange
        int id = 1;
        Agendamento agendamento = new Agendamento();
        when(agendamentoFacade.buscarAgendamentoPorId(id)).thenReturn(agendamento);

        // Act
        ResponseEntity<Agendamento> response = agendamentoController.buscarAgendamentoPorId(id);

        // Assert
        assertEquals("Status code incorreto", HttpStatus.OK, response.getStatusCode());
        assertEquals("Agendamento retornado incorreto", agendamento, response.getBody());
    }

    @Test
    void criarAgendamento_DeveRetornarNovoAgendamento() {
        // Arrange
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        Agendamento novoAgendamento = new Agendamento();
        when(agendamentoFacade.criarAgendamento(agendamentoDto)).thenReturn(novoAgendamento);

        // Act
        ResponseEntity<Agendamento> response = agendamentoController.criarAgendamento(agendamentoDto);

        // Assert
        assertEquals("Status code incorreto", HttpStatus.OK, response.getStatusCode());
        assertEquals("Novo agendamento incorreto", novoAgendamento, response.getBody());
    }

    @Test
    void atualizarAgendamento_DeveRetornarAgendamentoAtualizado() {
        // Arrange
        int id = 1;
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        Agendamento agendamentoAtualizado = new Agendamento();
        when(agendamentoFacade.atualizarAgendamento(id, agendamentoDto)).thenReturn(agendamentoAtualizado);

        // Act
        ResponseEntity<Agendamento> response = agendamentoController.atualizarAgendamento(id, agendamentoDto);

        // Assert
        assertEquals("Status code incorreto", HttpStatus.OK, response.getStatusCode());
        assertEquals("Agendamento atualizado incorreto", agendamentoAtualizado, response.getBody());
    }

    @Test
    void deletarAgendamento_DeveDeletarAgendamentoComSucesso() {
        // Arrange
        int id = 1;
        doNothing().when(agendamentoFacade).deletarAgendamento(id);

        // Act
        ResponseEntity<Void> response = agendamentoController.deletarAgendamento(id);

        // Assert
        assertEquals("Status code incorreto", HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(agendamentoFacade, times(1)).deletarAgendamento(id);
    }
}
