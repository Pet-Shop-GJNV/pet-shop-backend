package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Tests;
import br.com.gjnv.petshop.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teste")
public class TestController {

    public TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("")
    public ResponseEntity<List<Tests>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tests> getTestById(@PathVariable UUID id) {
        try {
            Tests test = testService.getTestById(id);
            if (test == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(test);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Tests> createTest(@RequestBody Tests test) {
        try {
            Tests t = testService.createTest(test);
            if (t != null) {
                return ResponseEntity.created(null).build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tests> updateTest(@PathVariable UUID id, @RequestBody Tests test) {
        try {
            Tests t = testService.updateTest(id, test);
            if (t == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable UUID id) {
        try {
            if (testService.deleteTest(id)) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
