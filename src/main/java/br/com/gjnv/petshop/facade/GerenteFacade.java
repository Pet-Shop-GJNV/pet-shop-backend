package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.service.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class GerenteFacade {

    private final GerenteService gerenteService;

    @Autowired
    public GerenteFacade(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    public List<Gerente> getAllGerentes() {
        return gerenteService.findAll();
    }

    public Optional<Gerente> getGerenteById(UUID id) {
        return gerenteService.findById(id);
    }

    public Gerente createGerente(GerenteDto gerenteDto) {
        return gerenteService.save(gerenteDto);
    }

    public Optional<Gerente> updateGerente(UUID id, GerenteDto gerenteDto) {
        return gerenteService.update(id, gerenteDto);
    }

    public boolean deleteGerente(UUID id) {
        return gerenteService.delete(id);
    }
}
