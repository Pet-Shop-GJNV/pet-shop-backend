package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteAgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteFacade {

    private final ClienteService clienteService;

    @Autowired
    public ClienteFacade(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<ClienteDto> listarClientes() {
        return clienteService.listarClientes();
    }

    public ClienteDto consultarCliente(Long id) {
        return clienteService.consultarCliente(id);
    }

    public ClienteDto criarCliente(ClienteDto clienteDto) {
        return clienteService.criarCliente(clienteDto);
    }

    public ClienteDto editarCliente(Long id, ClienteDto clienteDto) {
        return clienteService.editarCliente(id, clienteDto);
    }

    public void deletarCliente(Long id) {
        clienteService.deletarCliente(id);
    }

    public String solicitarServico(ClienteAgendamentoDto clienteAgendamentoDto) {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setClientId(clienteAgendamentoDto.getClientId());
        agendamentoDto.setServicoId(clienteAgendamentoDto.getServicoId());
        agendamentoDto.setFuncionarioId(clienteAgendamentoDto.getFuncionarioId());
        agendamentoDto.setVagaDisponivel(false);

        return clienteService.solicitarServico(
                agendamentoDto.getClientId(),
                agendamentoDto.getServicoId(),
                agendamentoDto
        );
    }

    public String cancelarServico(Long servicoId) {
        return clienteService.cancelarServico(servicoId);
    }
}
