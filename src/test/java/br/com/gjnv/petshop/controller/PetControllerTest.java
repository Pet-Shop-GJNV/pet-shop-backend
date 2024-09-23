package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.facade.PetFacade;
import br.com.gjnv.petshop.model.Pet;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PetControllerTest {

    @Mock
    private PetFacade petFacade;

    @InjectMocks
    private PetController petController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void testGetAllPets() throws Exception {
        when(petFacade.listarTodosPets()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pet")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testGetPetByIdSuccess() throws Exception {
        PetDto petDto = new PetDto();
        when(petFacade.buscarPetPorId(anyLong())).thenReturn(Optional.of(petDto));

        mockMvc.perform(get("/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }

    @Test
    void testGetPetByIdNotFound() throws Exception {
        when(petFacade.buscarPetPorId(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePetSuccess() throws Exception {
        Pet pet = new Pet();
        when(petFacade.criarPet(any(PetDto.class))).thenReturn(Optional.of(pet));

        mockMvc.perform(post("/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Rex\", \"raca\": \"Labrador\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreatePetBadRequest() throws Exception {
        when(petFacade.criarPet(any(PetDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(post("/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Rex\", \"raca\": \"Labrador\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdatePetSuccess() throws Exception {
        Pet pet = new Pet();
        when(petFacade.atualizarPet(anyLong(), any(PetDto.class))).thenReturn(Optional.of(pet));

        mockMvc.perform(put("/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Rex\", \"raca\": \"Labrador\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdatePetNotFound() throws Exception {
        when(petFacade.atualizarPet(anyLong(), any(PetDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Rex\", \"raca\": \"Labrador\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletePetSuccess() throws Exception {
        when(petFacade.deletarPet(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeletePetNotFound() throws Exception {
        when(petFacade.deletarPet(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/pet/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
