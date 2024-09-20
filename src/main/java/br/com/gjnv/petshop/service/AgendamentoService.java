package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public Agendamento createAgendamento(AgendamentoDto agendamentoDTO) {
        Agendamento agendamento = new Agendamento();
        agendamento.setTipoServico(new Servico()); // Ajustar a lógica para obter o Servico
        agendamento.setClientId(agendamentoDTO.getClientId());
        agendamento.setFuncionarioId(agendamentoDTO.getFuncionarioId());
        agendamento.setVagaDisponivel(agendamentoDTO.isVagaDisponivel());
        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public Agendamento updateAgendamento(int id, AgendamentoDto agendamentoDTO) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamento.setTipoServico(new Servico()); // Ajustar a lógica para obter o Servico
        agendamento.setClientId(agendamentoDTO.getClientId());
        agendamento.setFuncionarioId(agendamentoDTO.getFuncionarioId());
        agendamento.setVagaDisponivel(agendamentoDTO.isVagaDisponivel());

        return agendamentoRepository.save(agendamento);
    }

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
}
