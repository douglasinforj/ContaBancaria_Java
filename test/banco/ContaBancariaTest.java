package banco;

import org.junit.jupiter.api.BeforeEach;   //Diz para executar o metodo marcado antes de rodar um test
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Testes de ContaBancaria (classe abstrata)
 * 
 * Como ContaBancaria é abstrata, uso ContaCorrente como implementação
 * concreta mínima, pois ela herda ContaBancaria - testamos só o que está definido na classe base,
 * não o comportamento da subclasse
 */

@DisplayName("ContaBancaria - testes da classe base")
class ContaBancariaTest {

    private ContaCorrente conta; // ContaCorrente como veículo de teste
 
    @BeforeEach       //roda metodo primeiro antes de cada teste(geral)
    void setUp() {    //método de preparação(setup do ambiente de teste)
        conta = new ContaCorrente("João Teste", 1000.0);
    }

    // Teste 1 ----Construtor--------------------------------------------

    @Test
    @DisplayName("Deve criar conta com titular e saldo inicial corretos")
    void deveCriarContaComDadosCorretos() {
        assertEquals("João Teste", conta.getTitular());    //verifica Resultado esperado "João Teste" com getTitula que deve trazer após instancia 
        assertEquals(1000.0, conta.getSaldo());            // Esperado é 1000.0 comparando com getSaldo (saldo inicial)
    }

    //gerarNumeroConta()
    @Test
    @DisplayName("Deve gerar número de conta não nulo e não vazio")
    void deveGerarNumeroDeConta() {
        assertNotNull(conta.getNumeroConta());            //teste de esta nulo
        assertFalse(conta.getNumeroConta().isBlank());   //teste se esta em branco
    }


    // Verifica se o incremento de contador na agencia esta incrementando corretamente
    @Test
    @DisplayName("Deve gerar agência não nula e não Vazia")
    void deveGerarAgencia() {
        assertNotNull(conta.getAgencia());              //Verifica se esta criando com base na regra de incremento de contador na base
        assertFalse(conta.getAgencia().isBlank());
    }

    //testando exceção para titular nulo
    @Test
    @DisplayName("Deve lançar exceção para titular nulo")
    void deveLancarExcecaoParaTitularNulo(){
        assertThrows(IllegalArgumentException.class,                        //testa se ao criar a conta com titular nulo
            () -> new ContaCorrente(null, 1000.0));   //não recebe nada | cria uma conta | parametros
    }
    
    //testando exceção para titular em branco
    @Test
    @DisplayName("Deve lançar exceção para titular em branco")
    void deveLancarExcecaoParaTitularEmBranco() {
        assertThrows(IllegalArgumentException.class,
            () -> new ContaCorrente("   ", 1000.0)
        );
    }

    //testando exceção saldo inicial negativo
    @Test
    @DisplayName("Deve lançar exceção para saldo inicial negativo")
    void deveLancarExcecaoParaSaldoInicialNegativo() {
        assertThrows(IllegalArgumentException.class,
            () -> new ContaCorrente("João", -1.0)
        );
    }

    //testando exceção Deve permitir saldo inicial zero
    void devePermitirSaldoZero(){
        ContaBancaria c = new ContaCorrente("João", 0.0);
        assertEquals(0.0, c.getSaldo());
    }

    // Teste 2 ---depositar()-------------------------------------

    @Test
    @DisplayName("Deve depositar e atualizar saldo Corretamente")
    void deveDepositarCorretamente(){
        conta.depositar(500.0);
        assertEquals(1500.0, conta.getSaldo());     //Esperado 1500 - buscando saldo, verificando o somatorio de 1000 (inicial) + 500 depositado
    }

    @Test
    @DisplayName("Deve lançar exceção para depósito zero")
    void deveLancarExcecaoParaDepositoZero() {
        assertThrows(IllegalArgumentException.class, 
            () -> conta.depositar(0));
    }

    @Test
    @DisplayName("Deve lançar exceção para deposito negativo")
    void deveLancarExcecaoParaDepositoNegativo() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.depositar(-100.0)
        );
    }

    @Test
    @DisplayName("Depósito acumulados devem somar corretamente")
    void depositosAcumuladosDevemSomar() {
        conta.depositar(200.0);
        conta.depositar(300.0);
        assertEquals(1500, conta.getSaldo());   //onde saldo inicial era de 1000.0
    }

    //Teste 3 - ---transferir-------------------------------

    @Test
    @DisplayName("Deve transferir valor entre contas corretamente")
    void deveTransferirEntreContas() {
        ContaCorrente destino = new ContaCorrente("Maria", 500.0);
        conta.transferir(300, destino);   //conta esta com saldo inicial de 1000, transferiu para destino "Maria"
        assertEquals(700, conta.getSaldo());     // saldo da conta 1000 - 300 = 700
        assertEquals(800.0, destino.getSaldo());  // saldo do dstino "Maria"   500 + 300 = 800
    }

    @Test
    @DisplayName("Transferencia deve lançar exceção se saldo insuficiente")
    void deveLancarExcecaoSeSaldoInsuficienteNaTransferencia() {
        ContaCorrente destino = new ContaCorrente("Maria", 0.0);
        // Saldo = 1000, limite = 500 (padrão) -> máximo = 1500
        // Tentando 2000 -> deve falhar
        assertThrows(IllegalStateException.class, 
            () -> conta.transferir(2000.0, destino)
        );
    }


    //Teste 4 - ---setTitular()

    @Test
    @DisplayName("Deve atualizar titular com valor válido")
    void deveAtualizarTitular() {
        conta.setTitular("Carlos Novo");
        assertEquals("Carlos Novo", conta.getTitular());
    }

    @Test
    @DisplayName("Deve lançar exceção ao setar titular nulo")
    void deveLancarExcecaoAoSetarTitularNulo() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.setTitular(null)
        );
    }

    @Test
    @DisplayName("Deve lançar exceção ao setar titular em branco")
    void deveLancarExcecaoAoSetarTitularEmBranco() {
        assertThrows(IllegalArgumentException.class,
            () -> conta.setTitular("")
        );
    }


    // Teste 5 - função toString()

    @Test
    @DisplayName("toString() deve conter o nome do titular")
    void toStringDeveConterTitular() {
        assertTrue(conta.toString().contains("João Teste"));
    }
    
    @Test
    @DisplayName("toString() deve conter o saldo")
    void toStringDeveConterSaldo(){
        assertTrue(conta.toString().contains("1000"));
    }




}
