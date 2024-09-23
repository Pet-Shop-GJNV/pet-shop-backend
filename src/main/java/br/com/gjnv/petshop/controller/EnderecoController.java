package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.facade.EnderecoFacade;
import br.com.gjnv.petshop.model.Endereco;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Endereços", description = "Gerencia os endereços")
public class EnderecoController {

    private final EnderecoFacade enderecoFacade;

    @Autowired
    public EnderecoController(EnderecoFacade enderecoFacade) {
        this.enderecoFacade = enderecoFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de endereços")
    public List<Endereco> listarEnderecos() {
        return enderecoFacade.listarEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um endereço especificado pelo ID")
    public Endereco buscarEnderecoPorId(@PathVariable long id) {
        return enderecoFacade.buscarEnderecoPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cria um novo endereço")
    public void adicionarEndereco(@RequestBody Endereco endereco) {
        enderecoFacade.adicionarEndereco(endereco);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um endereço existente")
    public void removerEndereco(@PathVariable long id) {
        enderecoFacade.removerEndereco(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um endereço existente")
    public void atualizarEndereco(@PathVariable long id, @RequestBody EnderecoDto enderecoDto) {
        enderecoFacade.atualizarEndereco(id, enderecoDto);
    }
}
