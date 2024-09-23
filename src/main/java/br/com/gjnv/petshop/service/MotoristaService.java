package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.exception.Motorista.MotoristaNaoEncontradoException;
import br.com.gjnv.petshop.exception.Motorista.EnderecoInvalidoException;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import br.com.gjnv.petshop.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Motorista> findAll() {
        return motoristaRepository.findAll();
    }

    public Optional<Motorista> findById(UUID id) {
        return motoristaRepository.findById(id);
    }

    public Motorista save(Motorista motorista) {
        Endereco endereco = motorista.getEndereco();
        if (endereco.getId() == null || endereco.getId() == 0) {
            endereco = enderecoRepository.save(endereco);
        } else {
            Optional<Endereco> existingEndereco = enderecoRepository.findById(endereco.getId());
            if (existingEndereco.isEmpty()) {
                endereco = enderecoRepository.save(endereco);
            } else {
                endereco = existingEndereco.get();
            }
        }
        motorista.setEndereco(endereco);
        motorista.setRotaAtual(null);
        return motoristaRepository.save(motorista);
    }

    public Optional<Motorista> update(UUID id, Motorista motoristaDetails) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.setNome(motoristaDetails.getNome());
            motorista.setCpf(motoristaDetails.getCpf());

            Endereco endereco = motorista.getEndereco();
            Endereco enderecoDetails = motoristaDetails.getEndereco();
            endereco.setRua(enderecoDetails.getRua());
            endereco.setNumero(enderecoDetails.getNumero());
            endereco.setCidade(enderecoDetails.getCidade());
            endereco.setBairro(enderecoDetails.getBairro());
            endereco.setComplemento(enderecoDetails.getComplemento());
            enderecoRepository.save(endereco);

            motorista.setTelefone(motoristaDetails.getTelefone());
            motorista.setDataContratacao(motoristaDetails.getDataContratacao());
            motorista.setHorarioTrabalho(motoristaDetails.getHorarioTrabalho());
            motorista.setCargo(motoristaDetails.getCargo());
            motorista.setSalario(motoristaDetails.getSalario());
            motorista.setCnh(motoristaDetails.getCnh());
            motorista.setVeiculo(motoristaDetails.getVeiculo());
            return motoristaRepository.save(motorista);
        });
    }

    public boolean delete(UUID id) {
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new MotoristaNaoEncontradoException(id));
        Endereco endereco = motorista.getEndereco();
        motoristaRepository.delete(motorista);
        enderecoRepository.delete(endereco);
        return true;
    }

    public boolean realizarColeta(UUID id, Endereco endereco) {
        if (endereco == null) {
            throw new EnderecoInvalidoException();
        }
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new MotoristaNaoEncontradoException(id));
        motorista.realizarColeta(endereco);
        motoristaRepository.save(motorista);
        return true;
    }

    public boolean realizarEntrega(UUID id, Endereco endereco) {
        if (endereco == null) {
            throw new EnderecoInvalidoException();
        }
        Motorista motorista = motoristaRepository.findById(id)
                .orElseThrow(() -> new MotoristaNaoEncontradoException(id));
        motorista.realizarEntrega(endereco);
        motoristaRepository.save(motorista);
        return true;
    }
}