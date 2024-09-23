package br.com.gjnv.petshop.facade;

import br.com.gjnv.petshop.dto.PetDto;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PetFacade {

    private final PetService petService;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PetFacade(PetService petService, ClienteRepository clienteRepository) {
        this.petService = petService;
        this.clienteRepository = clienteRepository;
    }

    public List<PetDto> listarTodosPets() {
        return petService.findAll().stream().map(pet -> {
            PetDto dto = new PetDto();
            dto.setId(pet.getId());
            dto.setNome(pet.getNome());
            dto.setIdade(pet.getIdade());
            dto.setRaca(pet.getRaca());
            if (pet.getCliente() != null) {
                dto.setClienteId(pet.getCliente().getId());
            }
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<PetDto> buscarPetPorId(Long id) {
        return petService.findById(id).map(pet -> {
            PetDto dto = new PetDto();
            dto.setId(pet.getId());
            dto.setNome(pet.getNome());
            dto.setIdade(pet.getIdade());
            dto.setRaca(pet.getRaca());
            if (pet.getCliente() != null) {
                dto.setClienteId(pet.getCliente().getId());
            }
            return dto;
        });
    }

    public Optional<Pet> criarPet(PetDto petDto) throws IllegalArgumentException {
        Optional<Cliente> cliente = clienteRepository.findById(petDto.getClienteId());
        if (cliente.isPresent()) {
            Pet pet = new Pet();
            pet.setNome(petDto.getNome());
            pet.setIdade(petDto.getIdade());
            pet.setRaca(petDto.getRaca());
            pet.setCliente(cliente.get());
            return Optional.of(petService.save(pet));
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public Optional<Pet> atualizarPet(Long id, PetDto petDto) {
        Optional<Cliente> cliente = clienteRepository.findById(petDto.getClienteId());
        if (cliente.isPresent()) {
            Pet petAtualizado = new Pet(petDto.getNome(), petDto.getIdade(), petDto.getRaca(), cliente.get());
            return Optional.ofNullable(petService.updateById(id, petAtualizado));
        }
        return Optional.empty();
    }

    public boolean deletarPet(Long id) {
        return petService.delete(id);
    }
}
