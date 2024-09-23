package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.repository.AtendenteRepository;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.repository.GerenteRepository;
import br.com.gjnv.petshop.repository.MotoristaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerenteServiceTest {

    @Mock
    private GerenteRepository gerenteRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AtendenteRepository atendenteRepository;

    @Mock
    private MotoristaRepository motoristaRepository;

    @InjectMocks
    private GerenteService gerenteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<Gerente> gerentes = Arrays.asList(mock(Gerente.class), mock(Gerente.class));
        when(gerenteRepository.findAll()).thenReturn(gerentes);

        List<Gerente> result = gerenteService.findAll();

        assertEquals(2, result.size());
        verify(gerenteRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Gerente gerente = mock(Gerente.class);
        when(gerenteRepository.findById(id)).thenReturn(Optional.of(gerente));

        Optional<Gerente> result = gerenteService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(gerente, result.get());
        verify(gerenteRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        GerenteDto gerenteDto = new GerenteDto();
        Gerente gerente = new Gerente();
        when(gerenteRepository.save(any(Gerente.class))).thenReturn(gerente);

        Gerente result = gerenteService.save(gerenteDto);

        assertNotNull(result);
        verify(gerenteRepository, times(1)).save(any(Gerente.class));
    }

    @Test
    void update() {
        UUID id = UUID.randomUUID();
        GerenteDto gerenteDto = new GerenteDto();
        Gerente gerente = new Gerente();
        when(gerenteRepository.findById(id)).thenReturn(Optional.of(gerente));
        when(gerenteRepository.save(gerente)).thenReturn(gerente);

        Optional<Gerente> result = gerenteService.update(id, gerenteDto);

        assertTrue(result.isPresent());
        assertEquals(gerente, result.get());
        verify(gerenteRepository, times(1)).findById(id);
        verify(gerenteRepository, times(1)).save(gerente);
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();
        Gerente gerente = mock(Gerente.class);
        when(gerenteRepository.findById(id)).thenReturn(Optional.of(gerente));

        boolean result = gerenteService.delete(id);

        assertTrue(result);
        verify(gerenteRepository, times(1)).findById(id);
        verify(gerenteRepository, times(1)).deleteById(id);
    }

    @Test
    void cadastrarCliente() {
        Cliente cliente = new Cliente();
        gerenteService.cadastrarCliente(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void consultarCliente() {
        Long id = 1L;
        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente result = gerenteService.consultarCliente(id);

        assertNotNull(result);
        assertEquals(cliente, result);
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void excluirCliente() {
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(true);

        gerenteService.excluirCliente(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    void atualizarCliente() {
        Cliente cliente = new Cliente();
        when(clienteRepository.existsById(cliente.getId())).thenReturn(true);

        gerenteService.atualizarCliente(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }
}