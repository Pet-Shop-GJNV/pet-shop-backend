package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
class AtendenteRepositoryTest {

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Atendente atendente;

    @BeforeEach
    void setUp() {
        atendente = new Atendente("João da Silva", "12345678900", new Endereco(), "9999-9999", new Date(), "08:00 - 17:00", "Atendente", 2000.0);
        atendenteRepository.save(atendente);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Atendente> atendentes = atendenteRepository.findAll();
        assertThat(atendentes).hasSize(1);
        assertThat(atendentes.get(0).getNome()).isEqualTo("João da Silva");
    }

    @Test
    void testSave() {
        Atendente novoAtendente = new Atendente("Maria Oliveira", "12345678901", new Endereco(), "9999-9988", new Date(), "08:00 - 17:00", "Atendente", 2000.0);
        atendenteRepository.save(novoAtendente);
        List<Atendente> atendentes = atendenteRepository.findAll();
        assertThat(atendentes).hasSize(2);
        assertThat(atendentes.get(1).getNome()).isEqualTo("Maria Oliveira");
    }

    @Test
    void testDelete() {
        List<Atendente> atendentes = atendenteRepository.findAll();
        assertThat(atendentes).hasSize(1);
        atendenteRepository.delete(atendente);
        atendentes = atendenteRepository.findAll();
        assertThat(atendentes).isEmpty();
    }

    @Test
    void testFindById() {
        Optional<Atendente> foundAtendente = atendenteRepository.findById(atendente.getId());
        assertThat(foundAtendente).isPresent();
        assertThat(foundAtendente.get().getNome()).isEqualTo("João da Silva");
    }

    @Test
    void testFindByNome() {
        Atendente foundAtendente = atendenteRepository.findAtendenteByNome("João da Silva");
        assertEquals(foundAtendente.getNome(), "João da Silva");
    }
}
