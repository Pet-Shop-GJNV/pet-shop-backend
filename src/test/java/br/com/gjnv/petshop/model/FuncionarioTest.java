package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FuncionarioTest {

    private FuncionarioTestImpl funcionario;
    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        endereco = new Endereco("Rua A", 123, "Bairro B", "Cidade C", "Apt 45");
        funcionario = new FuncionarioTestImpl("João", "12345678901", endereco, "1234-5678", new Date(), "08:00-17:00", "Atendente", 2000.00);
        funcionario.setId(UUID.randomUUID());
    }

    @Test
    public void criandoFuncionarioCorretamente() {
        assertNotNull(funcionario.getId());
        assertEquals("João", funcionario.getNome());
        assertEquals("12345678901", funcionario.getCpf());
        assertEquals("1234-5678", funcionario.getTelefone());
        assertNotNull(funcionario.getDataContratacao());
        assertEquals("08:00-17:00", funcionario.getHorarioTrabalho());
        assertEquals("Atendente", funcionario.getCargo());
        assertEquals(2000.00, funcionario.getSalario());
        assertEquals(endereco, funcionario.getEndereco());
    }

    @Test
    public void atualizandoNomeDoFuncionario() {
        funcionario.setNome("Maria");
        assertEquals("Maria", funcionario.getNome());
    }

    @Test
    public void atualizandoCpfDoFuncionario() {
        funcionario.setCpf("09876543210");
        assertEquals("09876543210", funcionario.getCpf());
    }

    @Test
    public void atualizandoTelefoneDoFuncionario() {
        funcionario.setTelefone("9876-5432");
        assertEquals("9876-5432", funcionario.getTelefone());
    }

    @Test
    public void atualizandoHorarioDeTrabalho() {
        funcionario.setHorarioTrabalho("09:00-18:00");
        assertEquals("09:00-18:00", funcionario.getHorarioTrabalho());
    }

    @Test
    public void atualizandoCargo() {
        funcionario.setCargo("Gerente");
        assertEquals("Gerente", funcionario.getCargo());
    }

    @Test
    public void atualizandoSalario() {
        funcionario.setSalario(3000.00);
        assertEquals(3000.00, funcionario.getSalario());
    }

    @Test
    public void atualizandoEndereco() {
        Endereco novoEndereco = new Endereco("Rua B", 456, "Bairro Novo", "Cidade Nova", "Apt 67");
        funcionario.setEndereco(novoEndereco);
        assertEquals(novoEndereco, funcionario.getEndereco());
    }

    // Implementação concreta da classe Funcionario apenas para fins de teste
    private static class FuncionarioTestImpl extends Funcionario {
        public FuncionarioTestImpl(String nome, String cpf, Endereco endereco, String telefone, Date dataContratacao, String horarioTrabalho, String cargo, double salario) {
            super(nome, cpf, endereco, telefone, dataContratacao, horarioTrabalho, cargo, salario);
        }
    }
}
