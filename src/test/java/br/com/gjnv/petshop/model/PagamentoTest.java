package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PagamentoTest {

    private Pagamento pagamento;

    @BeforeEach
    public void setUp() {
        Servico servico = new Servico("Servico 1", 100.00, 60);
        pagamento = new Pagamento(100.00, servico);
    }

    @Test
    public void criandoPagamentoCorretamente() {
        assertEquals(100.00, pagamento.getValor());
        assertNotNull(pagamento.getServico());
    }

    @Test
    public void atualizandoValorPagamento() {
        pagamento.setValor(200.00);
        assertEquals(200.00, pagamento.getValor());
    }

    @Test
    public void pagamentoViaPix() {
        String resultado = pagamento.pagamentoPix();
        assertEquals("Pagamento de R$100.0 realizado via Pix.", resultado);
    }

    @Test
    public void pagamentoViaDinheiro() {
        String resultado = pagamento.pagamentoDinheiro();
        assertEquals("Pagamento de R$100.0 realizado em dinheiro.", resultado);
    }

    @Test
    public void pagamentoViaCredito() {
        String resultado = pagamento.pagamentoCredito();
        assertEquals("Pagamento de R$100.0 realizado via crédito.", resultado);
    }

    @Test
    public void pagamentoViaDebito() {
        String resultado = pagamento.pagamentoDebito();
        assertEquals("Pagamento de R$100.0 realizado via débito.", resultado);
    }
}
