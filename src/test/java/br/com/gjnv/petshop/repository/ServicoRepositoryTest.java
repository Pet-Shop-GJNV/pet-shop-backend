package br.com.gjnv.petshop.repository;

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
class ServicoRepositoryTest {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Servico servico;

    @BeforeEach
    void setUp() {
        servico = new Servico();
        servico.setTipoServico("Corte de Cabelo");
        servico.setPreco(50.00);
        servico.setDuracao(30);
        testEntityManager.persist(servico);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Servico> servicos = servicoRepository.findAll();
        assertThat(servicos).hasSize(1);
        assertThat(servicos.get(0).getTipoServico()).isEqualTo("Corte de Cabelo");
    }

    @Test
    void testSave() {
        Servico novoServico = new Servico();
        novoServico.setTipoServico("Banho");
        novoServico.setPreco(30.00);
        novoServico.setDuracao(45);

        servicoRepository.save(novoServico);
        List<Servico> servicos = servicoRepository.findAll();
        assertThat(servicos).hasSize(2);
        assertThat(servicos.get(1).getTipoServico()).isEqualTo("Banho");
    }

    @Test
    void testDelete() {
        servicoRepository.delete(servico);
        testEntityManager.flush();

        List<Servico> servicos = servicoRepository.findAll();
        assertThat(servicos).isEmpty();
    }

    @Test
    void testFindById() {
        Integer id = servico.getId();
        Optional<Servico> foundServico = servicoRepository.findById(id);
        assertThat(foundServico).isPresent();
        assertThat(foundServico.get().getTipoServico()).isEqualTo("Corte de Cabelo");
    }
}
