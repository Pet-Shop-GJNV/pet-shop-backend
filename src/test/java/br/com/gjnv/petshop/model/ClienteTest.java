package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Carlos");
        cliente.setTelefone("9999-9999");
        cliente.setCpf("123.456.789-00");
    }

    @Test
    public void criandoClienteCorretamente() {
        assertEquals(1L, cliente.getId());
        assertEquals("Carlos", cliente.getNome());
        assertEquals("9999-9999", cliente.getTelefone());
        assertEquals("123.456.789-00", cliente.getCpf());
    }

    @Test
    public void atualizandoNomeDoCliente() {
        cliente.setNome("Roberto");
        assertEquals("Roberto", cliente.getNome());
    }

    @Test
    public void atualizandoTelefoneDoCliente() {
        cliente.setTelefone("8888-8888");
        assertEquals("8888-8888", cliente.getTelefone());
    }

    @Test
    public void testConsultarServico() {
        String resultado = cliente.consultarServico(10L);
        assertEquals("Serviço consultado: 10", resultado);
    }

    @Test
    public void testSolicitarServico() {
        String resultado = cliente.solicitarServico("Banho");
        assertEquals("Serviço solicitado: Banho", resultado);
    }

    @Test
    public void testCancelarServico() {
        String resultado = cliente.cancelarServico(5L);
        assertEquals("Serviço cancelado: 5", resultado);
    }
}
