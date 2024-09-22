package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarPagamentoPix() {
        double valor = 100.0;
        String expectedResponse = "Gerando chave pix / código QR";

        when(pagamentoService.pagamentoPix(valor)).thenReturn(expectedResponse);

        ResponseEntity<String> response = pagamentoController.realizarPagamentoPix(valor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testRealizarPagamentoDinheiro() {
        double valor = 50.0;
        String expectedResponse = "efetue pagamento no estabelecimento";

        when(pagamentoService.pagamentoDinheiro(valor)).thenReturn(expectedResponse);

        ResponseEntity<String> response = pagamentoController.realizarPagamentoDinheiro(valor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testRealizarPagamentoCredito() {
        double valor = 150.0;
        String expectedResponse = "efetue pagamento no estabelecimento";

        when(pagamentoService.pagamentoCredito(valor)).thenReturn(expectedResponse);

        ResponseEntity<String> response = pagamentoController.realizarPagamentoCredito(valor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testRealizarPagamentoDebito() {
        double valor = 75.0;
        String expectedResponse = "efetue pagamento no estabelecimento";

        when(pagamentoService.pagamentoDebito(valor)).thenReturn(expectedResponse);

        ResponseEntity<String> response = pagamentoController.realizarPagamentoDebito(valor);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
    }
}
