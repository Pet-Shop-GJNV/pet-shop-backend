package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.model.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class AgendamentoRepositoryTest {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Agendamento agendamento;
    private Servico servico;

    @BeforeEach
    void setUp() {
        servico = new Servico();
        servico.setTipoServico("Corte de Cabelo");
        servico.setPreco(50.0);
        servico.setDuracao(30);
        testEntityManager.persist(servico);

        agendamento = new Agendamento();
        agendamento.setTipoServico(servico);
        agendamento.setClientId(1);
        agendamento.setFuncionarioId(2);
        agendamento.setVagaDisponivel(true);

        agendamentoRepository.save(agendamento);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        assertThat(agendamentos).hasSize(1);
        assertThat(agendamentos.get(0).getTipoServico().getTipoServico()).isEqualTo("Corte de Cabelo");
    }

    @Test
    void testSave() {
        Agendamento novoAgendamento = new Agendamento();
        novoAgendamento.setTipoServico(servico);
        novoAgendamento.setClientId(3);
        novoAgendamento.setFuncionarioId(4);
        novoAgendamento.setVagaDisponivel(false);

        agendamentoRepository.save(novoAgendamento);
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        assertThat(agendamentos).hasSize(2);
    }

    @Test
    void testDelete() {
        agendamentoRepository.delete(agendamento);
        testEntityManager.flush();

        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        assertThat(agendamentos).isEmpty();
    }

    @Test
    void testFindById() {
        Optional<Agendamento> foundAgendamento = agendamentoRepository.findById(agendamento.getId());
        assertThat(foundAgendamento).isPresent();
        assertThat(foundAgendamento.get().getTipoServico().getTipoServico()).isEqualTo("Corte de Cabelo");
    }
}
