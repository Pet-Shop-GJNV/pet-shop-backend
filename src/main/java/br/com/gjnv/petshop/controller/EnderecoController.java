package br.com.gjnv.petshop.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Endereços", description = "Gerencia os endereços")
public class EnderecoController {


    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de endereços")
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um endereço especificado pelo ID")
    public Endereco buscarEnderecoPorId(@PathVariable long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cria um novo endereço")
    public void adicionarEndereco(@RequestBody Endereco endereco) {
        enderecoService.adicionarEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um endereço existente")
    public void removerEndereco(@PathVariable long id) {
        enderecoService.removerEndereco(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço existente")
    public void atualizarEndereco(@PathVariable long id, @RequestBody EnderecoDto endereco) {
        enderecoService.atualizarEndereco(id, endereco);
    }
}
