package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Servico;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
class PagamentoServiceTest {

    private final PagamentoService pagamentoService = new PagamentoService();

    @Test
    void testPagamentoPix_Sucesso() {
        double valor = 100.0;
        Servico servico = new Servico();
        servico.setPreco(100.0);
        String expectedResponse = "Gerando chave pix / código QR. Valor: " + valor;

        String response = pagamentoService.pagamentoPix(valor, servico);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testPagamentoPix_ValorInvalido() {
        double valor = 50.0;
        Servico servico = new Servico();
        servico.setPreco(100.0);
        String expectedResponse = "Valor invalido";

        String response = pagamentoService.pagamentoPix(valor, servico);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testPagamentoDinheiro() {
        double valor = 50.0;
        String expectedResponse = "Efetue pagamento no estabelecimento. Valor: " + valor;

        String response = pagamentoService.pagamentoDinheiro(valor);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testPagamentoCredito() {
        double valor = 150.0;
        String expectedResponse = "Efetue pagamento no estabelecimento. Valor: " + valor;

        String response = pagamentoService.pagamentoCredito(valor);

        assertEquals(expectedResponse, response);
    }

    @Test
    void testPagamentoDebito() {
        double valor = 75.0;
        String expectedResponse = "Efetue pagamento no estabelecimento. Valor: " + valor;

        String response = pagamentoService.pagamentoDebito(valor);

        assertEquals(expectedResponse, response);
    }
}
