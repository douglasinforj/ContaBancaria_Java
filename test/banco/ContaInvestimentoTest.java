package banco;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test de ContaInvestimento
 * Cobre: 
 *  - sacar() com saldo mínimo
 *  - processarMes()
 *  - projetarSaldo()  
 */

@DisplayName("ContaInvestimento - testes específicos")
public class ContaInvestimentoTest {

    private ContaInvestimento conta;

    // rentabilidade 12 % a.a, taxa ad 1.5% a.a
    @BeforeEach
    void setUp() {
        conta = new ContaInvestimento("Carlos", 1000.0, 1.5, 12.0);
    }

    // 1 - Construtor

    @Test
    @DisplayName("Deve Criar conta saldo inicial correto")
    void deveCriarContaSaldoCorreto() {
        assertEquals(1000.0, conta.getSaldo());
    }

    // 2 - sacar()

    @Test
    @DisplayName("Saque válido deve reduzir saldo corretamente")
    void saqueValidoDeveReduzirSaldo() {
        conta.sacar(500.0);
        assertEquals(500.0, conta.getSaldo());
    }

    @Test
    @DisplayName("Saque deixando exatamente R$ 100 deve funcionar")
    void saqueDeixandoSaldoMinimoDeveFuncionar() {
        conta.sacar(900.0);
        assertEquals(100, conta.getSaldo(), 0.001);
    }

    // teste 3 - Saca() - Exceções

    @Test
    @DisplayName("Saque que deixaria saldo abaixo de R$100 deve lançar exceção")
    void saqueAbaixoDoSaldoMinimoDeveLancarExcecao() {
        assertThrows(IllegalStateException.class,
            () -> conta.sacar(1500.0)
        );
    }

    @Test
    @DisplayName("Saque com valor zero deve lançar exceção")
    void saqueZeroDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(0)
        );
    }

    @Test
    @DisplayName("Saque com valor negativo deve lançar exceção")
    void saqueNegativoDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(-100)
        );
    }

    @Test
    @DisplayName("Saldo não deve ser alterado após exceção de saque inválido")
    void saqueNaoDeveAlterarAposExcecao () {
        try { conta.sacar(950.0);} catch (IllegalStateException ignored) {}
        assertEquals(1000.0, conta.getSaldo());
    }








}
