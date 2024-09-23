package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.model.Atendente;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Pet;
import br.com.gjnv.petshop.repository.AtendenteRepository;
import br.com.gjnv.petshop.repository.ClienteRepository;
import br.com.gjnv.petshop.repository.EnderecoRepository;
import br.com.gjnv.petshop.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendenteServiceTest {

    @Mock
    private AtendenteRepository atendenteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private AtendenteService atendenteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        atendenteService.findAll();
        verify(atendenteRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        atendenteService.findById(id);
        verify(atendenteRepository, times(1)).findById(id);
    }

    @Test
    void save() {
        Atendente atendente = new Atendente();
        Endereco endereco = new Endereco();
        atendente.setEndereco(endereco);

        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(atendenteRepository.save(any(Atendente.class))).thenReturn(atendente);

        Atendente savedAtendente = atendenteService.save(atendente);

        assertNotNull(savedAtendente);
        assertFalse(savedAtendente.isServicoRealizado());
        verify(enderecoRepository, times(1)).save(endereco);
        verify(atendenteRepository, times(1)).save(atendente);
    }

    @Test
    void update() {
        UUID id = UUID.randomUUID();
        Atendente atendente = new Atendente();
        atendente.setId(id);
        Atendente atendenteDetails = new Atendente();
        atendenteDetails.setNome("Updated Name");

        when(atendenteRepository.findById(id)).thenReturn(Optional.of(atendente));
        when(atendenteRepository.save(any(Atendente.class))).thenReturn(atendente);

        Optional<Atendente> updatedAtendente = atendenteService.update(id, atendenteDetails);

        assertTrue(updatedAtendente.isPresent());
        assertEquals("Updated Name", updatedAtendente.get().getNome());
        verify(atendenteRepository, times(1)).findById(id);
        verify(atendenteRepository, times(1)).save(atendente);
    }

    @Test
    void delete() {
        UUID id = UUID.randomUUID();
        Atendente atendente = new Atendente();
        Endereco endereco = new Endereco();
        atendente.setEndereco(endereco);

        when(atendenteRepository.findById(id)).thenReturn(Optional.of(atendente));

        boolean isDeleted = atendenteService.delete(id);

        assertTrue(isDeleted);
        verify(atendenteRepository, times(1)).delete(atendente);
        verify(enderecoRepository, times(1)).delete(endereco);
    }

    @Test
    void atualizarStatusServico() {
        UUID id = UUID.randomUUID();
        Atendente atendente = new Atendente();

        when(atendenteRepository.findById(id)).thenReturn(Optional.of(atendente));
        when(atendenteRepository.save(any(Atendente.class))).thenReturn(atendente);

        atendenteService.atualizarStatusServico(id);

        assertTrue(atendente.isServicoRealizado());
        verify(atendenteRepository, times(1)).findById(id);
        verify(atendenteRepository, times(1)).save(atendente);
    }

    @Test
    void cadastrarCliente() {
        Cliente cliente = new Cliente();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        atendenteService.cadastrarCliente(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void consultarCliente() {
        Long id = 1L;
        Cliente cliente = new Cliente();

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Cliente foundCliente = atendenteService.consultarCliente(id);

        assertNotNull(foundCliente);
        verify(clienteRepository, times(1)).findById(id);
    }

    @Test
    void excluirCliente() {
        Long id = 1L;

        when(clienteRepository.existsById(id)).thenReturn(true);

        atendenteService.excluirCliente(id);

        verify(clienteRepository, times(1)).deleteById(id);
    }

    @Test
    void atualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        when(clienteRepository.existsById(cliente.getId())).thenReturn(true);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        atendenteService.atualizarCliente(cliente);

        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void cadastrarPet() {
        Long clienteId = 1L;
        Pet pet = new Pet();
        Cliente cliente = new Cliente();

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        atendenteService.cadastrarPet(clienteId, pet);

        assertEquals(cliente, pet.getCliente());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(petRepository, times(1)).save(pet);
    }

    @Test
    void consultarPet() {
        Long id = 1L;
        Pet pet = new Pet();

        when(petRepository.findById(id)).thenReturn(Optional.of(pet));

        Pet foundPet = atendenteService.consultarPet(id);

        assertNotNull(foundPet);
        verify(petRepository, times(1)).findById(id);
    }

    @Test
    void excluirPet() {
        Long id = 1L;

        when(petRepository.existsById(id)).thenReturn(true);

        atendenteService.excluirPet(id);

        verify(petRepository, times(1)).deleteById(id);
    }
}