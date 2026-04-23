package banco;


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

    @Test
    @DisplayName("Deve adicionar uma conta Corretamente")
    void deveAdicionarUmaConta() {
        banco.adicionarConta(cc);
        assertEquals(1, banco.getContas().size());
    }

    @Test
    @DisplayName("Deve adicionar múltiplas contas de tipos diferentes")
    void deveAdicionarMutiplasContas() {
        banco.adicionarConta(cc);
        banco.adicionarConta(cp);
        banco.adicionarConta(ci);
        assertEquals(3, banco.getContas().size());
    }

    @Test
    @DisplayName("getContas() deve retornar lista imutável de contas")
    void getContasDeveRetornarListaImutavel() {
        banco.adicionarConta(cc);                               //adiciono uma conta, ou varias
        List<ContaBancaria> contas = banco.getContas();         // retornando a lista com getContas ( com copyOf )
        assertThrows(UnsupportedOperationException.class,       //lança uma exceção, quando se tenta modificar uma coleção imutavel
            () -> contas.add(cp)                                // teantativa de modificar a lista imutável
        );
    }




}
