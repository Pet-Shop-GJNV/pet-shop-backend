package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.ClienteAgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.facade.ClienteFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Clientes", description = "Gerencia os clientes")
public class ClienteController {

    private final ClienteFacade clienteFacade;

    @Autowired
    public ClienteController(ClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    @GetMapping
    @Operation(summary = "Retorna uma lista de clientes")
    public ResponseEntity<List<ClienteDto>> listarClientes() {
        List<ClienteDto> clientes = clienteFacade.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna um cliente especificado pelo ID")
    public ResponseEntity<ClienteDto> consultarCliente(@PathVariable Long id) {
        ClienteDto cliente = clienteFacade.consultarCliente(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Operation(summary = "Cria um novo cliente")
    public ResponseEntity<ClienteDto> criarCliente(@RequestBody ClienteDto clienteDto) {
        ClienteDto novoCliente = clienteFacade.criarCliente(clienteDto);
        return ResponseEntity.ok(novoCliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um cliente existente")
    public ResponseEntity<ClienteDto> editarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteDto clienteAtualizado = clienteFacade.editarCliente(id, clienteDto);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um cliente existente")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteFacade.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/servicos/{servicoId}/solicitar")
    @Operation(summary = "Solicita um serviço para um cliente")
    public ResponseEntity<String> solicitarServico(@RequestBody ClienteAgendamentoDto clienteAgendamentoDto) {
        String mensagem = clienteFacade.solicitarServico(clienteAgendamentoDto);
        return ResponseEntity.ok(mensagem);
    }

    @DeleteMapping("/servicos/{servicoId}/cancelar")
    @Operation(summary = "Cancela um serviço para um cliente")
    public ResponseEntity<String> cancelarServico(@PathVariable Long servicoId) {
        String mensagem = clienteFacade.cancelarServico(servicoId);
        return ResponseEntity.ok(mensagem);
    }
}
