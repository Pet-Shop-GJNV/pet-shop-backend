package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.service.GerenteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GerenteController.class)
class GerenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerenteService gerenteService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllGerentes() throws Exception {
        List<Gerente> gerentes = Arrays.asList(new Gerente(), new Gerente());
        when(gerenteService.findAll()).thenReturn(gerentes);

        mockMvc.perform(get("/gerentes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(gerenteService, times(1)).findAll();
    }

    @Test
    void testGetGerenteById() throws Exception {
        UUID id = UUID.randomUUID();
        Gerente gerente = new Gerente();
        when(gerenteService.findById(id)).thenReturn(Optional.of(gerente));

        mockMvc.perform(get("/gerentes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(gerenteService, times(1)).findById(id);
    }

    @Test
    void testGetGerenteById_NotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(gerenteService.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/gerentes/{id}", id))
                .andExpect(status().isNotFound());

        verify(gerenteService, times(1)).findById(id);
    }

    @Test
    void testCreateGerente() throws Exception {
        GerenteDto gerenteDto = new GerenteDto();
        gerenteDto.setNome("João");
        gerenteDto.setCpf("12345678900");
        gerenteDto.setTelefone("99999-9999");

        Gerente gerente = new Gerente();
        when(gerenteService.save(any(GerenteDto.class))).thenReturn(gerente);

        mockMvc.perform(post("/gerentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gerenteDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(gerenteService, times(1)).save(any(GerenteDto.class));
    }

    @Test
    void testUpdateGerente() throws Exception {
        UUID id = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();
        gerenteDto.setNome("João Atualizado");

        Gerente gerente = new Gerente();
        when(gerenteService.update(eq(id), any(GerenteDto.class))).thenReturn(Optional.of(gerente));

        mockMvc.perform(put("/gerentes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gerenteDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(gerenteService, times(1)).update(eq(id), any(GerenteDto.class));
    }

    @Test
    void testUpdateGerente_NotFound() throws Exception {
        UUID id = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();
        gerenteDto.setNome("João Atualizado");

        when(gerenteService.update(eq(id), any(GerenteDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/gerentes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gerenteDto)))
                .andExpect(status().isNotFound());

        verify(gerenteService, times(1)).update(eq(id), any(GerenteDto.class));
    }

    @Test
    void testDeleteGerente() throws Exception {
        UUID id = UUID.randomUUID();
        when(gerenteService.delete(id)).thenReturn(true);

        mockMvc.perform(delete("/gerentes/{id}", id))
                .andExpect(status().isNoContent());

        verify(gerenteService, times(1)).delete(id);
    }

    @Test
    void testDeleteGerente_NotFound() throws Exception {
        UUID id = UUID.randomUUID();
        when(gerenteService.delete(id)).thenReturn(false);

        mockMvc.perform(delete("/gerentes/{id}", id))
                .andExpect(status().isNotFound());

        verify(gerenteService, times(1)).delete(id);
    }
}
