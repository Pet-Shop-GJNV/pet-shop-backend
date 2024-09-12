package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> findById(Long id) {
        return gerenteRepository.findById(id);
    }

    public Gerente save(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public Optional<Gerente> update(Long id, Gerente gerenteDetails) {
        return gerenteRepository.findById(id).map(gerente -> {
            gerente.setSetorResponsavel(gerenteDetails.getSetorResponsavel());
            gerente.setEquipe(gerenteDetails.getEquipe());
            gerente.setMetaMensal(gerenteDetails.getMetaMensal());
            return gerenteRepository.save(gerente);
        });
    }

    public boolean delete(Long id) {
        return gerenteRepository.findById(id).map(gerente -> {
            gerenteRepository.delete(gerente);
            return true;
        }).orElse(false);
    }
}