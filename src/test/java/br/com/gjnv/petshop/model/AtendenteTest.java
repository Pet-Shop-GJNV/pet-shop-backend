package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AtendenteTest {

    private Atendente atendente;
    private Endereco enderecoMock;
    private Cliente clienteMock;
    private Pet petMock;

    @BeforeEach
    public void setUp() {
        enderecoMock = new Endereco();
        clienteMock = new Cliente();
        petMock = new Pet();
        atendente = new Atendente("João", "123.456.789-00", enderecoMock, "9999-9999", "123456", "Carro", new Date(), "08:00 - 17:00", "Atendente", 2000.0);
    }

    @Test
    public void criandoAtendenteCorretamente() {
        assertEquals("João", atendente.getNome());
        assertEquals("123.456.789-00", atendente.getCpf());
        assertEquals("9999-9999", atendente.getTelefone());
        assertFalse(atendente.isServicoRealizado());
    }

    @Test
    public void associandoEnderecoAoAtendente() {
        atendente.setEndereco(enderecoMock);
        assertEquals(enderecoMock, atendente.getEndereco());
    }

    @Test
    public void realizandoAtendimentoDeveAtualizarServicoRealizado() {
        atendente.realizarAtendimento();
        atendente.atualizarStatusServico();
        assertTrue(atendente.isServicoRealizado(), "O serviço deveria estar marcado como realizado.");
    }

    @Test
    public void testCadastrarCliente() {
        clienteMock.setNome("Carlos");
        atendente.cadastrarCliente(clienteMock);
        assertEquals("Carlos", clienteMock.getNome());
    }

    @Test
    public void testConsultarCliente() {
        Cliente clienteConsultado = atendente.consultarCliente(1L);
        assertNotNull(clienteConsultado);
    }

    @Test
    public void testAtualizarCliente() {
        clienteMock.setNome("Carlos Atualizado");
        atendente.atualizarCliente(clienteMock);
        assertEquals("Carlos Atualizado", clienteMock.getNome());
    }

    @Test
    public void testExcluirCliente() {
        atendente.excluirCliente(1L);
    }

    @Test
    public void testCadastrarPet() {
        atendente.cadastrarPet(petMock);
    }

    @Test
    public void testConsultarPet() {
        Pet petConsultado = atendente.consultarPet(1L);
        assertNull(petConsultado);
    }

    @Test
    public void testAtualizarPet() {
        atendente.atualizarPet(petMock);
    }

    @Test
    public void testExcluirPet() {
        atendente.excluirPet(1L);
    }

    @Test
    public void testRegistrarServico() {
        atendente.registrarServico();
    }

    @Test
    public void testAtualizarPagamento() {
        atendente.atualizarPagamento();
    }

    @Test
    public void testConsultarAgenda() {
        atendente.consultarAgenda();
    }
}
