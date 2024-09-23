package br.com.gjnv.petshop.manager;

import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteManager implements IClienteManager {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente consultarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }

    @Override
    public void excluirCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }
}