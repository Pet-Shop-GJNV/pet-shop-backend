package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}/servico")
    public ClienteDto consultarServico(@PathVariable Long id) {
        return clienteService.consultarServico(id);
    }

    @PostMapping("/{id}/solicitar-servico")
    public String solicitarServico(@PathVariable Long id, @RequestParam String tipoServico) {
        return clienteService.solicitarServico(id, tipoServico);
    }

    @DeleteMapping("/{id}/cancelar-servico")
    public String cancelarServico(@PathVariable Long id) {
        return clienteService.cancelarServico(id);
    }
}
