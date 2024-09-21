package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ServicoService servicoService;

    @Transactional
    public Agendamento createAgendamento(AgendamentoDto agendamentoDto) {
        Optional<Servico> servicoOpt = servicoService.consultarServico(agendamentoDto.getServicoId());
        if (servicoOpt.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setClientId(agendamentoDto.getClientId());
        agendamento.setFuncionarioId(agendamentoDto.getFuncionarioId());
        agendamento.setVagaDisponivel(agendamentoDto.isVagaDisponivel());
        agendamento.setTipoServico(servicoOpt.get());

        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public Agendamento updateAgendamento(int id, AgendamentoDto agendamentoDto) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        Optional<Servico> servicoOpt = servicoService.consultarServico(agendamentoDto.getServicoId());
        if (servicoOpt.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado");
        }

        agendamentoExistente.setClientId(agendamentoDto.getClientId());
        agendamentoExistente.setFuncionarioId(agendamentoDto.getFuncionarioId());
        agendamentoExistente.setVagaDisponivel(agendamentoDto.isVagaDisponivel());
        agendamentoExistente.setTipoServico(servicoOpt.get());

        return agendamentoRepository.save(agendamentoExistente);
    }

    @Transactional
    public void deleteAgendamento(int id) {
        if (!agendamentoRepository.existsById(id)) {
            throw new RuntimeException("Agendamento não encontrado");
        }
        agendamentoRepository.deleteById(id);
    }

    public Agendamento getAgendamentoById(int id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }
}
