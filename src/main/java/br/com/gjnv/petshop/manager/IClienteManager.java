package br.com.gjnv.petshop.manager;

import br.com.gjnv.petshop.model.Cliente;

public interface IClienteManager {

    void cadastrarCliente(Cliente cliente);

    Cliente consultarCliente(Long id);

    void excluirCliente(Long id);

    void atualizarCliente(Cliente cliente);
}
