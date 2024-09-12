//package br.com.gjnv.petshop.controller;
//
//import br.com.gjnv.petshop.model.Atendente;
//import br.com.gjnv.petshop.service.AtendenteService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/atendentes")
//public class AtendenteController {
//
//    @Autowired
//    private AtendenteService atendenteService;
//
//    @GetMapping
//    public List<Atendente> getAllAtendentes() {
//        return atendenteService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Atendente> getAtendenteById(@PathVariable Long id) {
//        Optional<Atendente> atendente = atendenteService.findById(id);
//        return atendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public Atendente createAtendente(@RequestBody Atendente atendente) {
//        return atendenteService.save(atendente);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Atendente> updateAtendente(@PathVariable Long id, @RequestBody Atendente atendenteDetails) {
//        Optional<Atendente> updatedAtendente = atendenteService.update(id, atendenteDetails);
//        return updatedAtendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAtendente(@PathVariable Long id) {
//        if (atendenteService.delete(id)) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}