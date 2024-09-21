package br.com.gjnv.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/{id}")
    public Endereco buscarEnderecoPorId(@PathVariable long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @PostMapping
    public void adicionarEndereco(@RequestBody Endereco endereco) {
        enderecoService.adicionarEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    public void removerEndereco(@PathVariable long id) {
        enderecoService.removerEndereco(id);
    }

    @PutMapping("/{id}")
    public void atualizarEndereco(@PathVariable long id, @RequestBody EnderecoDto endereco) {
        enderecoService.atualizarEndereco(id, endereco);
    }
}
