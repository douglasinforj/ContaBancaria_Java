package banco;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Teste de Conta Corrente
 * 
 * Aqui testamos somente o que é especifico de ContaCorrente:
 * - sacar() com ou sem cheque especial
 * -limite especial (getter/setter)
 * -construtor com e sem limite customizado
 * 
 */

@DisplayName("ContaCorrente - teste especifico da classe")
public class ContaCorrentetest {

    private ContaCorrente conta;

    @BeforeEach
    void setUP() {
        // saldo: 1000.0 , limite especial padrão na classe: 500.0
        conta = new ContaCorrente("Ana Teste", 1000.0);
    }

    //--Teste 1 Contrutores---------------------

    @Test
    @DisplayName("Construtor padrão deve definir limite especial de 500")
    void construtorPadraoDefinirLimite500() {
        assertEquals(500.0, conta.getLimiteEspecial());  //verificando limite padrão
    }

    @Test
    @DisplayName("Construtor com limite customizado deve respeita-lo")
    void construtorCustomDeveDefinirLimiteCorreto() {
        ContaCorrente c = new ContaCorrente("Pedro", 2000.0,1500.0); //customizando o limite
        assertEquals(1500.0, c.getLimiteEspecial());
    }

    @Test
    @DisplayName("Deve lançar excessao para limite especial negativo")
    void deveLancarExcecaoParaLimiteNegativo() {
        assertThrows(IllegalArgumentException.class,
            () -> new ContaCorrente("Pedro", 1000.0,-1.0)
        );
    }


    // ---teste 2 - Sacar() dentro do saldo

    @Test
    @DisplayName("Saque dentro do saldo")
    void saqueDentroDoSaldoDeveReduzirSaldo() {
        conta.sacar(400.0);
        assertEquals(600.0, conta.getSaldo());
    }

    @Test
    @DisplayName("Saque do valor exato do saldo deve zerar a conta")
    void saqueDoSaldoDeveZerarConta() {
        conta.sacar(1000.0);
        assertEquals(0.0, conta.getSaldo());
    }

    //---teste 3 - sacar() - uso do cheque especial
    
    @Test
    @DisplayName("Saque além do saldo deve usar cheque especial")
    void saqueAlemSaldoDeveUsarChequeEspecial() {
        // saldo 1000 + limite 500 = 1500
        conta.sacar(1200.0);
        assertEquals(-200.00, conta.getSaldo(), 0.001);
    }

    @Test
    @DisplayName("Saque até o limite máximo (saldo + limite) deve funcionar")
    void saqueAteOLimiteMaximoDeveFuncionar() {
        conta.sacar(1500);    //saldo 1000 + limite especial 500
        assertEquals(-500.00, conta.getSaldo());
    }

    @Test
    @DisplayName("Saque acima do limite máximo deve lançar a exceção")
    void saqueAcimaDoLimiteMaximoDeveLancarExcecao() {
        //maximo disponivel = 1500, tentando 1501
        assertThrows(IllegalStateException.class,     //testa se esta violando os estado atual do sistema, onde tem um limite de saque definido
            () -> conta.sacar(1501.0)
        );
    }

    @Test
    @DisplayName("Saque com valor zero deve lançar exceção")
    void saqueComValorZeroDeveLancarExcecao() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(0)
        );
    }

    @Test
    @DisplayName("Saque com valor negativo deve lançar exceção")
    void saqueComValorNegativoDeveLancarExcecao () {
        assertThrows(IllegalArgumentException.class,
            () -> conta.sacar(-50.0)
        );
    }


    // -- teste 4 - Saque múltiplo ---------------------------

    @Test
    @DisplayName("Saque sequenciais devem acumular corretamente")
    void saquesSequenciaisDevemAcumular() {
        conta.sacar(300.0);
        conta.sacar(300.0);
        conta.sacar(300.0);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    @DisplayName("Segundo saque além do saldo deve verificar limite restante")
    void segundoSaqueDeveVerificarLimiteRestante() {
        conta.sacar(1000.0);   //zera saldo, ainda resta 500 do limite especial
        conta.sacar(500.0);    //usa o limite especial
        assertEquals(-500, conta.getSaldo(), 0.001);
    }

    // ---teste 5 - setLimiteEspecial - atualização de limite

    @Test
    @DisplayName("Deve Atualizar limite especial com valor válido")
    void deveAtualizarLimiteEspecial() {
        conta.setLimiteEspecial(1000);
        assertEquals(1000, conta.getSaldo());
    }

    @Test
    @DisplayName("Deve aceitar limite especial zero")
    void deveAceitarLimiteZero() {
        conta.setLimiteEspecial(0.0);
        assertEquals(0.0, conta.getLimiteEspecial());
    }

    @Test
    @DisplayName("Deve lançar excecao ao setar limite negativo")
    void deveLancarExcecaoParaLimiteNegativoNoSetter() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.setLimiteEspecial(-100.0)
        );
    }



}
