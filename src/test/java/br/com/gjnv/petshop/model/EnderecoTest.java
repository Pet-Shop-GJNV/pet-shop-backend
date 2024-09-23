package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnderecoTest {

    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        endereco = new Endereco("Rua A", 123, "Bairro B", "Cidade C", "Apt 45");
        endereco.setId(1L);
    }

    @Test
    public void criandoEnderecoCorretamente() {
        assertEquals(1L, endereco.getId());
        assertEquals("Rua A", endereco.getRua());
        assertEquals(123, endereco.getNumero());
        assertEquals("Bairro B", endereco.getBairro());
        assertEquals("Cidade C", endereco.getCidade());
        assertEquals("Apt 45", endereco.getComplemento());
    }

    @Test
    public void atualizandoRuaDoEndereco() {
        endereco.setRua("Rua Nova");
        assertEquals("Rua Nova", endereco.getRua());
    }

    @Test
    public void atualizandoNumeroDoEndereco() {
        endereco.setNumero(456);
        assertEquals(456, endereco.getNumero());
    }

    @Test
    public void atualizandoBairroDoEndereco() {
        endereco.setBairro("Bairro Novo");
        assertEquals("Bairro Novo", endereco.getBairro());
    }

    @Test
    public void atualizandoCidadeDoEndereco() {
        endereco.setCidade("Cidade Nova");
        assertEquals("Cidade Nova", endereco.getCidade());
    }

    @Test
    public void atualizandoComplementoDoEndereco() {
        endereco.setComplemento("Apt 100");
        assertEquals("Apt 100", endereco.getComplemento());
    }
}
