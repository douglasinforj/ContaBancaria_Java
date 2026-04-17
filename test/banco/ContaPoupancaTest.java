package banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  Teste de ContaPoupanca
 *  Cobre: 
 *  - sacar() com limite mensal
 *  - aplicarRendimento()
 *  - getSaquesMensais()
 * 
 *  Não cobrindo o que ja foi testado depositar/transferir ja cobertos em ContaBancariaTest
 * 
 */

@DisplayName("ContaPoupanca - testes específicos")
public class ContaPoupancaTest {

    private ContaPoupanca conta;

    @BeforeEach
    void setUp() {
        conta = new ContaPoupanca("Maria Teste", 1000.0);
    }

    // teste 1 - Contrutor ------------------------------

    @Test
    @DisplayName("Deve iniciar com zero saques mensais")
    void deveIniciarComZeroSaquesMensais(){
        assertEquals(0, conta.getSaquesMensais());
    }

    @Test
    @DisplayName("Deve Criar Conta Con saldo Inicial correto")
    void deveCriarContaComSaldoCorreto() {
        assertEquals(1000.0, 0);
    }


    // teste 2 - sacar()

    @Test
    @DisplayName("Saque válido deve reduzir saldo corretamente")
    void saqueValidoDeveReduzirSaldo() {
        conta.sacar(300.0);
        assertEquals(700.0, conta.getSaldo());
    }

    @Test
    @DisplayName("Saque deve incrementar contador saques mensais")
    void saqueDeveIncrementarContatos () {
        conta.sacar(100.0);
        assertEquals(1, conta.getSaquesMensais());
    }

    @Test
    @DisplayName("Deve permitir até 4 saques mensais")
    void devePermitirAte4SaquesMensair() {
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.sacar(100.0);
        assertEquals(600, conta.getSaldo());
    }

    @Test
    @DisplayName("Saque do valor exato do saldo deve zerar conta")
    void saqueExatoDeveZerarSaldo() {
        conta.sacar(1000.0);
        assertEquals(0, conta.getSaldo());
    }


}
