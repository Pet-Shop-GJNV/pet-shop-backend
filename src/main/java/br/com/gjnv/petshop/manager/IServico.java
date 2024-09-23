package br.com.gjnv.petshop.manager;

import br.com.gjnv.petshop.model.Servico;

import java.util.List;
import java.util.Optional;

public interface IServico {

    List<Servico> listarServicos();

    Optional<Servico> consultarServico(int id);

    void cancelarServico(int id);

    void adicionarServico(Servico servico);
}
