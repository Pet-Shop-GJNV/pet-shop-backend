package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.service.AgendamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgendamentoControllerTest {

    @InjectMocks
    private AgendamentoController agendamentoController;

    @Mock
    private AgendamentoService agendamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarAgendamentos() {
        Agendamento agendamento1 = new Agendamento();
        Agendamento agendamento2 = new Agendamento();

        when(agendamentoService.listarAgendamentos()).thenReturn(Arrays.asList(agendamento1, agendamento2));

        ResponseEntity<List<Agendamento>> response = agendamentoController.listarAgendamentos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testBuscarAgendamentoPorId() {
        int id = 1;
        Agendamento agendamento = new Agendamento();
        agendamento.setId(id);

        when(agendamentoService.getAgendamentoById(id)).thenReturn(agendamento);

        ResponseEntity<Agendamento> response = agendamentoController.buscarAgendamentoPorId(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void testCriarAgendamento() {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setId(1);

        when(agendamentoService.createAgendamento(agendamentoDto)).thenReturn(novoAgendamento);

        ResponseEntity<Agendamento> response = agendamentoController.criarAgendamento(agendamentoDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(novoAgendamento.getId(), response.getBody().getId());
    }

    @Test
    void testAtualizarAgendamento() {
        int id = 1;
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        Agendamento agendamentoAtualizado = new Agendamento();
        agendamentoAtualizado.setId(id);

        when(agendamentoService.updateAgendamento(id, agendamentoDto)).thenReturn(agendamentoAtualizado);

        ResponseEntity<Agendamento> response = agendamentoController.atualizarAgendamento(id, agendamentoDto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(id, response.getBody().getId());
    }

    @Test
    void testDeletarAgendamento() {
        int id = 1;

        doNothing().when(agendamentoService).deleteAgendamento(id);

        ResponseEntity<Void> response = agendamentoController.deletarAgendamento(id);

        assertEquals(204, response.getStatusCodeValue());
    }
}