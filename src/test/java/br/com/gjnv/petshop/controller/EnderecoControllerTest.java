package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.facade.EnderecoFacade;
import br.com.gjnv.petshop.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoControllerTest {

    @Mock
    private EnderecoFacade enderecoFacade;

    @InjectMocks
    private EnderecoController enderecoController;

    private Endereco endereco;
    private EnderecoDto enderecoDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        endereco = new Endereco();
        enderecoDto = new EnderecoDto();
    }

    @Test
    void listarEnderecos_DeveRetornarListaDeEnderecos() {
        // Arrange
        List<Endereco> enderecos = Arrays.asList(new Endereco(), new Endereco());
        when(enderecoFacade.listarEnderecos()).thenReturn(enderecos);

        // Act
        List<Endereco> response = enderecoController.listarEnderecos();

        // Assert
        assertEquals(enderecos, response);
    }

    @Test
    void buscarEnderecoPorId_DeveRetornarEndereco() {
        // Arrange
        long enderecoId = 1L;
        when(enderecoFacade.buscarEnderecoPorId(enderecoId)).thenReturn(endereco);

        // Act
        Endereco response = enderecoController.buscarEnderecoPorId(enderecoId);

        // Assert
        assertEquals(endereco, response);
    }

    @Test
    void adicionarEndereco_DeveAdicionarEndereco() {
        // Arrange
        doNothing().when(enderecoFacade).adicionarEndereco(endereco);

        // Act
        enderecoController.adicionarEndereco(endereco);

        // Assert
        verify(enderecoFacade, times(1)).adicionarEndereco(endereco);
    }

    @Test
    void removerEndereco_DeveRemoverEndereco() {
        // Arrange
        long enderecoId = 1L;
        doNothing().when(enderecoFacade).removerEndereco(enderecoId);

        // Act
        enderecoController.removerEndereco(enderecoId);

        // Assert
        verify(enderecoFacade, times(1)).removerEndereco(enderecoId);
    }

    @Test
    void atualizarEndereco_DeveAtualizarEndereco() {
        // Arrange
        long enderecoId = 1L;
        doNothing().when(enderecoFacade).atualizarEndereco(enderecoId, enderecoDto);

        // Act
        enderecoController.atualizarEndereco(enderecoId, enderecoDto);

        // Assert
        verify(enderecoFacade, times(1)).atualizarEndereco(enderecoId, enderecoDto);
    }
}
