package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.model.Endereco;
import br.com.gjnv.petshop.model.Motorista;
import br.com.gjnv.petshop.service.MotoristaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MotoristaControllerTest {

    @Mock
    private MotoristaService motoristaService;

    @InjectMocks
    private MotoristaController motoristaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllMotoristas() {
        Motorista motorista1 = new Motorista();
        Motorista motorista2 = new Motorista();
        List<Motorista> motoristas = Arrays.asList(motorista1, motorista2);

        when(motoristaService.findAll()).thenReturn(motoristas);

        List<Motorista> result = motoristaController.getAllMotoristas();

        assertEquals(2, result.size());
        verify(motoristaService, times(1)).findAll();
    }

    @Test
    void getMotoristaById() {
        UUID id = UUID.randomUUID();
        Motorista motorista = new Motorista();
        when(motoristaService.findById(id)).thenReturn(Optional.of(motorista));

        ResponseEntity<Motorista> response = motoristaController.getMotoristaById(id);

        assertEquals(ResponseEntity.ok(motorista), response);
        verify(motoristaService, times(1)).findById(id);
    }

    @Test
    void updateMotorista() {
        UUID id = UUID.randomUUID();
        Motorista motoristaDetails = new Motorista();
        Motorista updatedMotorista = new Motorista();
        when(motoristaService.update(id, motoristaDetails)).thenReturn(Optional.of(updatedMotorista));

        ResponseEntity<Motorista> response = motoristaController.updateMotorista(id, motoristaDetails);

        assertEquals(ResponseEntity.ok(updatedMotorista), response);
        verify(motoristaService, times(1)).update(id, motoristaDetails);
    }

    @Test
    void createMotorista() {
        Motorista motorista = new Motorista();
        when(motoristaService.save(motorista)).thenReturn(motorista);

        ResponseEntity<Motorista> response = motoristaController.createMotorista(motorista);

        assertEquals(ResponseEntity.ok(motorista), response);
        verify(motoristaService, times(1)).save(motorista);
    }

    @Test
    void deleteMotorista() {
        UUID id = UUID.randomUUID();
        when(motoristaService.delete(id)).thenReturn(true);

        ResponseEntity<Void> response = motoristaController.deleteMotorista(id);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(motoristaService, times(1)).delete(id);
    }

    @Test
    void realizarColeta() {
        UUID id = UUID.randomUUID();
        Endereco endereco = new Endereco();
        when(motoristaService.realizarColeta(id, endereco)).thenReturn(true);

        ResponseEntity<Void> response = motoristaController.realizarColeta(id, endereco);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(motoristaService, times(1)).realizarColeta(id, endereco);
    }

    @Test
    void realizarEntrega() {
        UUID id = UUID.randomUUID();
        Endereco endereco = new Endereco();
        when(motoristaService.realizarEntrega(id, endereco)).thenReturn(true);

        ResponseEntity<Void> response = motoristaController.realizarEntrega(id, endereco);

        assertEquals(ResponseEntity.ok().build(), response);
        verify(motoristaService, times(1)).realizarEntrega(id, endereco);
    }
}