package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.ServicoFacade;
import br.com.gjnv.petshop.model.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ServicoControllerTest {

    @Mock
    private ServicoFacade servicoFacade;

    @InjectMocks
    private ServicoController servicoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(servicoController).build();
    }

    @Test
    void testConsultarTodosServicos() throws Exception {
        when(servicoFacade.listarTodosServicos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/servicos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testConsultarServicoByIdSuccess() throws Exception {
        Servico servico = new Servico();
        when(servicoFacade.consultarServicoPorId(anyInt())).thenReturn(Optional.of(servico));

        mockMvc.perform(get("/servicos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void testConsultarServicoByIdNotFound() throws Exception {
        when(servicoFacade.consultarServicoPorId(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(get("/servicos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCancelarServicoSuccess() throws Exception {
        mockMvc.perform(delete("/servicos/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Serviço cancelado com sucesso."));
    }

    @Test
    void testAdicionarServicoSuccess() throws Exception {
        mockMvc.perform(post("/servicos/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Serviço de banho\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testAdicionarServicoBadRequest() throws Exception {
        doThrow(new IllegalArgumentException()).when(servicoFacade).adicionarServico(any(Servico.class));

        mockMvc.perform(post("/servicos/adicionar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"descricao\": \"Serviço de banho\"}"))
                .andExpect(status().isBadRequest());
    }
}
