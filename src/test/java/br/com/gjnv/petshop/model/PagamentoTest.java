package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoTest {

    private Pagamento pagamento;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Cliente 1", "12345678900");
        pagamento = new Pagamento(100.00, cliente);
    }

    @Test
    public void criandoPagamentoCorretamente() {
        assertNotNull(pagamento.getId());
        assertEquals(100.00, pagamento.getValor());
        assertNotNull(pagamento.getCliente());
        assertEquals("Cliente 1", pagamento.getCliente().getNome());
    }

    @Test
    public void atualizandoValorPagamento() {
        pagamento.setValor(200.00);
        assertEquals(200.00, pagamento.getValor());
    }

    @Test
    public void atualizandoClientePagamento() {
        Cliente novoCliente = new Cliente("Cliente 2", "98765432100");
        pagamento.setCliente(novoCliente);
        assertEquals("Cliente 2", pagamento.getCliente().getNome());
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
