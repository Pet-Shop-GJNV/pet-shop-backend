package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.FuncionarioDto;
import br.com.gjnv.petshop.model.Funcionario;
import br.com.gjnv.petshop.repository.FuncionarioRepository;
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

class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioService funcionarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosFuncionarios() {
        List<Funcionario> funcionarios = Arrays.asList(mock(Funcionario.class), mock(Funcionario.class));
        when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        List<Funcionario> result = funcionarioService.listarTodosFuncionarios();

        assertEquals(2, result.size());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId() {
        UUID id = UUID.randomUUID();
        Funcionario funcionario = mock(Funcionario.class);
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionario));

        Optional<Funcionario> result = funcionarioService.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(funcionario, result.get());
        verify(funcionarioRepository, times(1)).findById(id);
    }

    @Test
    void salvarFuncionario() {
        Funcionario funcionario = mock(Funcionario.class);
        when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

        Funcionario result = funcionarioService.salvarFuncionario(funcionario);

        assertEquals(funcionario, result);
        verify(funcionarioRepository, times(1)).save(funcionario);
    }

    @Test
    void deletarFuncionario() {
        UUID id = UUID.randomUUID();

        funcionarioService.deletarFuncionario(id);

        verify(funcionarioRepository, times(1)).deleteById(id);
    }

    @Test
    void convertToDTO() {
        Funcionario funcionario = mock(Funcionario.class);
        when(funcionario.getId()).thenReturn(UUID.randomUUID());
        when(funcionario.getNome()).thenReturn("Nome");
        when(funcionario.getCpf()).thenReturn("12345678900");
        when(funcionario.getTelefone()).thenReturn("123456789");
        when(funcionario.getCargo()).thenReturn("Cargo");

        FuncionarioDto dto = funcionarioService.convertToDTO(funcionario);

        assertEquals(funcionario.getId(), dto.getId());
        assertEquals(funcionario.getNome(), dto.getNome());
        assertEquals(funcionario.getCpf(), dto.getCpf());
        assertEquals(funcionario.getTelefone(), dto.getTelefone());
        assertEquals(funcionario.getCargo(), dto.getCargo());
    }
}