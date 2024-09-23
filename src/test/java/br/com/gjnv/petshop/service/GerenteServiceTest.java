package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import br.com.gjnv.petshop.repository.GerenteRepository;
import br.com.gjnv.petshop.repository.AtendenteRepository;
import br.com.gjnv.petshop.repository.MotoristaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenteServiceTest {

    @InjectMocks
    private GerenteService gerenteService;

    @Mock
    private GerenteRepository gerenteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private AtendenteRepository atendenteRepository;

    @Mock
    private MotoristaRepository motoristaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<Gerente> gerentes = Arrays.asList(new Gerente(), new Gerente());
        when(gerenteRepository.findAll()).thenReturn(gerentes);

        List<Gerente> result = gerenteService.findAll();

        assertEquals(2, result.size());
        verify(gerenteRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Gerente gerente = new Gerente();
        when(gerenteRepository.findById(id)).thenReturn(Optional.of(gerente));

        Optional<Gerente> result = gerenteService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(gerente, result.get());
        verify(gerenteRepository, times(1)).findById(id);
    }

    @Test
    void testSave() {
        GerenteDto gerenteDto = new GerenteDto();
        gerenteDto.setNome("João");
        gerenteDto.setCpf("12345678900");
        gerenteDto.setTelefone("99999-9999");

        Gerente gerente = new Gerente();
        when(gerenteRepository.save(any(Gerente.class))).thenReturn(gerente);

        Gerente result = gerenteService.save(gerenteDto);

        assertNotNull(result);
        verify(gerenteRepository, times(1)).save(any(Gerente.class));
    }

    @Test
    void testUpdate() {
        UUID id = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();
        gerenteDto.setNome("João Atualizado");

        Gerente gerente = new Gerente();
        when(gerenteRepository.findById(id)).thenReturn(Optional.of(gerente));
        when(gerenteRepository.save(any(Gerente.class))).thenReturn(gerente);

        Optional<Gerente> result = gerenteService.update(id, gerenteDto);

        assertTrue(result.isPresent());
        verify(gerenteRepository, times(1)).findById(id);
        verify(gerenteRepository, times(1)).save(any(Gerente.class));
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();

        when(gerenteRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Gerente> result = gerenteService.update(id, gerenteDto);

        assertFalse(result.isPresent());
        verify(gerenteRepository, times(1)).findById(id);
        verify(gerenteRepository, times(0)).save(any(Gerente.class));
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();
        Gerente gerente = new Gerente();
        when(gerenteRepository.findById(id)).thenReturn(Optional.of(gerente));

        boolean result = gerenteService.delete(id);

        assertTrue(result);
        verify(gerenteRepository, times(1)).findById(id);
        verify(gerenteRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteNotFound() {
        UUID id = UUID.randomUUID();
        when(gerenteRepository.findById(id)).thenReturn(Optional.empty());

        boolean result = gerenteService.delete(id);

        assertFalse(result);
        verify(gerenteRepository, times(1)).findById(id);
        verify(gerenteRepository, times(0)).deleteById(any(UUID.class));
    }
}
