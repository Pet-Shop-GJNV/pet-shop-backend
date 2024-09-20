package br.com.gjnv.petshop.service;

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

//    @Autowired
//    private EnderecoService enderecoService;
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
        if (endereco.getId() == null) {
            endereco = enderecoRepository.save(endereco);
        }
        motorista.setEndereco(endereco);
        motorista.setRotaAtual(null); // Inicializa rotaAtual como null
        return motoristaRepository.save(motorista);
    }
//    public Motorista save(Motorista motorista) throws Exception {
//
//        Optional<Endereco> enderecoDoCaboclo = enderecoRepository.findById(motorista.getEndereco().getId());
//        Optional<Endereco> enderecoDeEntregaDoCaboclo = enderecoRepository.findById(motorista.getRotaAtual().getId());
//        Motorista paraSalvar;
//        if (enderecoDoCaboclo.isEmpty() || enderecoDeEntregaDoCaboclo.isEmpty()) {
//            throw new Exception("Endereço não encontrado");
//        }
//        paraSalvar = new Motorista(motorista.getNome(), motorista.getCpf(), enderecoDoCaboclo.get(), motorista.getTelefone(), motorista.getCnh(), motorista.getVeiculo(), enderecoDoCaboclo.get(), motorista.getDataContratacao(), motorista.getHorarioTrabalho(), motorista.getCargo(), motorista.getSalario());
//        return motoristaRepository.save(paraSalvar);
//
//    }

    public Optional<Motorista> update(UUID id, Motorista motoristaDetails) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.setCnh(motoristaDetails.getCnh());
            motorista.setVeiculo(motoristaDetails.getVeiculo());
            motorista.setRotaAtual(motoristaDetails.getRotaAtual());
            return motoristaRepository.save(motorista);
        });
    }

    public boolean delete(UUID id) {
        return motoristaRepository.findById(id).map(motorista -> {
            motoristaRepository.delete(motorista);
            return true;
        }).orElse(false);
    }

    public boolean realizarColeta(UUID id, Endereco endereco) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.realizarColeta(endereco);
            motoristaRepository.save(motorista);
            return true;
        }).orElse(false);
    }

    public boolean realizarEntrega(UUID id, Endereco endereco) {
        return motoristaRepository.findById(id).map(motorista -> {
            motorista.realizarEntrega(endereco);
            motoristaRepository.save(motorista);
            return true;
        }).orElse(false);
    }
}