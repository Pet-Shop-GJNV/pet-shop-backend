package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.util.Messages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<Messages> healthTest() {
        return ResponseEntity.ok().body(new Messages(200, "API Ok!"));
    }
}
