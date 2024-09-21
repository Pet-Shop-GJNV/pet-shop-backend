package br.com.gjnv.petshop.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import br.com.gjnv.petshop.repository.MotoristaRepository;

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
        motorista.setRotaAtual(null); // Deixa rotaAtual como null
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
            motorista.setNome(motoristaDetails.getNome());
            motorista.setCpf(motoristaDetails.getCpf());

            // Atualiza o endereço existente
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
            // Não altera rotaAtual
            return motoristaRepository.save(motorista);
        });
    }

    public boolean delete(UUID id) {
        return motoristaRepository.findById(id).map(motorista -> {
            Endereco endereco = motorista.getEndereco();
            motoristaRepository.delete(motorista);
            enderecoRepository.delete(endereco);
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