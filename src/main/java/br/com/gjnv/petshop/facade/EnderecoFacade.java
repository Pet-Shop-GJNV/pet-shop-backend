package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoFacade {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoFacade(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    public List<Endereco> listarEnderecos() {
        return enderecoService.listarEnderecos();
    }

    public Endereco buscarEnderecoPorId(long id) {
        return enderecoService.buscarEnderecoPorId(id);
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecoService.adicionarEndereco(endereco);
    }

    public void atualizarEndereco(long id, EnderecoDto enderecoDto) {
        enderecoService.atualizarEndereco(id, enderecoDto);
    }

    public void removerEndereco(long id) {
        enderecoService.removerEndereco(id);
    }
}
