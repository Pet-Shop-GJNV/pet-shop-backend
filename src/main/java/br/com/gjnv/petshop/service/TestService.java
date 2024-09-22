package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Tests;
import br.com.gjnv.petshop.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TestService {

    public TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }


    public List<Tests> getAllTests() {
        return testRepository.findAll();
    }

    public Tests getTestById(@PathVariable UUID id) {
        try {
            Optional<Tests> t = testRepository.findById(id);
            return t.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    public Tests createTest(@RequestBody Tests test) {
        try {
            return testRepository.save(test);
        } catch (Exception e) {
            return null;
        }
    }

    public Tests updateTest(@PathVariable UUID id, @RequestBody Tests test) {
        try {
            Optional<Tests> t = testRepository.findById(id);
            if (t.isEmpty()) {
                return null;
            }
            t.get().setName(test.getName());
            t.get().setDescription(test.getDescription());
            testRepository.save(t.get());
            return t.get();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteTest(@PathVariable UUID id) {
        try {
            Optional<Tests> found = testRepository.findById(id);
            return found.isPresent();
        } catch (Exception e) {
            return false;
        }
    }
}
