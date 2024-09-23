package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.repository.EnderecoRepository;
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

class MotoristaServiceTest {

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private MotoristaService motoristaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Motorista motorista1 = new Motorista();
        Motorista motorista2 = new Motorista();
        List<Motorista> motoristas = Arrays.asList(motorista1, motorista2);

        when(motoristaRepository.findAll()).thenReturn(motoristas);

        List<Motorista> result = motoristaService.findAll();

        assertEquals(2, result.size());
        verify(motoristaRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        Motorista motorista = new Motorista();
        when(motoristaRepository.findById(id)).thenReturn(Optional.of(motorista));

        Optional<Motorista> result = motoristaService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(motorista, result.get());
        verify(motoristaRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        Motorista motorista = new Motorista();
        Endereco endereco = new Endereco();
        motorista.setEndereco(endereco);

        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(motoristaRepository.save(any(Motorista.class))).thenReturn(motorista);

        Motorista result = motoristaService.save(motorista);

        assertNotNull(result);
        assertEquals(motorista, result);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
        verify(motoristaRepository, times(1)).save(any(Motorista.class));
    }

    @Test
    void update() {
        UUID id = UUID.randomUUID();
        Motorista motorista = new Motorista();
        Motorista motoristaDetails = new Motorista();
        Endereco endereco = new Endereco();
        motorista.setEndereco(endereco);
        motoristaDetails.setEndereco(endereco);

        when(motoristaRepository.findById(id)).thenReturn(Optional.of(motorista));
        when(motoristaRepository.save(any(Motorista.class))).thenReturn(motorista);

        Optional<Motorista> result = motoristaService.update(id, motoristaDetails);

        assertTrue(result.isPresent());
        assertEquals(motorista, result.get());
        verify(motoristaRepository, times(1)).findById(id);
        verify(motoristaRepository, times(1)).save(any(Motorista.class));
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();
        Motorista motorista = new Motorista();
        Endereco endereco = new Endereco();
        motorista.setEndereco(endereco);

        when(motoristaRepository.findById(id)).thenReturn(Optional.of(motorista));

        boolean result = motoristaService.delete(id);

        assertTrue(result);
        verify(motoristaRepository, times(1)).findById(id);
        verify(motoristaRepository, times(1)).delete(motorista);
        verify(enderecoRepository, times(1)).delete(endereco);
    }

    @Test
    void realizarColeta() {
        UUID id = UUID.randomUUID();
        Motorista motorista = new Motorista();
        Endereco endereco = new Endereco();

        when(motoristaRepository.findById(id)).thenReturn(Optional.of(motorista));
        when(motoristaRepository.save(any(Motorista.class))).thenReturn(motorista);

        boolean result = motoristaService.realizarColeta(id, endereco);

        assertTrue(result);
        verify(motoristaRepository, times(1)).findById(id);
        verify(motoristaRepository, times(1)).save(any(Motorista.class));
    }

    @Test
    void realizarEntrega() {
        UUID id = UUID.randomUUID();
        Motorista motorista = new Motorista();
        Endereco endereco = new Endereco();

        when(motoristaRepository.findById(id)).thenReturn(Optional.of(motorista));
        when(motoristaRepository.save(any(Motorista.class))).thenReturn(motorista);

        boolean result = motoristaService.realizarEntrega(id, endereco);

        assertTrue(result);
        verify(motoristaRepository, times(1)).findById(id);
        verify(motoristaRepository, times(1)).save(any(Motorista.class));
    }
}