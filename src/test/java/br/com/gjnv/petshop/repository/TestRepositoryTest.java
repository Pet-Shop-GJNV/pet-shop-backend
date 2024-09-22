package br.com.gjnv.petshop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import br.com.gjnv.petshop.model.Tests;

@DataJpaTest
@ActiveProfiles("test")
class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Tests testEntity;

    @BeforeEach
    void setUp() {
        testEntity = new Tests("Test Name", "Test Description");
        testRepository.save(testEntity);
        testEntityManager.flush();
    }

    @Test
    void testFindAll() {
        List<Tests> allTests = testRepository.findAll();
        assertThat(allTests).hasSize(1);
        assertThat(allTests.get(0).getName()).isEqualTo("Test Name");
    }

    @Test
    void testSave() {
        Tests newTest = new Tests("Another Test", "Another Description");
        testRepository.save(newTest);
        List<Tests> allTests = testRepository.findAll();
        assertThat(allTests).hasSize(2);
        assertThat(allTests.get(1).getName()).isEqualTo("Another Test");
    }

    @Test
    void testDelete() {
        testRepository.delete(testEntity);
        testEntityManager.flush();
        List<Tests> allTests = testRepository.findAll();
        assertThat(allTests).isEmpty();
    }

    @Test
    void testFindById() {
        UUID id = testEntity.getId();
        Optional<Tests> found = testRepository.findById(id);
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Name");
    }
}
