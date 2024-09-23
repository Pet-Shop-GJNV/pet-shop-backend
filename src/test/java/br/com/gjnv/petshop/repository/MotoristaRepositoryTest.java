package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
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
        Endereco endereco = new Endereco();
        testEntityManager.persist(endereco);
        motorista = new Motorista("Joao", "12345678901", endereco, "11987654321", "ABCDXPTO123", "Ferrari", new Date(), "07:00 - 17:00", "Motorista", 1900.00);
        testEntityManager.persist(motorista);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Motorista> motoristas = motoristaRepository.findAll();
        assertThat(motoristas).hasSize(1);
        assertThat(motoristas.get(0).getNome()).isEqualTo("Joao");
    }

    @Test
    void testSave() {
        Endereco endereco = new Endereco();
        testEntityManager.persist(endereco);
        Motorista novoMotorista = new Motorista("Amanda", "12345678902", endereco, "11987654322", "ABCDEPTO123", "Ferrari", new Date(), "07:00 - 17:00", "Motorista", 1900.00);

        motoristaRepository.save(novoMotorista);
        List<Motorista> motoristas = motoristaRepository.findAll();
        assertThat(motoristas).hasSize(2);
        assertThat(motoristas.get(1).getNome()).isEqualTo("Amanda");
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
        assertThat(foundMotorista.get().getNome()).isEqualTo("Joao");
    }
}
