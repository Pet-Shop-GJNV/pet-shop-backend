package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Pagamento;
import br.com.gjnv.petshop.model.Servico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
    private ServicoRepository servicoRepository;


    @Test
    public void testFindAll() {
        Servico servico = new Servico();

        servicoRepository.save(servico);

        Pagamento pagamento1 = new Pagamento(10.0, servico);
        Pagamento pagamento2 = new Pagamento(20.0, servico);
        pagamentoRepository.save(pagamento1);
        pagamentoRepository.save(pagamento2);

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        assertThat(pagamentos).hasSize(2);
        assertThat(pagamentos).contains(pagamento1, pagamento2);
    }

    @Test
    public void testFindById() {
        Servico servico = new Servico();
        Pagamento pagamento = new Pagamento(10.0, servico);
        pagamentoRepository.save(pagamento);

        Optional<Pagamento> optionalPagamento = pagamentoRepository.findById(pagamento.getId());

        assertThat(optionalPagamento).isPresent();
        assertThat(optionalPagamento.get()).isEqualTo(pagamento);
    }

    @Test
    void testDelete() {
        Servico servico = new Servico();
        Pagamento pagamento = new Pagamento(10.0, servico);
        pagamentoRepository.save(pagamento);

        pagamentoRepository.delete(pagamento);

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        assertThat(pagamentos).isEmpty();
    }

    @Test
    void testSave() {
        Servico servico = new Servico();
        servicoRepository.save(servico);

        Pagamento pagamento = new Pagamento(10.0, servico);
        pagamentoRepository.save(pagamento);

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        assertThat(pagamentos).hasSize(1);
        assertThat(pagamentos.get(0)).isEqualTo(pagamento);
    }

    @Test
    void testUpdate() {
        Servico servico = new Servico();
        servicoRepository.save(servico);

        Pagamento pagamento = new Pagamento(10.0, servico);
        pagamentoRepository.save(pagamento);

        pagamento.setValor(20.0);
        pagamentoRepository.save(pagamento);

        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        assertThat(pagamentos).hasSize(1);
        assertThat(pagamentos.get(0).getValor()).isEqualTo(20.0);
    }
}