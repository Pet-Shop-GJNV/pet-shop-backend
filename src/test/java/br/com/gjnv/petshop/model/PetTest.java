package br.com.gjnv.petshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    private Pet pet;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Cliente 1", "12345678900");
        pet = new Pet(1, "Rex", 5, "Labrador", cliente);
    }

    @Test
    public void criandoPetCorretamente() {
        assertNotNull(pet.getId());
        assertEquals("Rex", pet.getNome());
        assertEquals(5, pet.getIdade());
        assertEquals("Labrador", pet.getRaca());
        assertNotNull(pet.getCliente());
        assertEquals("Cliente 1", pet.getCliente().getNome());
    }

    @Test
    public void atualizandoNomePet() {
        pet.setNome("Max");
        assertEquals("Max", pet.getNome());
    }

    @Test
    public void atualizandoIdadePet() {
        pet.setIdade(6);
        assertEquals(6, pet.getIdade());
    }

    @Test
    public void atualizandoRacaPet() {
        pet.setRaca("Poodle");
        assertEquals("Poodle", pet.getRaca());
    }

    @Test
    public void atualizandoClientePet() {
        Cliente novoCliente = new Cliente("Cliente 2", "98765432100");
        pet.setCliente(novoCliente);
        assertEquals("Cliente 2", pet.getCliente().getNome());
    }
}
