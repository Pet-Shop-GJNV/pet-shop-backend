package br.com.gjnv.petshop.model;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MotoristaTest {

    private Motorista motorista;
    private Endereco endereco;

    @BeforeEach
    public void setUp() {
        endereco = new Endereco("Rua A", 123, "Bairro B", "Cidade C", "Apt 45");

        motorista = new Motorista("Motorista 1", "12345678900", endereco, "1234-5678", "CNH1234", "Carro", new Date(), "08:00-17:00", "Motorista", 2000.00);
        motorista.setId(UUID.randomUUID());
    }

    @Test
    public void criandoMotoristaCorretamente() {
        assertNotNull(motorista.getId());
        assertEquals("Motorista 1", motorista.getNome());
        assertEquals("12345678900", motorista.getCpf());
        assertEquals("1234-5678", motorista.getTelefone());
        assertNotNull(motorista.getDataContratacao());
        assertEquals("08:00-17:00", motorista.getHorarioTrabalho());
        assertEquals("Motorista", motorista.getCargo());
        assertEquals(2000.00, motorista.getSalario());
        assertEquals("CNH1234", motorista.getCnh());
        assertEquals("Carro", motorista.getVeiculo());
        assertNull(motorista.getRotaAtual());
    }

    @Test
    public void atualizandoVeiculo() {
        motorista.setVeiculo("Moto");
        assertEquals("Moto", motorista.getVeiculo());
    }

    @Test
    public void atualizandoCnh() {
        motorista.setCnh("CNH5678");
        assertEquals("CNH5678", motorista.getCnh());
    }

    @Test
    public void realizandoColeta() {
        Endereco novaRota = new Endereco("Rua B", 456, "Bairro D", "Cidade E", "Apt 67");
        motorista.realizarColeta(novaRota);
        assertEquals(novaRota, motorista.getRotaAtual());
    }

    @Test
    public void realizandoEntrega() {
        Endereco novaRota = new Endereco("Rua C", 789, "Bairro E", "Cidade F", "Apt 89");
        motorista.realizarEntrega(novaRota);
        assertEquals(novaRota, motorista.getRotaAtual());
    }
}
