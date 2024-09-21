package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import br.com.gjnv.petshop.repository.GerenteRepository;
import br.com.gjnv.petshop.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private MotoristaRepository funcionarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> findById(UUID id) {
        return gerenteRepository.findById(id);
    }

    public Gerente save(Gerente gerente) {
        Endereco endereco = gerente.getEndereco();
        if (endereco != null) {
            if (endereco.getId() == null) {
                endereco = enderecoRepository.save(endereco);
            } else {
                Optional<Endereco> existingEndereco = enderecoRepository.findById(endereco.getId());
                if (existingEndereco.isEmpty()) {
                    endereco = enderecoRepository.save(endereco);
                } else {
                    endereco = existingEndereco.get();
                }
            }
            gerente.setEndereco(endereco);
        }

        List<Motorista> equipe = gerente.getEquipe();
        for (Motorista funcionario : equipe) {
            if (funcionario.getId() == null) {
                funcionarioRepository.save(funcionario);
            } else {
                Optional<Motorista> existingMotorista = funcionarioRepository.findById(funcionario.getId());
                if (existingMotorista.isEmpty()) {
                    funcionarioRepository.save(funcionario);
                } else {
                    funcionarioRepository.save(existingMotorista.get());
                }
            }
        }
        return gerenteRepository.save(gerente);
    }

    public Optional<Gerente> update(UUID id, Gerente gerenteDetails) {
        return gerenteRepository.findById(id).map(gerente -> {
            gerente.setSetorResponsavel(gerenteDetails.getSetorResponsavel());
            gerente.setMetaMensal(gerenteDetails.getMetaMensal());

            Endereco endereco = gerente.getEndereco();
            Endereco enderecoDetails = gerenteDetails.getEndereco();
            if (endereco != null && enderecoDetails != null) {
                endereco.setRua(enderecoDetails.getRua());
                endereco.setNumero(enderecoDetails.getNumero());
                endereco.setCidade(enderecoDetails.getCidade());
                endereco.setBairro(enderecoDetails.getBairro());
                endereco.setComplemento(enderecoDetails.getComplemento());
                enderecoRepository.save(endereco);
            }

            List<Motorista> equipe = gerente.getEquipe();
            List<Motorista> equipeDetails = gerenteDetails.getEquipe();
            for (int i = 0; i < equipe.size(); i++) {
                Motorista funcionario = equipe.get(i);
                Motorista funcionarioDetails = equipeDetails.get(i);
                funcionario.setNome(funcionarioDetails.getNome());
                funcionario.setCpf(funcionarioDetails.getCpf());
                funcionario.setEndereco(funcionarioDetails.getEndereco());
                funcionario.setTelefone(funcionarioDetails.getTelefone());
                funcionario.setDataContratacao(funcionarioDetails.getDataContratacao());
                funcionario.setHorarioTrabalho(funcionarioDetails.getHorarioTrabalho());
                funcionario.setCargo(funcionarioDetails.getCargo());
                funcionario.setSalario(funcionarioDetails.getSalario());
                funcionario.setCnh(funcionarioDetails.getCnh());
                funcionario.setVeiculo(funcionarioDetails.getVeiculo());
                funcionarioRepository.save(funcionario);
            }

            return gerenteRepository.save(gerente);
        });
    }

    public boolean delete(UUID id) {
        return gerenteRepository.findById(id).map(gerente -> {
            Endereco endereco = gerente.getEndereco();
            List<Motorista> equipe = gerente.getEquipe();
            for (Motorista funcionario : equipe) {
                funcionarioRepository.delete(funcionario);
            }
            gerenteRepository.delete(gerente);
            if (endereco != null) {
                enderecoRepository.delete(endereco);
            }
            return true;
        }).orElse(false);
    }
}