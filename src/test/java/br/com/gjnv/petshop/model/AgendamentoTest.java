package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgendamentoTest {

    private Agendamento agendamento;
    private Servico servicoMock;

    @BeforeEach
    public void setUp() {
        agendamento = new Agendamento();
        servicoMock = new Servico();
    }

    @Test
    public void criandoAgendamentoComIdCorreto() {
        agendamento.setId(1);
        assertEquals(1, agendamento.getId(), "O ID do agendamento deveria ser 1");
    }

    @Test
    public void atribuindoTipoDeServicoAoAgendamento() {
        agendamento.setTipoServico(servicoMock);
        assertEquals(servicoMock, agendamento.getTipoServico(), "O serviço atribuído ao agendamento deveria ser o mock de Servico");
    }

    @Test
    public void associandoClienteAoAgendamento() {
        agendamento.setClientId(100);
        assertEquals(100, agendamento.getClientId(), "O ID do cliente no agendamento deveria ser 100");
    }

    @Test
    public void associandoFuncionarioAoAgendamento() {
        agendamento.setFuncionarioId(200);
        assertEquals(200, agendamento.getFuncionarioId(), "O ID do funcionário no agendamento deveria ser 200");
    }

    @Test
    public void verificandoVagaDisponivelNoAgendamento() {
        agendamento.setVagaDisponivel(true);
        assertTrue(agendamento.isVagaDisponivel(), "A vaga deveria estar disponível no agendamento");

        agendamento.setVagaDisponivel(false);
        assertFalse(agendamento.isVagaDisponivel(), "A vaga deveria não estar disponível no agendamento");
    }
}
