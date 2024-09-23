package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.exception.ResourceNotFoundException;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarEndereco() {
        Endereco endereco = new Endereco();
        enderecoService.adicionarEndereco(endereco);
        verify(enderecoRepository, times(1)).save(endereco);
    }

    @Test
    void testRemoverEndereco_EnderecoNaoEncontrado() {
        long id = 1;
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            enderecoService.removerEndereco(id);
        });

        assertEquals("Endereço com ID 1 não encontrado.", exception.getMessage());
    }

    @Test
    void testRemoverEndereco() {
        long id = 1;
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(new Endereco()));

        enderecoService.removerEndereco(id);

        verify(enderecoRepository, times(1)).deleteById(id);
    }

    @Test
    void testAtualizarEndereco_EnderecoNaoEncontrado() {
        long id = 1;
        EnderecoDto enderecoDto = new EnderecoDto();
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            enderecoService.atualizarEndereco(id, enderecoDto);
        });

        assertEquals("Endereço com ID 1 não encontrado.", exception.getMessage());
    }

    @Test
    void testAtualizarEndereco() {
        long id = 1;
        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setRua("Rua A");
        enderecoDto.setNumero(123);
        enderecoDto.setBairro("Bairro A");
        enderecoDto.setCidade("Cidade A");
        enderecoDto.setComplemento("Complemento A");

        Endereco enderecoExistente = new Endereco();
        enderecoExistente.setRua("Rua B");

        when(enderecoRepository.findById(id)).thenReturn(Optional.of(enderecoExistente));
        when(enderecoRepository.save(any(Endereco.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDto);

        assertNotNull(enderecoAtualizado);
        assertEquals("Rua A", enderecoAtualizado.getRua());
        assertEquals(123, enderecoAtualizado.getNumero());
        assertEquals("Bairro A", enderecoAtualizado.getBairro());
        assertEquals("Cidade A", enderecoAtualizado.getCidade());
        assertEquals("Complemento A", enderecoAtualizado.getComplemento());
    }

    @Test
    void testListarEnderecos() {
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();

        when(enderecoRepository.findAll()).thenReturn(Arrays.asList(endereco1, endereco2));

        List<Endereco> enderecos = enderecoService.listarEnderecos();

        assertNotNull(enderecos);
        assertEquals(2, enderecos.size());
    }

    @Test
    void testBuscarEnderecoPorId_EnderecoNaoEncontrado() {
        long id = 1;
        when(enderecoRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            enderecoService.buscarEnderecoPorId(id);
        });

        assertEquals("Endereço com ID 1 não encontrado.", exception.getMessage());
    }

    @Test
    void testBuscarEnderecoPorId() {
        long id = 1;
        Endereco endereco = new Endereco();
        when(enderecoRepository.findById(id)).thenReturn(Optional.of(endereco));

        Endereco enderecoEncontrado = enderecoService.buscarEnderecoPorId(id);

        assertNotNull(enderecoEncontrado);
    }
}
