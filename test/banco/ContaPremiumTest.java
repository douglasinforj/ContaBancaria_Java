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

    // teste 2 -------Tributavel - calcularImposto()---------------

    @Test
    @DisplayName("calcularImposto() deve retornar 0,38% do saldo")
    void deveCalcularImpostoCorretamente() {
        // 5000 * 0.0038 = 19.0
        assertEquals(19.0, conta.calcularImposto(), 0.001);
    }


    @Test
    @DisplayName("calcularImporto() deve recalcular após depósito")
    void deveRecalcularImpostoAposDeposito() {
        conta.depositar(5000); // saldo sobe para 10000
        assertEquals(38, conta.calcularImposto(), 0.001);
    }

    @Test
    @DisplayName("calcularImposto() deve recalcular após Saque")
    void deveRecalcularAposSaque() {
        conta.sacar(2500.0);    //saldo ficará em R$ 2500.00
        assertEquals(9.5, conta.calcularImposto());
    }

    // ---teste 3 ----Tributavel --descricaoTributo()-----------------

    @Test
    @DisplayName("descricaoTributo() não deve ser nulo e nem vazio")
    void descricaoNaoDeveSerVazia() {
        assertNotNull(conta.descricaoTributo());
        assertNotNull(conta.descricaoTributo().isBlank());
    }

    @Test
    @DisplayName("descricaoTributo() deve conter o valor do imposto")
    void deveConterValorDoImposto() {
        //importo = 5000 * 0.0038 = 19.0
        assertTrue(conta.descricaoTributo().contains("19,00") 
        || conta.descricaoTributo().contains("19.00"));
    }


}
