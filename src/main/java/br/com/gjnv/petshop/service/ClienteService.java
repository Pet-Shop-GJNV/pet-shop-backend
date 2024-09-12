package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDto consultarServico(Long servicoId) {

        Cliente cliente = clienteRepository.findById(servicoId).orElse(null);
        assert cliente != null;
        return convertToDTO(cliente);
    }

    public String solicitarServico(Long clienteId, String tipoServico) {
        return "Serviço solicitado para o cliente " + clienteId;
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
}
