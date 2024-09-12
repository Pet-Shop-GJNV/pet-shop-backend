package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public List<Motorista> findAll() {
        return motoristaRepository.findAll();
    }

    public Optional<Motorista> findById(Long id) {
        return motoristaRepository.findById(id);
    }

    public Motorista save(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public Optional<Motorista> update(Long id, Motorista motoristaDetails) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.setCnh(motoristaDetails.getCnh());
            motorista.setVeiculo(motoristaDetails.getVeiculo());
            motorista.setRotaAtual(motoristaDetails.getRotaAtual());
            return motoristaRepository.save(motorista);
        });
    }

    public boolean delete(Long id) {
        return motoristaRepository.findById(id).map(motorista -> {
            motoristaRepository.delete(motorista);
            return true;
        }).orElse(false);
    }

    public boolean realizarColeta(Long id, Endereco endereco) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.realizarColeta(endereco);
            motoristaRepository.save(motorista);
            return true;
        }).orElse(false);
    }

    public boolean realizarEntrega(Long id, Endereco endereco) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.realizarEntrega(endereco);
            motoristaRepository.save(motorista);
            return true;
        }).orElse(false);
    }
}