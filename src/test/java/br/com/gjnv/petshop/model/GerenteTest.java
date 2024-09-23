package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GerenteTest {

    private Gerente gerente;
    private Endereco endereco;
    private List<Motorista> motoristas;
    private List<Atendente> atendentes;

    @BeforeEach
    public void setUp() {
        endereco = new Endereco("Rua A", 123, "Bairro B", "Cidade C", "Apt 45");

        // Inicializando listas de Motoristas e Atendentes
        motoristas = new ArrayList<>();
        motoristas.add(new Motorista("Motorista 1", "12345678900", endereco, "1234-5678", "ABC1234", "Carro", new Date(), "08:00-17:00", "Motorista", 2000.00));

        atendentes = new ArrayList<>();
        atendentes.add(new Atendente("Atendente 1", "09876543210", endereco, "9876-5432", "XYZ123", "Carro", new Date(), "08:00-17:00", "Atendente", 1500.00));

        gerente = new Gerente();
        gerente.setNome("Gerente 1");
        gerente.setCpf("11122233344");
        gerente.setEndereco(endereco);
        gerente.setTelefone("1234-5678");
        gerente.setDataContratacao(new Date());
        gerente.setHorarioTrabalho("08:00-17:00");
        gerente.setCargo("Gerente Geral");
        gerente.setSalario(5000.00);
        gerente.setSetorResponsavel("Operações");
        gerente.setMotoristas(motoristas);
        gerente.setAtendentes(atendentes);
        gerente.setMetaMensal(100000.00);
        gerente.setId(UUID.randomUUID());
    }

    @Test
    public void criandoGerenteCorretamente() {
        assertNotNull(gerente.getId());
        assertEquals("Gerente 1", gerente.getNome());
        assertEquals("11122233344", gerente.getCpf());
        assertEquals("1234-5678", gerente.getTelefone());
        assertNotNull(gerente.getDataContratacao());
        assertEquals("08:00-17:00", gerente.getHorarioTrabalho());
        assertEquals("Gerente Geral", gerente.getCargo());
        assertEquals(5000.00, gerente.getSalario());
        assertEquals("Operações", gerente.getSetorResponsavel());
        assertEquals(1, gerente.getMotoristas().size());
        assertEquals(1, gerente.getAtendentes().size());
        assertEquals(100000.00, gerente.getMetaMensal());
    }

    @Test
    public void atualizandoSetorResponsavel() {
        gerente.setSetorResponsavel("Vendas");
        assertEquals("Vendas", gerente.getSetorResponsavel());
    }

    @Test
    public void atualizandoMetaMensal() {
        gerente.setMetaMensal(150000.00);
        assertEquals(150000.00, gerente.getMetaMensal());
    }

    @Test
    public void atualizandoListaDeMotoristas() {
        List<Motorista> novosMotoristas = new ArrayList<>();
        novosMotoristas.add(new Motorista("Motorista 2", "22233344455", endereco, "4321-8765", "ABC1234", "Carro", new Date(), "08:00-17:00", "Motorista", 2000.00));
        gerente.setMotoristas(novosMotoristas);
        assertEquals(1, gerente.getMotoristas().size());
        assertEquals("Motorista 2", gerente.getMotoristas().get(0).getNome());
    }

    @Test
    public void atualizandoListaDeAtendentes() {
        List<Atendente> novosAtendentes = new ArrayList<>();
        novosAtendentes.add(new Atendente("Atendente 2", "33344455566", endereco, "8765-4321", "LMN789", "Moto", new Date(), "09:00-18:00", "Atendente", 1600.00));
        gerente.setAtendentes(novosAtendentes);
        assertEquals(1, gerente.getAtendentes().size());
        assertEquals("Atendente 2", gerente.getAtendentes().get(0).getNome());
    }
}
