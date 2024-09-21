package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.model.Motorista;
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

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> findById(UUID id) {
        return gerenteRepository.findById(id);
    }

    public Gerente save(Gerente gerente) {
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
            List<Motorista> equipe = gerente.getEquipe();
            for (Motorista funcionario : equipe) {
                funcionarioRepository.delete(funcionario);
            }
            gerenteRepository.delete(gerente);
            return true;
        }).orElse(false);
    }
}