package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarEnderecos() {
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();

        when(enderecoService.listarEnderecos()).thenReturn(Arrays.asList(endereco1, endereco2));

        List<Endereco> enderecos = enderecoController.listarEnderecos();

        assertNotNull(enderecos);
        assertEquals(2, enderecos.size());
    }

    @Test
    void testBuscarEnderecoPorId() {
        long id = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(id);

        when(enderecoService.buscarEnderecoPorId(id)).thenReturn(endereco);

        Endereco foundEndereco = enderecoController.buscarEnderecoPorId(id);

        assertNotNull(foundEndereco);
        assertEquals(id, foundEndereco.getId());
    }

    @Test
    void testAdicionarEndereco() {
        Endereco endereco = new Endereco();
        endereco.setId(1L);

        doNothing().when(enderecoService).adicionarEndereco(endereco);

        enderecoController.adicionarEndereco(endereco);

        verify(enderecoService, times(1)).adicionarEndereco(endereco);
    }

    @Test
    void testRemoverEndereco() {
        long id = 1L;

        doNothing().when(enderecoService).removerEndereco(id);

        enderecoController.removerEndereco(id);

        verify(enderecoService, times(1)).removerEndereco(id);
    }

    @Test
    void testAtualizarEndereco() {
        long id = 1L;
        EnderecoDto enderecoDto = new EnderecoDto();

        enderecoController.atualizarEndereco(id, enderecoDto);

        verify(enderecoService, times(1)).atualizarEndereco(id, enderecoDto);
    }

}
