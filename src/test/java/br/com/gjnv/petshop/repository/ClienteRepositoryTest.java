package br.com.gjnv.petshop.repository;

import br.com.gjnv.petshop.model.Cliente;
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
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNome("Ana Maria");
        cliente.setCpf("111.222.333-44");
        // Configure outros campos conforme necessário
        testEntityManager.persist(cliente);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(1);
        assertThat(clientes.get(0).getNome()).isEqualTo("Ana Maria");
    }

    @Test
    void testSave() {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Carlos Eduardo");
        novoCliente.setCpf("555.666.777-88");
        // Configure outros campos conforme necessário

        clienteRepository.save(novoCliente);
        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).hasSize(2);
        assertThat(clientes.get(1).getNome()).isEqualTo("Carlos Eduardo");
    }

    @Test
    void testDelete() {
        clienteRepository.delete(cliente);
        testEntityManager.flush();

        List<Cliente> clientes = clienteRepository.findAll();
        assertThat(clientes).isEmpty();
    }

    @Test
    void testFindById() {
        Long id = cliente.getId();
        Optional<Cliente> foundCliente = clienteRepository.findById(id);
        assertThat(foundCliente).isPresent();
        assertThat(foundCliente.get().getNome()).isEqualTo("Ana Maria");
    }
}
