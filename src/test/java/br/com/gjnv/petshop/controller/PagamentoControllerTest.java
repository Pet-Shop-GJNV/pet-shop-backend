package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.PagamentoFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PagamentoControllerTest {

    @Mock
    private PagamentoFacade pagamentoFacade;

    @InjectMocks
    private PagamentoController pagamentoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();
    }

    @Test
    void testRealizarPagamentoPixSucesso() throws Exception {
        when(pagamentoFacade.realizarPagamentoPix(anyDouble(), anyInt())).thenReturn(true);

        mockMvc.perform(post("/pagamento/pix")
                        .param("valor", "100.00")
                        .param("servicoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pagamento realizado com sucesso via PIX."));
    }

    @Test
    void testRealizarPagamentoPixFalha() throws Exception {
        when(pagamentoFacade.realizarPagamentoPix(anyDouble(), anyInt())).thenReturn(false);

        mockMvc.perform(post("/pagamento/pix")
                        .param("valor", "100.00")
                        .param("servicoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Valor inválido ou serviço não encontrado."));
    }

    @Test
    void testRealizarPagamentoDinheiro() throws Exception {
        when(pagamentoFacade.realizarPagamentoDinheiro(anyDouble(), anyInt())).thenReturn("Pagamento realizado com sucesso em dinheiro.");

        mockMvc.perform(post("/pagamento/dinheiro")
                        .param("valor", "100.00")
                        .param("servicoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pagamento realizado com sucesso em dinheiro."));
    }

    @Test
    void testRealizarPagamentoCredito() throws Exception {
        when(pagamentoFacade.realizarPagamentoCredito(anyDouble(), anyInt())).thenReturn("Pagamento realizado com sucesso no crédito.");

        mockMvc.perform(post("/pagamento/credito")
                        .param("valor", "100.00")
                        .param("servicoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pagamento realizado com sucesso no crédito."));
    }

    @Test
    void testRealizarPagamentoDebito() throws Exception {
        when(pagamentoFacade.realizarPagamentoDebito(anyDouble(), anyInt())).thenReturn("Pagamento realizado com sucesso no débito.");

        mockMvc.perform(post("/pagamento/debito")
                        .param("valor", "100.00")
                        .param("servicoId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Pagamento realizado com sucesso no débito."));
    }
}
