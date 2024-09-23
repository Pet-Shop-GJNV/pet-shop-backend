package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MotoristaFacade {

    private final MotoristaService motoristaService;

    @Autowired
    public MotoristaFacade(MotoristaService motoristaService) {
        this.motoristaService = motoristaService;
    }

    public List<Motorista> listarTodosMotoristas() {
        return motoristaService.findAll();
    }

    public Optional<Motorista> buscarMotoristaPorId(UUID id) {
        return motoristaService.findById(id);
    }

    public Motorista criarMotorista(Motorista motorista) throws Exception {
        return motoristaService.save(motorista);
    }

    public Optional<Motorista> atualizarMotorista(UUID id, Motorista motoristaDetails) {
        return motoristaService.update(id, motoristaDetails);
    }

    public boolean excluirMotorista(UUID id) {
        return motoristaService.delete(id);
    }

    public boolean realizarColeta(UUID id, Endereco endereco) {
        return motoristaService.realizarColeta(id, endereco);
    }

    public boolean realizarEntrega(UUID id, Endereco endereco) {
        return motoristaService.realizarEntrega(id, endereco);
    }
}
