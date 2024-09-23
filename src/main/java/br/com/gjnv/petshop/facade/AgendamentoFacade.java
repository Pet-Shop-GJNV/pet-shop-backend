package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgendamentoFacade {

    private final AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoFacade(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoService.listarAgendamentos();
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        return agendamentoService.getAgendamentoById(id);
    }

    public Agendamento criarAgendamento(AgendamentoDto agendamentoDto) {
        // Aqui podemos adicionar validações ou lógica extra antes de criar o agendamento
        return agendamentoService.createAgendamento(agendamentoDto);
    }

    public Agendamento atualizarAgendamento(int id, AgendamentoDto agendamentoDto) {
        // Validações ou lógica extra antes de atualizar o agendamento
        return agendamentoService.updateAgendamento(id, agendamentoDto);
    }

    public void deletarAgendamento(int id) {
        // Lógica extra para validações antes da exclusão, se necessário
        agendamentoService.deleteAgendamento(id);
    }
}
