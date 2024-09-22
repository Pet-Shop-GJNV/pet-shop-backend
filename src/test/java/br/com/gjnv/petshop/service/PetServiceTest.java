//package br.com.gjnv.petshop.service;
//
//import br.com.gjnv.petshop.model.Cliente;
//import br.com.gjnv.petshop.model.Pet;
//import br.com.gjnv.petshop.repository.PetRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class PetServiceTest {
//
//    @Mock
//    private PetRepository petRepository;
//
//    @Mock
//    private Cliente clienteRepository;
//
//    @InjectMocks
//    private PetService petService;
//
//    private Pet pet;
//    private Cliente cliente;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        cliente = new Cliente();
//        cliente.setId(1L);
//        cliente.setNome("João");
//
//        pet = new Pet();
//        pet.setId(1L);
//        pet.setNome("Rex");
//        pet.setCliente(cliente);
//    }
//
//    @Test
//    void testFindAll() {
//        // Mocking the repository
//        when(petRepository.findAll()).thenReturn(Arrays.asList(pet));
//
//        // Calling the method
//        List<Pet> pets = petService.findAll();
//
//        // Verifications
//        assertNotNull(pets);
//        assertFalse(pets.isEmpty());
//        assertEquals(1, pets.size());
//        verify(petRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testFindById_Success() {
//        // Mocking the repository
//        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
//
//        // Calling the method
//        Optional<Pet> foundPet = petService.findById(1L);
//
//        // Verifications
//        assertTrue(foundPet.isPresent());
//        assertEquals(pet, foundPet.get());
//        verify(petRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testFindById_NotFound() {
//        // Mocking the repository
//        when(petRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Calling the method
//        Optional<Pet> foundPet = petService.findById(1L);
//
//        // Verifications
//        assertFalse(foundPet.isPresent());
//        verify(petRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void testSave_Success() {
//        // Mocking the repository and clienteRepository
//        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
//        when(petRepository.save(any(Pet.class))).thenReturn(pet);
//
//        // Calling the method
//        Pet savedPet = petService.save(pet, 1L);
//
//        // Verifications
//        assertNotNull(savedPet);
//        assertEquals(pet, savedPet);
//        assertEquals(cliente, savedPet.getCliente());
//        verify(clienteRepository, times(1)).findById(1L);
//        verify(petRepository, times(1)).save(any(Pet.class));
//    }
//
//    @Test
//    void testSave_ClienteNotFound() {
//        // Mocking the repository and clienteRepository
//        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Verifying the IllegalArgumentException
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            petService.save(pet, 1L);
//        });
//
//        // Verifications
//        assertEquals("Cliente não encontrado", exception.getMessage());
//        verify(clienteRepository, times(1)).findById(1L);
//        verify(petRepository, times(0)).save(any(Pet.class));
//    }
//
//    @Test
//    void testUpdateById_Success() {
//        // Mocking the repository
//        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
//        when(petRepository.save(any(Pet.class))).thenReturn(pet);
//
//        // Creating a new pet for update
//        Pet updatedPet = new Pet();
//        updatedPet.setNome("Max");
//        updatedPet.setIdade(5);
//        updatedPet.setRaca("Labrador");
//        updatedPet.setCliente(cliente);
//
//        // Calling the method
//        Pet result = petService.updateById(1L, updatedPet);
//
//        // Verifications
//        assertNotNull(result);
//        assertEquals("Max", result.getNome());
//        assertEquals(5, result.getIdade());
//        assertEquals("Labrador", result.getRaca());
//        verify(petRepository, times(1)).findById(1L);
//        verify(petRepository, times(1)).save(any(Pet.class));
//    }
//
//    @Test
//    void testUpdateById_NotFound() {
//        // Mocking the repository
//        when(petRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Calling the method
//        Pet result = petService.updateById(1L, pet);
//
//        // Verifications
//        assertNull(result);
//        verify(petRepository, times(1)).findById(1L);
//        verify(petRepository, times(0)).save(any(Pet.class));
//    }
//
//    @Test
//    void testDelete_Success() {
//        // Mocking the repository
//        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
//
//        // Calling the method
//        boolean result = petService.delete(1L);
//
//        // Verifications
//        assertTrue(result);
//        verify(petRepository, times(1)).findById(1L);
//        verify(petRepository, times(1)).delete(any(Pet.class));
//    }
//
//    @Test
//    void testDelete_NotFound() {
//        // Mocking the repository
//        when(petRepository.findById(1L)).thenReturn(Optional.empty());
//
//        // Calling the method
//        boolean result = petService.delete(1L);
//
//        // Verifications
//        assertFalse(result);
//        verify(petRepository, times(1)).findById(1L);
//        verify(petRepository, times(0)).delete(any(Pet.class));
//    }
//}
