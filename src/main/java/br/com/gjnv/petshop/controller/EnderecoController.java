package br.com.gjnv.petshop.controller;
import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public void adicionarEndereco(@RequestBody Endereco endereco) {
        enderecoService.adicionarEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public void removerEndereco(@PathVariable int id) {
        enderecoService.removerEndereco(id);
    }

    @PutMapping("/{id}")
    public void atualizarEndereco(@PathVariable int id, @RequestBody EnderecoDto endereco) {
        enderecoService.atualizarEndereco(id, endereco);
    }
}

