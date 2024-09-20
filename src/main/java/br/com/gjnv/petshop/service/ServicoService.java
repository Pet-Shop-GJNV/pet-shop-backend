package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.ServicoDto;
import br.com.gjnv.petshop.manager.IServico;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicoService implements IServico {

    @Autowired
    private ServicoRepository servicoRepository;


    @Override
    public List<Servico> consultarServico(int id) {
        return servicoRepository.findAll();
    }

    @Override
    public void cancelarServico(int id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent()) {
            servicoRepository.deleteById(id);
        }
    }

    @Override
    public void agendarServico(int id, boolean vagaDisponivel) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (servico.isPresent() && vagaDisponivel) {
            System.out.println("Serviço agendado: " + servico.get().getTipoServico());
        } else {
            System.out.println("Não foi possível agendar o serviço.");
        }
    }
}
