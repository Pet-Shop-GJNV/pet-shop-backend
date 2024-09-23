package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    private Servico servico;

    @BeforeEach
    public void setUp() {
        servico = new Servico();
        servico.setId(1);
        servico.setTipoServico("Banho");
        servico.setPreco(50.0);
        servico.setDuracao(30);
    }

    @Test
    public void criandoServicoCorretamente() {
        assertNotNull(servico.getId());
        assertEquals("Banho", servico.getTipoServico());
        assertEquals(50.0, servico.getPreco());
        assertEquals(30, servico.getDuracao());
    }

    @Test
    public void atualizandoTipoServico() {
        servico.setTipoServico("Tosa");
        assertEquals("Tosa", servico.getTipoServico());
    }

    @Test
    public void atualizandoPrecoServico() {
        servico.setPreco(60.0);
        assertEquals(60.0, servico.getPreco());
    }

    @Test
    public void atualizandoDuracaoServico() {
        servico.setDuracao(45);
        assertEquals(45, servico.getDuracao());
    }
}
