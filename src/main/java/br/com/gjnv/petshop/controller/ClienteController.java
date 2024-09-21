package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteAgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<ClienteDto> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> consultarCliente(@PathVariable Long id) {
        ClienteDto cliente = clienteService.consultarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto novoCliente = clienteService.criarCliente(clienteDto);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> editarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteAtualizado = clienteService.editarCliente(id, clienteDto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/servicos/{servicoId}/solicitar")
    public ResponseEntity<String> solicitarServico(@RequestBody ClienteAgendamentoDto clienteAgendamentoDto) {
        AgendamentoDto agendamentoDto = new AgendamentoDto();
        agendamentoDto.setClientId(clienteAgendamentoDto.getClientId());
        agendamentoDto.setServicoId(clienteAgendamentoDto.getServicoId());
        agendamentoDto.setFuncionarioId(clienteAgendamentoDto.getFuncionarioId());
        agendamentoDto.setVagaDisponivel(false);

        String mensagem = clienteService.solicitarServico(agendamentoDto.getClientId(), agendamentoDto.getServicoId(), agendamentoDto);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/servicos/{servicoId}/cancelar")
    public ResponseEntity<String> cancelarServico(@PathVariable Long servicoId) {
        String mensagem = clienteService.cancelarServico(servicoId);
        return ResponseEntity.ok(mensagem);
    }
}
