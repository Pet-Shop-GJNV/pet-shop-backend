package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping
    public List<Motorista> getAllMotoristas() {
        return motoristaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorista> getMotoristaById(@PathVariable UUID id) {
        Optional<Motorista> motorista = motoristaService.findById(id);
        return motorista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorista> updateMotorista(@PathVariable UUID id, @RequestBody Motorista motoristaDetails) {
        Optional<Motorista> updatedMotorista = motoristaService.update(id, motoristaDetails);
        return updatedMotorista.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //    @PostMapping
//    public ResponseEntity<Motorista> createMotorista(@RequestBody Motorista motorista) {
//        try {
//            Motorista savedMotorista = motoristaService.save(motorista);
//            return ResponseEntity.ok(savedMotorista);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }
    @PostMapping
    public Motorista createMotorista(@RequestBody Motorista motorista) {
        return motoristaService.save(motorista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorista(@PathVariable UUID id) {
        if (motoristaService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/coleta")
    public ResponseEntity<Void> realizarColeta(@PathVariable UUID id, @RequestBody Endereco endereco) {
        if (motoristaService.realizarColeta(id, endereco)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/entrega")
    public ResponseEntity<Void> realizarEntrega(@PathVariable UUID id, @RequestBody Endereco endereco) {
        if (motoristaService.realizarEntrega(id, endereco)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}