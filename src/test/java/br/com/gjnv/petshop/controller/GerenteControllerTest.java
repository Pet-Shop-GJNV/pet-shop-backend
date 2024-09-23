package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.facade.GerenteFacade;
import br.com.gjnv.petshop.model.Gerente;
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

class GerenteControllerTest {

    @Mock
    private GerenteFacade gerenteFacade;

    @InjectMocks
    private GerenteController gerenteController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gerenteController).build();
    }

    @Test
    void testGetAllGerentes() throws Exception {
        List<Gerente> gerentes = new ArrayList<>();
        gerentes.add(new Gerente());  // Adicionar gerente de exemplo
        when(gerenteFacade.getAllGerentes()).thenReturn(gerentes);

        mockMvc.perform(get("/gerentes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testGetGerenteById() throws Exception {
        UUID gerenteId = UUID.randomUUID();
        Gerente gerente = new Gerente();
        when(gerenteFacade.getGerenteById(gerenteId)).thenReturn(Optional.of(gerente));

        mockMvc.perform(get("/gerentes/{id}", gerenteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testGetGerenteByIdNotFound() throws Exception {
        UUID gerenteId = UUID.randomUUID();
        when(gerenteFacade.getGerenteById(gerenteId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/gerentes/{id}", gerenteId))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateGerente() throws Exception {
        GerenteDto gerenteDto = new GerenteDto();
        Gerente gerente = new Gerente();
        when(gerenteFacade.createGerente(any(GerenteDto.class))).thenReturn(gerente);

        mockMvc.perform(post("/gerentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Gerente Exemplo\" }"))  // Exemplo de payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testUpdateGerente() throws Exception {
        UUID gerenteId = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();
        Gerente gerente = new Gerente();
        when(gerenteFacade.updateGerente(eq(gerenteId), any(GerenteDto.class))).thenReturn(Optional.of(gerente));

        mockMvc.perform(put("/gerentes/{id}", gerenteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Novo Nome\" }"))  // Exemplo de payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void testUpdateGerenteNotFound() throws Exception {
        UUID gerenteId = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();
        when(gerenteFacade.updateGerente(eq(gerenteId), any(GerenteDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/gerentes/{id}", gerenteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nome\": \"Novo Nome\" }"))  // Exemplo de payload
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteGerente() throws Exception {
        UUID gerenteId = UUID.randomUUID();
        when(gerenteFacade.deleteGerente(gerenteId)).thenReturn(true);

        mockMvc.perform(delete("/gerentes/{id}", gerenteId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteGerenteNotFound() throws Exception {
        UUID gerenteId = UUID.randomUUID();
        when(gerenteFacade.deleteGerente(gerenteId)).thenReturn(false);

        mockMvc.perform(delete("/gerentes/{id}", gerenteId))
                .andExpect(status().isNotFound());
    }
}
