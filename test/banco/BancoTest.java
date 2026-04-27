package banco;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de Banco
 * Cobre: 
 *  - adicionarConta()
 *  - buscarPorNumeroConta()
 *  - buscarPorTitular()
 *  - listarContas()
 *  - calcularPatrimonioTotal
 */

public class BancoTest {

    private Banco banco;
    private ContaCorrente cc;
    private ContaPoupanca cp;
    private ContaInvestimento ci;

    @BeforeEach
    void setUp() {
        banco = new Banco("TestBank");
        cc = new ContaCorrente("Alice", 1000.0);
        cp = new ContaPoupanca("Bob", 2000.0);
        ci = new ContaInvestimento("Carol", 3000, 1.5, 12.0);
    }

    // Teste 1 ------Contrutor----------

    @Test
    @DisplayName("Banco deve iniciar sem contas")
    void deveIniciarSemContas(){
        assertTrue(banco.getContas().isEmpty());
    }

    @Test
    @DisplayName("Banco deve guardar o Nome corretamente")
    void deveGuardarNomeCorretamente () {
        assertEquals("TestBank", banco.getName());
    }

    // Teste 2 -----------AdicionarConta()--------------------

    @Test
    @DisplayName("Deve adicionar uma conta Corretamente")
    void deveAdicionarUmaConta() {
        banco.adicionarConta(cc);
        assertEquals(1, banco.getContas().size());
    }

    @Test
    @DisplayName("Deve adicionar múltiplas contas de tipos diferentes")
    void deveAdicionarMutiplasContas() {
        banco.adicionarConta(cc);
        banco.adicionarConta(cp);
        banco.adicionarConta(ci);
        assertEquals(3, banco.getContas().size());
    }

    @Test
    @DisplayName("getContas() deve retornar lista imutável de contas")
    void getContasDeveRetornarListaImutavel() {
        banco.adicionarConta(cc);                               //adiciono uma conta, ou varias
        List<ContaBancaria> contas = banco.getContas();         // retornando a lista com getContas ( com copyOf )
        assertThrows(UnsupportedOperationException.class,       //lança uma exceção, quando se tenta modificar uma coleção imutavel
            () -> contas.add(cp)                                // teantativa de modificar a lista imutável
        );
    }

    // ---teste 3 buscarPorNumeroConta()---------------

    @Test
    @DisplayName("Deve encontrar conta pelo número correto")
    void deveEncontrarContaPeloNumero() {
        banco.adicionarConta(cc);          //adionando conta
        Optional<ContaBancaria> resultado = banco.buscarPorNumeroConta(cc.getNumeroConta());  //usando tipo de retorno seguro com optional, metodo de buscar pelo numero da conta, obtendo o numero da conta criada
        assertTrue(resultado.isPresent());   //tornando seguro o resultado para evitar null
        assertEquals(cc.getNumeroConta(), resultado.get().getNumeroConta());  // esperado  |  recuperando o resultado | pega o numero encontrado
    }

    @Test
    @DisplayName("Deve retornar Optional vazio para número inexistente")
    void deveRetornarVazioParaNuemroInexistente() {
        banco.adicionarConta(cc);
        Optional<ContaBancaria> resultado = banco.buscarPorNumeroConta("00000-0");
        assertFalse(resultado.isPresent());
    }



    // -----4. buscarPorTitular() --------------------------------

    @Test
    @DisplayName("Deve encontrar conta pelo nome do titular")
    void deveEncontrarContaPeloTitular() {
        banco.adicionarConta(cc);
        List<ContaBancaria> resultado = banco.buscarPorTitular("Alice");
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Buscar por titular de ser case-insensitive")
    void buscarTitularDeveSercaseInsensitive() {
        banco.adicionarConta(cc);
        List<ContaBancaria> resultado = banco.buscarPorTitular("alice");
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Deve retornar lista vazia para titular inexistente")
    void deveRetornarVazioParaTitularInexixtente() {
        banco.adicionarConta(cc);
        List<ContaBancaria> resultado = banco.buscarPorTitular("Inexistente");
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar Multiplas contas do mesmo titular")
    void deveRetornarMultiplasContasDoMesmoTitular() {
        ContaCorrente cc2 = new ContaCorrente("Alice",500.0);
        banco.adicionarConta(cc);
        banco.adicionarConta(cc2);
        List<ContaBancaria> resultado = banco.buscarPorTitular("Alice");
        assertEquals(2, resultado.size());
    }

    // ---5 calcularPatrimonilaTotal()

    @Test
    @DisplayName("Patrimonio com zero contas deve ser zero")
    void patrimonioComZeroContasDeveSerZero() {
        assertEquals(0.0, banco.calcularPatrimonioTotal());
    }

    @Test
    @DisplayName("Patrimonio deve somar saldo de todas as contas")
    void patrimonioDeveSomarTodosOsSaldos() {
        banco.adicionarConta(cc);    //1000
        banco.adicionarConta(cp);    //2000
        banco.adicionarConta(ci);    // 3000
        assertEquals(6000.0, banco.calcularPatrimonioTotal(), 0.001);
    }

    @Test
    @DisplayName("PAtrimonio deve atualizar após operações nas contas")
    void patrimonioDeveAtualizarAposOperacoes() {
        banco.adicionarConta(cc);
        banco.adicionarConta(cp);
        cc.depositar(500.0);    //passará para 1500
        assertEquals(3500.0, banco.calcularPatrimonioTotal(), 0.001);
    }







}
