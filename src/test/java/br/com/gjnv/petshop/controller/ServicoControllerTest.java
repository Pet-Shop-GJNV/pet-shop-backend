package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.ServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicoControllerTest {

    @InjectMocks
    private ServicoController servicoController;

    @Mock
    private ServicoService servicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsultarServicoList() {
        Servico servico1 = new Servico();
        servico1.setId(1);
        Servico servico2 = new Servico();
        servico2.setId(2);

        when(servicoService.listarServicos()).thenReturn(Arrays.asList(servico1, servico2));

        List<Servico> servicos = servicoController.consultarServico();

        assertNotNull(servicos);
        assertEquals(2, servicos.size());
    }

    @Test
    void testConsultarServicoById() {
        int id = 1;
        Servico servico = new Servico();
        servico.setId(id);

        when(servicoService.consultarServico(id)).thenReturn(Optional.of(servico));

        Optional<Servico> foundServico = servicoController.consultarServico(id);

        assertTrue(foundServico.isPresent());
        assertEquals(id, foundServico.get().getId());
    }

    @Test
    void testCancelarServicoSuccess() {
        int id = 1;

        doNothing().when(servicoService).cancelarServico(id);

        ResponseEntity<String> response = servicoController.cancelarServico(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Serviço cancelado com sucesso.", response.getBody());
    }

    @Test
    void testCancelarServicoNotFound() {
        int id = 1;

        doThrow(new RuntimeException("Serviço não encontrado.")).when(servicoService).cancelarServico(id);

        ResponseEntity<String> response = servicoController.cancelarServico(id);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Serviço não encontrado.", response.getBody());
    }

    @Test
    void testAdicionarServico() {
        Servico servico = new Servico();
        servico.setId(1);

        doNothing().when(servicoService).adicionarServico(servico);

        servicoController.adicionarServico(servico);

        verify(servicoService, times(1)).adicionarServico(servico);
    }
}
