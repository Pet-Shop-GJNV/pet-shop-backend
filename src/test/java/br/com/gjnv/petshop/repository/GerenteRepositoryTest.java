//package br.com.gjnv.petshop.repository;
//
//import br.com.gjnv.petshop.model.Gerente;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@ActiveProfiles("test")
//class GerenteRepositoryTest {
//
//    @Autowired
//    private GerenteRepository gerenteRepository;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    private Gerente gerente;
//
//    @BeforeEach
//    void setUp() {
//        gerente = new Gerente();
//        gerente.setNome("Gabriel");
//        gerente.setCpf("222.333.444-55");
//        testEntityManager.persist(gerente);
//        testEntityManager.flush();
//    }
//
//    @Test
//    void testFindAll() {
//        List<Gerente> gerentes = gerenteRepository.findAll();
//        assertThat(gerentes).hasSize(1);
//        assertThat(gerentes.get(0).getNome()).isEqualTo("Gabriel");
//    }
//
//    @Test
//    void testSave() {
//        Gerente novoGerente = new Gerente();
//        novoGerente.setNome("Mariana");
//        novoGerente.setCpf("666.777.888-99");
//
//        gerenteRepository.save(novoGerente);
//        List<Gerente> gerentes = gerenteRepository.findAll();
//        assertThat(gerentes).hasSize(2);
//        assertThat(gerentes.get(1).getNome()).isEqualTo("Mariana");
//    }
//
//    @Test
//    void testDelete() {
//        gerenteRepository.delete(gerente);
//        testEntityManager.flush();
//
//        List<Gerente> gerentes = gerenteRepository.findAll();
//        assertThat(gerentes).isEmpty();
//    }
//
//    @Test
//    void testFindById() {
//        UUID id = gerente.getId();
//        Optional<Gerente> foundGerente = gerenteRepository.findById(id);
//        assertThat(foundGerente).isPresent();
//        assertThat(foundGerente.get().getNome()).isEqualTo("Gabriel");
//    }
//}
