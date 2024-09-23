package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Atendente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
        atendente = new Atendente();
        atendente.setNome("João da Silva");
        atendente.setCpf("123.456.789-00");
        // Configure outros campos conforme necessário
        testEntityManager.persist(atendente);
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
        Atendente novoAtendente = new Atendente();
        novoAtendente.setNome("Maria Oliveira");
        novoAtendente.setCpf("987.654.321-00");
        // Configure outros campos conforme necessário

        atendenteRepository.save(novoAtendente);
        List<Atendente> atendentes = atendenteRepository.findAll();
        assertThat(atendentes).hasSize(2);
        assertThat(atendentes.get(1).getNome()).isEqualTo("Maria Oliveira");
    }

    @Test
    void testDelete() {
        atendenteRepository.delete(atendente);
        testEntityManager.flush();

        List<Atendente> atendentes = atendenteRepository.findAll();
        assertThat(atendentes).isEmpty();
    }

    @Test
    void testFindById() {
        UUID id = atendente.getId();
        Optional<Atendente> foundAtendente = atendenteRepository.findById(id);
        assertThat(foundAtendente).isPresent();
        assertThat(foundAtendente.get().getNome()).isEqualTo("João da Silva");
    }
}
