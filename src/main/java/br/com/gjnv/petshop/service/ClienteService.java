package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AgendamentoService agendamentoService;

    public ClienteDto consultarCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return convertToDTO(cliente);
    }

    @Transactional
    public ClienteDto criarCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setTelefone(clienteDto.getTelefone());
        cliente.setCpf(clienteDto.getCpf());
        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDTO(savedCliente);
    }

    @Transactional
    public ClienteDto editarCliente(Long clienteId, ClienteDto clienteDto) {
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteExistente.setNome(clienteDto.getNome());
        clienteExistente.setTelefone(clienteDto.getTelefone());
        clienteExistente.setCpf(clienteDto.getCpf());

        Cliente updatedCliente = clienteRepository.save(clienteExistente);
        return convertToDTO(updatedCliente);
    }

    @Transactional
    public void deletarCliente(Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public String solicitarServico(int clienteId, int servicoId, AgendamentoDto agendamentoDto) {
        agendamentoDto.setClientId(clienteId);
        agendamentoDto.setVagaDisponivel(false);
        Agendamento agendamento = agendamentoService.createAgendamento(agendamentoDto);
        return "Agendamento criado com sucesso para o serviço com ID " + servicoId + " para o cliente " + clienteId;
    }

    public String cancelarServico(Long servicoId) {
        return "Serviço cancelado com sucesso: " + servicoId;
    }

    private ClienteDto convertToDTO(Cliente cliente) {
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setTelefone(cliente.getTelefone());
        dto.setCpf(cliente.getCpf());
        return dto;
    }

    public List<ClienteDto> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::convertToDTO).toList();
    }
}
