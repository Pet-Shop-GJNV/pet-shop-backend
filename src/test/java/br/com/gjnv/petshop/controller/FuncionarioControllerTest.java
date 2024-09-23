//package br.com.gjnv.petshop.controller;
//
//import br.com.gjnv.petshop.model.Funcionario;
//import br.com.gjnv.petshop.service.FuncionarioService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class FuncionarioControllerTest {
//
//    @Mock
//    private FuncionarioService funcionarioService;
//
//    @InjectMocks
//    private FuncionarioController funcionarioController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void listarTodosFuncionarios() {
//        List<Funcionario> funcionarios = Arrays.asList(mock(Funcionario.class), mock(Funcionario.class));
//        when(funcionarioService.listarTodosFuncionarios()).thenReturn(funcionarios);
//
//        List<Funcionario> result = funcionarioController.listarTodosFuncionarios();
//
//        assertEquals(2, result.size());
//        verify(funcionarioService, times(1)).listarTodosFuncionarios();
//    }
//
//    @Test
//    void buscarPorId() {
//        UUID id = UUID.randomUUID();
//        Funcionario funcionario = mock(Funcionario.class);
//        when(funcionarioService.buscarPorId(id)).thenReturn(Optional.of(funcionario));
//
//        Optional<Funcionario> result = funcionarioController.buscarPorId(id);
//
//        assertTrue(result.isPresent());
//        assertEquals(funcionario, result.get());
//        verify(funcionarioService, times(1)).buscarPorId(id);
//    }
//
//    @Test
//    void salvarFuncionario() {
//        Funcionario funcionario = mock(Funcionario.class);
//        when(funcionarioService.salvarFuncionario(funcionario)).thenReturn(funcionario);
//
//        Funcionario result = funcionarioController.salvarFuncionario(funcionario);
//
//        assertEquals(funcionario, result);
//        verify(funcionarioService, times(1)).salvarFuncionario(funcionario);
//    }
//
//    @Test
//    void deletarFuncionario() {
//        UUID id = UUID.randomUUID();
//
//        funcionarioController.deletarFuncionario(id);
//
//        verify(funcionarioService, times(1)).deletarFuncionario(id);
//    }
//}