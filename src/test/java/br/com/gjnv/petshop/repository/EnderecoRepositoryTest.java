package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco();
        endereco.setRua("Rua das Flores");
        endereco.setNumero(123);
        endereco.setCidade("São Paulo");
        endereco.setBairro("SP");
        testEntityManager.persist(endereco);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        assertThat(enderecos).hasSize(1);
        assertThat(enderecos.get(0).getRua()).isEqualTo("Rua das Flores");
    }

    @Test
    void testSave() {
        Endereco novoEndereco = new Endereco();
        novoEndereco.setRua("Avenida Brasil");
        novoEndereco.setNumero(456);
        novoEndereco.setCidade("Rio de Janeiro");
        novoEndereco.setBairro("RJ");

        enderecoRepository.save(novoEndereco);
        List<Endereco> enderecos = enderecoRepository.findAll();
        assertThat(enderecos).hasSize(2);
        assertThat(enderecos.get(1).getRua()).isEqualTo("Avenida Brasil");
    }

    @Test
    void testDelete() {
        enderecoRepository.delete(endereco);
        testEntityManager.flush();

        List<Endereco> enderecos = enderecoRepository.findAll();
        assertThat(enderecos).isEmpty();
    }

    @Test
    void testFindById() {
        Long id = endereco.getId();
        Optional<Endereco> foundEndereco = enderecoRepository.findById(id);
        assertThat(foundEndereco).isPresent();
        assertThat(foundEndereco.get().getRua()).isEqualTo("Rua das Flores");
    }
}