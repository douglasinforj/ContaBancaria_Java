package banco;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de ContaPremium.
 * Cobre:
 *  - limite especial padrão. calcularImposto(), descricaoTributo(). Herdado | Interface
 *  - creditarBonus() e getTipoConta
 *  - Não repete testes de sacar já cobertos em ContaCorrenteTest
 */

@DisplayName("ContaPremium - teste específico")
public class ContaPremiumTest {

    private ContaPremium conta;

    @BeforeEach
    void setUp() {
        conta = new ContaPremium("Daniel Teste", 5000.0);
    }


    // teste 1 ------Construtor---------------------------------
    @Test
    @DisplayName("Deve Criar conta com saldo inicial correto")
    void deveCriarContaComSaldoCorreto() {
        assertEquals("5000.0", conta.getSaldo());
    }

    @Test
    @DisplayName("Limite especial padrão da Premium deve ser R$ 2.000")
    void limiteEspecialPadraoDeveSer2000() {
        assertEquals(2000.0, conta.getLimiteEspecial());
    }

}
