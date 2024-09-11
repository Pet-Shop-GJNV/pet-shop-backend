package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.controller.exception.ResourceNotFoundException;
import br.com.gjnv.petshop.dto.EnderecoDto;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecoRepository.save(endereco);
    }

    public void removerEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco atualizarEndereco(Long id, EnderecoDto enderecoDto) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            endereco.setRua(enderecoDto.getRua());
            endereco.setNumero(enderecoDto.getNumero());
            endereco.setBairro(enderecoDto.getBairro());
            endereco.setCidade(enderecoDto.getCidade());
            endereco.setComplemento(enderecoDto.getComplemento());

            return enderecoRepository.save(endereco);
        } else {
            throw new ResourceNotFoundException("Endereço com ID " + id + " não encontrado.");
        }
    }
}
