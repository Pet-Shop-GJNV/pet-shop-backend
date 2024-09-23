package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.facade.MotoristaFacade;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MotoristaControllerTest {

    @Mock
    private MotoristaFacade motoristaFacade;

    @InjectMocks
    private MotoristaController motoristaController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(motoristaController).build();
    }

    @Test
    void testGetAllMotoristas() throws Exception {
        List<Motorista> motoristas = new ArrayList<>();
        motoristas.add(new Motorista());  // Exemplo de motorista
        when(motoristaFacade.listarTodosMotoristas()).thenReturn(motoristas);

        mockMvc.perform(get("/motoristas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetMotoristaById() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        Motorista motorista = new Motorista();
        when(motoristaFacade.buscarMotoristaPorId(motoristaId)).thenReturn(Optional.of(motorista));

        mockMvc.perform(get("/motoristas/{id}", motoristaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testGetMotoristaByIdNotFound() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        when(motoristaFacade.buscarMotoristaPorId(motoristaId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/motoristas/{id}", motoristaId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateMotorista() throws Exception {
        Motorista motorista = new Motorista();
        when(motoristaFacade.criarMotorista(any(Motorista.class))).thenReturn(motorista);

        mockMvc.perform(post("/motoristas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Motorista Exemplo\" }"))  // Exemplo de payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testCreateMotoristaBadRequest() throws Exception {
        when(motoristaFacade.criarMotorista(any(Motorista.class))).thenThrow(new RuntimeException("Erro ao criar motorista"));

        mockMvc.perform(post("/motoristas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Motorista Exemplo\" }"))  // Exemplo de payload
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateMotorista() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        Motorista motorista = new Motorista();
        when(motoristaFacade.atualizarMotorista(eq(motoristaId), any(Motorista.class))).thenReturn(Optional.of(motorista));

        mockMvc.perform(put("/motoristas/{id}", motoristaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Novo Nome\" }"))  // Exemplo de payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testUpdateMotoristaNotFound() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        when(motoristaFacade.atualizarMotorista(eq(motoristaId), any(Motorista.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/motoristas/{id}", motoristaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Novo Nome\" }"))  // Exemplo de payload
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteMotorista() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        when(motoristaFacade.excluirMotorista(motoristaId)).thenReturn(true);

        mockMvc.perform(delete("/motoristas/{id}", motoristaId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteMotoristaNotFound() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        when(motoristaFacade.excluirMotorista(motoristaId)).thenReturn(false);

        mockMvc.perform(delete("/motoristas/{id}", motoristaId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testRealizarColeta() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        Endereco endereco = new Endereco();
        when(motoristaFacade.realizarColeta(eq(motoristaId), any(Endereco.class))).thenReturn(true);

        mockMvc.perform(post("/motoristas/{id}/coleta", motoristaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"rua\": \"Rua Exemplo\", \"numero\": \"123\" }"))  // Exemplo de payload
                .andExpect(status().isOk());
    }

    @Test
    void testRealizarColetaNotFound() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        when(motoristaFacade.realizarColeta(eq(motoristaId), any(Endereco.class))).thenReturn(false);

        mockMvc.perform(post("/motoristas/{id}/coleta", motoristaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"rua\": \"Rua Exemplo\", \"numero\": \"123\" }"))  // Exemplo de payload
                .andExpect(status().isNotFound());
    }

    @Test
    void testRealizarEntrega() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        Endereco endereco = new Endereco();
        when(motoristaFacade.realizarEntrega(eq(motoristaId), any(Endereco.class))).thenReturn(true);

        mockMvc.perform(post("/motoristas/{id}/entrega", motoristaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"rua\": \"Rua Exemplo\", \"numero\": \"123\" }"))  // Exemplo de payload
                .andExpect(status().isOk());
    }

    @Test
    void testRealizarEntregaNotFound() throws Exception {
        UUID motoristaId = UUID.randomUUID();
        when(motoristaFacade.realizarEntrega(eq(motoristaId), any(Endereco.class))).thenReturn(false);

        mockMvc.perform(post("/motoristas/{id}/entrega", motoristaId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"rua\": \"Rua Exemplo\", \"numero\": \"123\" }"))  // Exemplo de payload
                .andExpect(status().isNotFound());
    }
}
