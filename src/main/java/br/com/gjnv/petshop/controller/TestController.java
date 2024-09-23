package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Tests;
import br.com.gjnv.petshop.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teste")
@Tag(name = "Teste", description = "Apenas uma classe de teste")
public class TestController {

    public TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("")
    @Operation(summary = "Retorna todos os testes")
    public ResponseEntity<List<Tests>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Retorna um teste especifico")
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
    @Operation(summary = "Cria um novo teste")
    public ResponseEntity<Tests> createTest(@RequestBody Tests test) {
        try {
            Tests t = testService.createTest(test);
            if (t != null) {
                return ResponseEntity.created(null).build();
            }
            return ResponseEntity.status(404).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um teste existente")
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
    @Operation(summary = "Deleta um teste existente")
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
