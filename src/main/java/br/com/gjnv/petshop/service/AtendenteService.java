package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.AtendenteRepository;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import br.com.gjnv.petshop.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PetRepository petRepository;

    public List<Atendente> findAll() {
        return atendenteRepository.findAll();
    }

    public Optional<Atendente> findById(UUID id) {
        return atendenteRepository.findById(id);
    }

    public Atendente save(Atendente atendente) {
        Endereco endereco = atendente.getEndereco();
        if (endereco.getId() == null || endereco.getId() == 0) {
            endereco = enderecoRepository.save(endereco);
        } else {
            Optional<Endereco> existingEndereco = enderecoRepository.findById(endereco.getId());
            if (existingEndereco.isEmpty()) {
                endereco = enderecoRepository.save(endereco);
            } else {
                endereco = existingEndereco.get();
            }
        }
        atendente.setServicoRealizado(false);
        return atendenteRepository.save(atendente);
    }

    public Optional<Atendente> update(UUID id, Atendente atendenteDetails) {
        return atendenteRepository.findById(id).map(atendente -> {
            atendente.setNome(atendenteDetails.getNome());
            atendente.setCpf(atendenteDetails.getCpf());
            atendente.setTelefone(atendenteDetails.getTelefone());
            atendente.setDataContratacao(atendenteDetails.getDataContratacao());
            atendente.setHorarioTrabalho(atendenteDetails.getHorarioTrabalho());
            atendente.setCargo(atendenteDetails.getCargo());
            atendente.setSalario(atendenteDetails.getSalario());

            // Atualiza o endereço existente
            Endereco endereco = atendente.getEndereco();
            Endereco enderecoDetails = atendenteDetails.getEndereco();
            if (endereco != null && enderecoDetails != null) {
                endereco.setRua(enderecoDetails.getRua());
                endereco.setNumero(enderecoDetails.getNumero());
                endereco.setCidade(enderecoDetails.getCidade());
                endereco.setBairro(enderecoDetails.getBairro());
                endereco.setComplemento(enderecoDetails.getComplemento());
                enderecoRepository.save(endereco);
            }

            return atendenteRepository.save(atendente);
        });
    }

    public boolean delete(UUID id) {
        return atendenteRepository.findById(id).map(atendente -> {
            Endereco endereco = atendente.getEndereco();
            atendenteRepository.delete(atendente);
            if (endereco != null) {
                enderecoRepository.delete(endereco);
            }
            return true;
        }).orElse(false);
    }

    public void atualizarStatusServico(UUID id) {
        atendenteRepository.findById(id).ifPresent(atendente -> {
            atendente.setServicoRealizado(true);
            atendenteRepository.save(atendente);
        });
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente consultarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }

    public void excluirCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public void atualizarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public void cadastrarPet(Long clienteId, Pet pet) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            pet.setCliente(cliente);
            petRepository.save(pet);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
    }

    public Pet consultarPet(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet não encontrado."));
    }

    public void excluirPet(Long id) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Pet não encontrado.");
        }
    }

    public void atualizarPet(Pet pet) {
        if (petRepository.existsById(pet.getId())) {
            Pet Petexistente = petRepository.findById(pet.getId()).orElseThrow(() -> new IllegalArgumentException("Pet não encontrado."));
            Petexistente.setNome(pet.getNome());
            Petexistente.setRaca(pet.getRaca());
            Petexistente.setIdade(pet.getIdade());
            petRepository.save(Petexistente);
        } else {
            throw new IllegalArgumentException("Pet não encontrado.");
        }
    }
}