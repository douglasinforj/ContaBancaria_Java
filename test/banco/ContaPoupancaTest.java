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


}
