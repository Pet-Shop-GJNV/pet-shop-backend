package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pagamento;
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
class PagamentoRepositoryTest {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Pagamento pagamento;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNome("Gabriel");
        testEntityManager.persist(cliente);
        testEntityManager.flush();

        pagamento = new Pagamento(100.00, cliente);
        testEntityManager.persist(pagamento);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        assertThat(pagamentos).hasSize(1);
        assertThat(pagamentos.get(0).getValor()).isEqualTo(100.00);
        assertThat(pagamentos.get(0).getCliente().getNome()).isEqualTo("Gabriel");
    }

    @Test
    void testSave() {
        Pagamento novoPagamento = new Pagamento(150.00, cliente);
        pagamentoRepository.save(novoPagamento);
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        assertThat(pagamentos).hasSize(2);
        assertThat(pagamentos.get(1).getValor()).isEqualTo(150.00);
    }

    @Test
    void testDelete() {
        pagamentoRepository.delete(pagamento);
        testEntityManager.flush();

        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        assertThat(pagamentos).isEmpty();
    }

    @Test
    void testFindById() {
        Long id = pagamento.getId();
        Optional<Pagamento> foundPagamento = pagamentoRepository.findById(id);
        assertThat(foundPagamento).isPresent();
        assertThat(foundPagamento.get().getValor()).isEqualTo(100.00);
        assertThat(foundPagamento.get().getCliente().getNome()).isEqualTo("Gabriel");
    }
}
