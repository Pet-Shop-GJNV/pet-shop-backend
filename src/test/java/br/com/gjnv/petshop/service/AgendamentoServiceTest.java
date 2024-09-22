package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.AgendamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @Mock
    private ServicoService servicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAgendamento_ServicoNaoEncontrado() {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setServicoId(1);
        agendamentoDto.setClientId(1);
        agendamentoDto.setFuncionarioId(1);
        agendamentoDto.setVagaDisponivel(true);

        when(servicoService.consultarServico(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            agendamentoService.createAgendamento(agendamentoDto);
        });

        assertEquals("Serviço não encontrado", exception.getMessage());
    }

    @Test
    void testCreateAgendamento() {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setServicoId(1);
        agendamentoDto.setClientId(1);
        agendamentoDto.setFuncionarioId(1);
        agendamentoDto.setVagaDisponivel(true);

        Servico servico = new Servico();
        servico.setId(1);

        when(servicoService.consultarServico(1)).thenReturn(Optional.of(servico));
        when(agendamentoRepository.save(any(Agendamento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Agendamento agendamento = agendamentoService.createAgendamento(agendamentoDto);

        assertNotNull(agendamento);
        assertEquals(1, agendamento.getClientId());
        assertEquals(1, agendamento.getFuncionarioId());
        assertTrue(agendamento.isVagaDisponivel());
        assertEquals(servico, agendamento.getTipoServico());
    }

    @Test
    void testUpdateAgendamento_ServicoNaoEncontrado() {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setServicoId(1);

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(new Agendamento()));
        when(servicoService.consultarServico(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            agendamentoService.updateAgendamento(1, agendamentoDto);
        });

        assertEquals("Serviço não encontrado", exception.getMessage());
    }

    @Test
    void testUpdateAgendamento_AgendamentoNaoEncontrado() {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setServicoId(1);

        when(agendamentoRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            agendamentoService.updateAgendamento(1, agendamentoDto);
        });

        assertEquals("Agendamento não encontrado", exception.getMessage());
    }

    @Test
    void testUpdateAgendamento() {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setServicoId(1);
        agendamentoDto.setClientId(1);
        agendamentoDto.setFuncionarioId(1);
        agendamentoDto.setVagaDisponivel(true);

        Agendamento agendamentoExistente = new Agendamento();
        agendamentoExistente.setId(1);

        Servico servico = new Servico();
        servico.setId(1);

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(agendamentoExistente));
        when(servicoService.consultarServico(1)).thenReturn(Optional.of(servico));
        when(agendamentoRepository.save(any(Agendamento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Agendamento agendamentoAtualizado = agendamentoService.updateAgendamento(1, agendamentoDto);

        assertNotNull(agendamentoAtualizado);
        assertEquals(1, agendamentoAtualizado.getClientId());
        assertEquals(1, agendamentoAtualizado.getFuncionarioId());
        assertTrue(agendamentoAtualizado.isVagaDisponivel());
        assertEquals(servico, agendamentoAtualizado.getTipoServico());
    }

    @Test
    void testDeleteAgendamento_AgendamentoNaoEncontrado() {
        when(agendamentoRepository.existsById(1)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            agendamentoService.deleteAgendamento(1);
        });

        assertEquals("Agendamento não encontrado", exception.getMessage());
    }

    @Test
    void testDeleteAgendamento() {
        when(agendamentoRepository.existsById(1)).thenReturn(true);

        agendamentoService.deleteAgendamento(1);

        verify(agendamentoRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetAgendamentoById() {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(1);

        when(agendamentoRepository.findById(1)).thenReturn(Optional.of(agendamento));

        Agendamento foundAgendamento = agendamentoService.getAgendamentoById(1);

        assertNotNull(foundAgendamento);
        assertEquals(1, foundAgendamento.getId());
    }

    @Test
    void testListarAgendamentos() {
        Agendamento agendamento1 = new Agendamento();
        Agendamento agendamento2 = new Agendamento();

        when(agendamentoRepository.findAll()).thenReturn(Arrays.asList(agendamento1, agendamento2));

        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();

        assertNotNull(agendamentos);
        assertEquals(2, agendamentos.size());
    }
}
