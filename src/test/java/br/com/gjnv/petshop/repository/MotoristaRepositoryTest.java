package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Motorista;
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
class MotoristaRepositoryTest {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Motorista motorista;

    @BeforeEach
    void setUp() {
        motorista = new Motorista();
        motorista.setNome("Gabriel");
        motorista.setCpf("444.555.666-77");
        testEntityManager.persist(motorista);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Motorista> motoristas = motoristaRepository.findAll();
        assertThat(motoristas).hasSize(1);
        assertThat(motoristas.get(0).getNome()).isEqualTo("Gabriel");
    }

    @Test
    void testSave() {
        Motorista novoMotorista = new Motorista();
        novoMotorista.setNome("Fernanda");
        novoMotorista.setCpf("888.999.000-11");

        motoristaRepository.save(novoMotorista);
        List<Motorista> motoristas = motoristaRepository.findAll();
        assertThat(motoristas).hasSize(2);
        assertThat(motoristas.get(1).getNome()).isEqualTo("Fernanda");
    }

    @Test
    void testDelete() {
        motoristaRepository.delete(motorista);
        testEntityManager.flush();

        List<Motorista> motoristas = motoristaRepository.findAll();
        assertThat(motoristas).isEmpty();
    }

    @Test
    void testFindById() {
        UUID id = motorista.getId();
        Optional<Motorista> foundMotorista = motoristaRepository.findById(id);
        assertThat(foundMotorista).isPresent();
        assertThat(foundMotorista.get().getNome()).isEqualTo("Gabriel");
    }
}
