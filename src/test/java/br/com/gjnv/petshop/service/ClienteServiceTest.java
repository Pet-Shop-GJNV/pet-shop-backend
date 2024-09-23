package br.com.gjnv.petshop.service;

import br.com.gjnv.petshop.dto.AgendamentoDto;
import br.com.gjnv.petshop.dto.ClienteDto;
import br.com.gjnv.petshop.model.Agendamento;
import br.com.gjnv.petshop.model.Cliente;
import br.com.gjnv.petshop.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AgendamentoService agendamentoService;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void consultarCliente() {
        Long clienteId = 1L;
        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        ClienteDto result = clienteService.consultarCliente(clienteId);

        assertNotNull(result);
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void criarCliente() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNome("Nome");
        clienteDto.setTelefone("123456789");
        clienteDto.setCpf("12345678900");

        Cliente cliente = new Cliente();
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDto result = clienteService.criarCliente(clienteDto);

        assertNotNull(result);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void editarCliente() {
        Long clienteId = 1L;
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNome("Nome");
        clienteDto.setTelefone("123456789");
        clienteDto.setCpf("12345678900");

        Cliente cliente = new Cliente();
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        ClienteDto result = clienteService.editarCliente(clienteId, clienteDto);

        assertNotNull(result);
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void deletarCliente() {
        Long clienteId = 1L;
        when(clienteRepository.existsById(clienteId)).thenReturn(true);

        clienteService.deletarCliente(clienteId);

        verify(clienteRepository, times(1)).deleteById(clienteId);
    }

    @Test
    void solicitarServico() {
        int clienteId = 1;
        int servicoId = 1;
        AgendamentoDto agendamentoDto = new AgendamentoDto();

        Agendamento agendamento = new Agendamento();
        when(agendamentoService.createAgendamento(any(AgendamentoDto.class))).thenReturn(agendamento);

        String result = clienteService.solicitarServico(clienteId, servicoId, agendamentoDto);

        assertNotNull(result);
        verify(agendamentoService, times(1)).createAgendamento(any(AgendamentoDto.class));
    }

    @Test
    void cancelarServico() {
        Long servicoId = 1L;

        String result = clienteService.cancelarServico(servicoId);

        assertNotNull(result);
        assertEquals("Serviço cancelado com sucesso: " + servicoId, result);
    }

    @Test
    void listarClientes() {
        List<Cliente> clientes = Arrays.asList(mock(Cliente.class), mock(Cliente.class));
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteDto> result = clienteService.listarClientes();

        assertEquals(2, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Long id = 1L;
        Cliente cliente = mock(Cliente.class);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(clienteRepository, times(1)).findById(id);
    }
}