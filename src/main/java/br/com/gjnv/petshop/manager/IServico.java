package br.com.gjnv.petshop.manager;

import br.com.gjnv.petshop.dto.ServicoDto;
import br.com.gjnv.petshop.model.Servico;

import java.util.List;

public interface IServico {

    List<Servico> consultarServico(int id);
    void cancelarServico(int id);
    void agendarServico(int id, boolean vagaDisponivel);
}
