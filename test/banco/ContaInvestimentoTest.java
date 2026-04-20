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



}
