package repository;

import org.example.model.Deposito;
import org.example.repository.DepositoRepository;

@ExtendWith(MockitoExtension.class)
class DepositoRepositoryTest {
    @Mock
    DepositoRepository depositoRepository;

    @Test
    @DisplayName("Should not remove any deposit when the provided code does not match any existing deposit")
    void eliminarDepositoPorCodigoWhenCodeDoesNotMatch() {
        DepositoRepository depositoRepository = new DepositoRepository();
        Deposito deposito = new Deposito("4", "Deposito Test", "Calle Test", "12345678", "test@gmail.com", new Ubicacion(123, 456));

        depositoRepository.eliminarDepositoPorCodigo(deposito);

        verify(depositoRepository, never()).eliminarDepositoPorCodigo(deposito);
        assertEquals(3, depositoRepository.buscarTodosLosDepositos().size());
    }

    @Test
    @DisplayName("Should remove the deposit when the provided code matches an existing deposit")
    void eliminarDepositoPorCodigoWhenCodeMatches() {        // Create a new deposit
        Deposito deposito = new Deposito("1", "Deposito Test", "Calle Test", "123456789", "test@example.com", new Ubicacion(0, 0));

        // Mock the behavior of the DepositoRepository
        when(depositoRepository.buscarDepositoPorCodigo("1")).thenReturn(deposito);

        // Call the method under test
        depositoRepository.eliminarDepositoPorCodigo(deposito);

        // Verify that the deposit was removed
        verify(depositoRepository, times(1)).eliminarDepositoPorCodigo(deposito);
        assertFalse(depositoRepository.buscarTodosLosDepositos().contains(deposito));
    }

    @Test
    @DisplayName("Should remove the deposit when the provided deposit exists in the list")
    void eliminarDepositoPorCodigoWhenDepositExists() {        // Create a deposit to be removed
        Deposito deposito = new Deposito("1", "Deposito Test", "Calle Test", "123456789", "test@example.com", new Ubicacion(0, 0));

        // Add the deposit to the repository
        depositoRepository.getDepositos().add(deposito);

        // Call the method to remove the deposit
        depositoRepository.eliminarDepositoPorCodigo(deposito);

        // Verify that the deposit has been removed from the repository
        assertFalse(depositoRepository.getDepositos().contains(deposito));
    }

    @Test
    @DisplayName("Should not change the list when the provided deposit does not exist in the list")
    void eliminarDepositoPorCodigoWhenDepositDoesNotExist() {        // Create a deposito object
        Deposito deposito = new Deposito("4", "Deposito Test", "Calle Test", "123456789", "test@example.com", new Ubicacion(123, 456));

        // Call the eliminarDepositoPorCodigo method
        depositoRepository.eliminarDepositoPorCodigo(deposito);

        // Verify that the deposito object is not removed from the depositos list
        assertFalse(depositoRepository.getDepositos().isEmpty());
        assertTrue(depositoRepository.getDepositos().contains(deposito));

        // Verify that the eliminarDepositoPorCodigo method is called only once
        verify(depositoRepository, times(1)).eliminarDepositoPorCodigo(deposito);
    }

    @BeforeEach
    void setUp() {
        depositoRepository = new DepositoRepository();
    }
}