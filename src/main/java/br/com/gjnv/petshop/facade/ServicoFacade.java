package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServicoFacade {

    private final ServicoService servicoService;

    @Autowired
    public ServicoFacade(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    public List<Servico> listarTodosServicos() {
        return servicoService.listarServicos();
    }

    public Optional<Servico> consultarServicoPorId(int id) {
        return servicoService.consultarServico(id);
    }

    public void adicionarServico(Servico servico) {
        servicoService.adicionarServico(servico);
    }

    public void cancelarServico(int id) {
        servicoService.cancelarServico(id);
    }
}
