package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.GerenteDto;
import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Gerente;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.repository.AtendenteRepository;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.repository.MotoristaRepository;
import br.com.gjnv.petshop.repository.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AtendenteRepository atendenteRepository;

    @Autowired
    private MotoristaRepository motoristaRepository;

    public List<Gerente> findAll() {
        return gerenteRepository.findAll();
    }

    public Optional<Gerente> findById(UUID id) {
        return gerenteRepository.findById(id);
    }

    public Gerente save(GerenteDto gerenteDto) {
        Gerente gerente = new Gerente();
        preencherCamposGerente(gerente, gerenteDto);
        return gerenteRepository.save(gerente);
    }

    public Optional<Gerente> update(UUID id, GerenteDto gerenteDto) {
        Optional<Gerente> gerenteOptional = gerenteRepository.findById(id);

        if (gerenteOptional.isPresent()) {
            Gerente gerente = gerenteOptional.get();
            preencherCamposGerente(gerente, gerenteDto);
            return Optional.of(gerenteRepository.save(gerente));
        }
        return Optional.empty();
    }

    public boolean delete(UUID id) {
        Optional<Gerente> gerenteOptional = gerenteRepository.findById(id);
        if (gerenteOptional.isPresent()) {
            gerenteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void preencherCamposGerente(Gerente gerente, GerenteDto gerenteDto) {
        gerente.setNome(gerenteDto.getNome());
        gerente.setCpf(gerenteDto.getCpf());
        gerente.setTelefone(gerenteDto.getTelefone());
        gerente.setDataContratacao(new Date());
        gerente.setSalario(gerenteDto.getSalario());
        gerente.setHorarioTrabalho(gerenteDto.getHorarioTrabalho());
        gerente.setCargo("Gerente");

        gerente.setSetorResponsavel(gerenteDto.getSetorResponsavel());

        List<Atendente> atendentes = atendenteRepository.findAllById(gerenteDto.getAtendentesIds());
        List<Motorista> motoristas = motoristaRepository.findAllById(gerenteDto.getMotoristasIds());

        gerente.setAtendentes(atendentes);
        gerente.setMotoristas(motoristas);
        gerente.setMetaMensal(gerenteDto.getMetaMensal());
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
}