package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.ServicoRepository;
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

class ServicoServiceTest {

    @InjectMocks
    private ServicoService servicoService;

    @Mock
    private ServicoRepository servicoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarServicos() {
        Servico servico1 = new Servico();
        Servico servico2 = new Servico();

        when(servicoRepository.findAll()).thenReturn(Arrays.asList(servico1, servico2));

        List<Servico> servicos = servicoService.listarServicos();

        assertNotNull(servicos);
        assertEquals(2, servicos.size());
    }

    @Test
    void testConsultarServico() {
        int id = 1;
        Servico servico = new Servico();
        servico.setId(id);

        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));

        Optional<Servico> foundServico = servicoService.consultarServico(id);

        assertTrue(foundServico.isPresent());
        assertEquals(id, foundServico.get().getId());
    }

    @Test
    void testCancelarServico() {
        int id = 1;
        Servico servico = new Servico();
        servico.setId(id);

        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));

        Optional<Servico> foundServico = servicoService.consultarServico(id);
        assertTrue(foundServico.isPresent());

        servicoService.cancelarServico(foundServico.get().getId());

        verify(servicoRepository, times(1)).deleteById(id);
    }

    @Test
    void testAdicionarServico() {
        Servico servico = new Servico("Banho", 50.0, 60);
        servicoService.adicionarServico(servico);
        verify(servicoRepository, times(1)).save(servico);
    }
}
