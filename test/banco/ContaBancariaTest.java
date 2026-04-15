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







}
