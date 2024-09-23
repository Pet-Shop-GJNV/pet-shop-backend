package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.dto.ShowDaFe;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.PetService;
import br.com.gjnv.petshop.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPets() throws Exception {
        List<Pet> pets = Arrays.asList(new Pet(), new Pet());
        when(petService.findAll()).thenReturn(pets);

        mockMvc.perform(get("/pet"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(petService, times(1)).findAll();
    }

    @Test
    void testGetPetById() throws Exception {
        Long id = 1L;
        Pet pet = new Pet();
        when(petService.findById(id)).thenReturn(Optional.of(pet));

        mockMvc.perform(get("/pet/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(petService, times(1)).findById(id);
    }

    @Test
    void testGetPetById_NotFound() throws Exception {
        Long id = 1L;
        when(petService.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/pet/{id}", id))
                .andExpect(status().isNotFound());

        verify(petService, times(1)).findById(id);
    }

    @Test
    void testCreatePet() throws Exception {
        PetDto petDto = new PetDto();
        petDto.setNome("Rex");
        petDto.setIdade(3);
        petDto.setRaca("Labrador");
        petDto.setClienteId(1L);

        Cliente cliente = new Cliente();
        Pet pet = new Pet();
        pet.setNome("Rex");

        when(clienteRepository.findById(petDto.getClienteId())).thenReturn(Optional.of(cliente));
        when(petService.save(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(petService, times(1)).save(any(Pet.class));
    }

    @Test
    void testCreatePet_ClienteNotFound() throws Exception {
        PetDto petDto = new PetDto();
        petDto.setNome("Rex");
        petDto.setIdade(3);
        petDto.setRaca("Labrador");
        petDto.setClienteId(1L);

        when(clienteRepository.findById(petDto.getClienteId())).thenReturn(Optional.empty());

        mockMvc.perform(post("/pet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petDto)))
                .andExpect(status().isBadRequest());

        verify(clienteRepository, times(1)).findById(petDto.getClienteId());
        verify(petService, times(0)).save(any(Pet.class));
    }

    @Test
    void testUpdatePetById() throws Exception {
        Long id = 1L;

        ShowDaFe petParaEditar = new ShowDaFe("Buddy", 4, "Golden", 1L);

        Cliente cliente = new Cliente();
        Pet petEditado = new Pet();
        petEditado.setNome(petParaEditar.nome());
        petEditado.setIdade(petParaEditar.idade());
        petEditado.setRaca(petParaEditar.raca());
        petEditado.setCliente(cliente);

        when(clienteRepository.findById(petParaEditar.clienteId())).thenReturn(Optional.of(cliente));

        when(petService.updateById(eq(id), any(Pet.class))).thenReturn(petEditado);

        mockMvc.perform(put("/pet/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petParaEditar)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value(petParaEditar.nome()))
                .andExpect(jsonPath("$.idade").value(petParaEditar.idade()))
                .andExpect(jsonPath("$.raca").value(petParaEditar.raca()));

        verify(petService, times(1)).updateById(eq(id), any(Pet.class));
    }



    @Test
    void testUpdatePetById_NotFound() throws Exception {
        Long id = 1L;
        ShowDaFe petParaEditar = new ShowDaFe("Buddy", 4, "Golden", 1L);

        when(clienteRepository.findById(petParaEditar.clienteId())).thenReturn(Optional.empty());

        mockMvc.perform(put("/pet/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petParaEditar)))
                .andExpect(status().isBadRequest());

        verify(clienteRepository, times(1)).findById(petParaEditar.clienteId());
        verify(petService, times(0)).updateById(eq(id), any(Pet.class));
    }

    @Test
    void testDeletePetById() throws Exception {
        Long id = 1L;
        when(petService.delete(id)).thenReturn(true);

        mockMvc.perform(delete("/pet/{id}", id))
                .andExpect(status().isNoContent());

        verify(petService, times(1)).delete(id);
    }

    @Test
    void testDeletePetById_NotFound() throws Exception {
        Long id = 1L;
        when(petService.delete(id)).thenReturn(false);

        mockMvc.perform(delete("/pet/{id}", id))
                .andExpect(status().isNotFound());

        verify(petService, times(1)).delete(id);
    }
}
