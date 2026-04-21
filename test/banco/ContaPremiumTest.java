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


    // --teste 4 -----creditarBonus-----------

    @Test
    @DisplayName("creditarBonus() deve aumentar o saldo corretamente")
    void creditarBonusDeveAumentarSaldo() {
        conta.creditarBonus(2.0);  // 2% de 5000 = 100
        assertEquals(5100, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("creditarBonus() com 0% não deve ser alterado")
    void creditarBonusNaoDeveSerAlterado() {
        conta.creditarBonus(0.0);
        assertEquals(5000, conta.getSaldo());
    }

    @Test
    @DisplayName("creditaBonus() com 0% não deve alterar saldo")
    void creditarBonusZeroNaoDeveAlterarSaldo() {
        conta.creditarBonus(0);
        assertEquals(5000.0, conta.getSaldo());
    }

    @Test
    @DisplayName("creditarBonus() deve calcular percentual sobre saldo atual")
    void creditarBonusDeveUsarSaldoAtual() {
        conta.depositar(5000.0);    //saldo passa para 10000
        conta.creditarBonus(1.0);   // 1% de 10000 = 100
        assertEquals(10100.0, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Bônus acumulados devem crescer sobre saldo atualizado")
    void bonusAcumuladosDevemCrescerSobreSaldoAtualizado() {
        conta.creditarBonus(10.0);          //saldo = 5500
        double saldoApois1Bonus = conta.getSaldo();           //variavel com valor para comparação
        conta.creditarBonus(10);
        assertTrue(conta.getSaldo() > saldoApois1Bonus);      //verifica se houve alteração com acumulado
    }


    // --- 5 - Herança de ContaCorrente - cheque especial--------------------------

    @Test
    @DisplayName("Deve usar cheque especial de R$ 2.000 no saque")
    void deveUsarChequeEspecialDe2000Saque() {
        // saldo 5000 + limite 2000 = 7000 disponível
        conta.sacar(7000);
        assertEquals(-2000, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Saque acima do limite máximo deve lançar exceção")
    void saqueAcimaDoLimiteMaximoDeveLancarExcecao() {
        assertThrows(IllegalStateException.class,
            () -> conta.sacar(7001.0)
        );
    }

    



}
