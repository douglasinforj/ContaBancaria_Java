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
    @DisplayName("Deve Criar Conta Com saldo Inicial correto")
    void deveCriarContaComSaldoCorreto() {
        assertEquals(1000.0, conta.getSaldo());
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

    // Teste 3 - Sacar() - Exceções -------------------------------------

    @Test
    @DisplayName("Quinto saque no mês deve lançar excessao")
    void quitoSaqueDeveLancarExcecao() {
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.sacar(100.0);
        assertThrows(IllegalStateException.class,
            () -> conta.sacar(100.0)
        );
    }

    @Test
    @DisplayName("Saque acima do saldo deve lançar excecao")
    void saqueAcimaSaldoDeveLancarExcessao () {
        assertThrows(IllegalStateException.class,
            () -> conta.sacar(1500.0)
        );
    }

    @Test
    @DisplayName("Saque com valor zero deve lançar excessao")
    void saqueZeroDeveLancarExcecao () {
        assertThrows(IllegalArgumentException.class, 
            () -> conta.sacar(0)
        );
    }

    @Test
    @DisplayName("Saque com valor negativo deve lançar excessao")
    void saqueNegativoDeveLancarExcecao () {
        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(-50.0)
        );
    }

    @Test
    @DisplayName("Saldo não deve ser alterado após excecao de saque inválido")
    void saldoNaoDeveAlterarAposExcecao () {
        try { 
            conta.sacar(9999.0);
        } catch (IllegalStateException ignored){
        
        }
        assertEquals(1000, conta.getSaldo());
    }

    // - teste 4 -----aplicarRendimento()-----------------

    @Test
    @DisplayName("Rendimento deve aumentar o saldo em 0,5%")
    void rendimentoDeveAumentarSaldo() {
        conta.aplicarRendimento();                          //aplica o rendimento no saldo de 1000.0
        assertEquals(1005.0, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Contador deve resetar contador de saques para zero")
    void rendimentoDeveResetarContadorDeSaques() {
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.aplicarRendimento();
        assertEquals(0, conta.getSaquesMensais());   //verifica se zerou os saques
    }

    @Test
    @DisplayName("Após reset do rendimento deve permitir 4 saques novamente")
    void aposRendimentoDevePermitir4SaquesNovamente() {
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.sacar(100.0);
        conta.aplicarRendimento();       //reset do contador
        assertDoesNotThrow(             //garante que este trecho não lança excessao, ou seja testa se o codigo não esta lancando excecao, pois resetou
            () -> conta.sacar(100.0)
        );
    }

    @Test
    @DisplayName("Rendimentos acumulados devem crescer sobre saldo atualizado")
    void rendimentosAcumuladosDevemCrescerSobreSaldoAtualizado() {
        conta.aplicarRendimento();   //saldo 1000 -> 1005
        conta.aplicarRendimento();   //saldo 1005 -> 1010.025
        assertTrue(conta.getSaldo() > 1005.0);   //verifica se é verdade ser houve acumulo de rendimentos
    }

    // - teste 5 - getTipoConta() -----------------------------------------

    @Test
    @DisplayName("getTipoConta() deve retornar CONTA POUPANCA")
    void deveRetornarTipoConta () {
        assertEquals("CONTA POUPANÇA", conta.getTipoConta());
    }

}
