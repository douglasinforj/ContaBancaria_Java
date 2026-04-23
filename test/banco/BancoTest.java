package banco;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de Banco
 * Cobre: 
 *  - adicionarConta()
 *  - buscarPorNumeroConta()
 *  - buscarPorTitular()
 *  - listarContas()
 *  - calcularPatrimonioTotal
 */

public class BancoTest {

    private Banco banco;
    private ContaCorrente cc;
    private ContaPoupanca cp;
    private ContaInvestimento ci;

    @BeforeEach
    void setUp() {
        banco = new Banco("TestBank");
        cc = new ContaCorrente("Alice", 1000.0);
        cp = new ContaPoupanca("Bob", 2000.0);
        ci = new ContaInvestimento("Carol", 3000, 1.5, 12.0);
    }

    // Teste 1 ------Contrutor----------

    @Test
    @DisplayName("Banco deve iniciar sem contas")
    void deveIniciarSemContas(){
        assertTrue(banco.getContas().isEmpty());
    }

    @Test
    @DisplayName("Banco deve guardar o Nome corretamente")
    void deveGuardarNomeCorretamente () {
        assertEquals("Test Bank", banco.getName());
    }

    // Teste 2 -----------AdicionarConta()--------------------

   




}
