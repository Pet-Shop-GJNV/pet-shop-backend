package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.service.AtendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AtendenteFacade {

    private final AtendenteService atendenteService;

    @Autowired
    public AtendenteFacade(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    public List<Atendente> getAllAtendentes() {
        return atendenteService.findAll();
    }

    public Optional<Atendente> getAtendenteById(UUID id) {
        return atendenteService.findById(id);
    }

    public Atendente createAtendente(Atendente atendente) {
        return atendenteService.save(atendente);
    }

    public Optional<Atendente> updateAtendente(UUID id, Atendente atendenteDetails) {
        return atendenteService.update(id, atendenteDetails);
    }

    public boolean deleteAtendente(UUID id) {
        return atendenteService.delete(id);
    }

    public void atualizarStatusServico(UUID id) {
        atendenteService.atualizarStatusServico(id);
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        atendenteService.cadastrarCliente(cliente);
        return cliente;
    }

    public Cliente consultarCliente(Long id) {
        return atendenteService.consultarCliente(id);
    }

    public void excluirCliente(Long id) {
        atendenteService.excluirCliente(id);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        cliente.setId(id);
        atendenteService.atualizarCliente(cliente);
        return cliente;
    }

    public Pet cadastrarPet(Long clienteId, Pet pet) {
        atendenteService.cadastrarPet(clienteId, pet);
        return pet;
    }

    public Pet consultarPet(Long id) {
        return atendenteService.consultarPet(id);
    }

    public void excluirPet(Long id) {
        atendenteService.excluirPet(id);
    }

    public Pet atualizarPet(Long petId, Pet pet) {
        pet.setId(petId);
        atendenteService.atualizarPet(pet);
        return pet;
    }
}
