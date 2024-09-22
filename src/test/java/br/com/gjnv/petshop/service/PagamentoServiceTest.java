package br.com.gjnv.petshop.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagamentoServiceTest {

    private final PagamentoService pagamentoService = new PagamentoService();

    @Test
    void testPagamentoPix() {
        double valor = 100.0;
        String expectedResponse = "Gerando chave pix / código QR. Valor: " + valor;

        String response = pagamentoService.pagamentoPix(valor);

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
