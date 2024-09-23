package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.exception.Servico.ServicoInvalidoException;
import br.com.gjnv.petshop.exception.Servico.ServicoNaoEncontradoException;
import br.com.gjnv.petshop.manager.IServico;
import br.com.gjnv.petshop.model.Servico;
import br.com.gjnv.petshop.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService implements IServico {

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    @Override
    public Optional<Servico> consultarServico(int id) {
        return Optional.ofNullable(servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNaoEncontradoException(id)));
    }

    @Override
    public void cancelarServico(int id) {
        if (!servicoRepository.existsById(id)) {
            throw new ServicoNaoEncontradoException(id);
        }
        servicoRepository.deleteById(id);
    }

    @Override
    public void adicionarServico(Servico servico) {
        if (servico.getTipoServico() == null || servico.getTipoServico().isEmpty()) {
            throw new ServicoInvalidoException();
        }
        servicoRepository.save(servico);
    }
}