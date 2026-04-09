package banco;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Testes da interface Tributavel.
 * 
 * Como Tributavel é uma interface, testamos atraves de uma classe anônima que implementa
 * assim isolamos só o "contrato da interface", sem depender de nenhuma conta ainda.
 * conceitos juntos: JUnit 5 + interface + classe anônima + teste isolado.
 */

@DisplayName("Tributavel - testes da interface")  //Anotação JUnit5 - Define nome amigável
class TributavelTest {                            //Nome da classe que quero testar

    /**
     * Classe anônima (sem nome, criada na hora, Usada uma vez)simples para implementar a interface
     * Obs.: interfaces não podem ser intanciadas
    */
    private Tributavel criarTributavel(double saldo) {    //Método auxiliar para criar um objeto (fake) que implementa interface,  
        return new Tributavel() {                        //implementação inline da interface
            @Override
            public double calcularImposto() {            //implementando o método da Interface
                return saldo * ALIQUOTA_IOF;             //Para testar colocamos o imposto será saldo * taxa
            }
        };
    }

    // ---1. Testando a constante ALIQUOTA_IOF
    @Test                                                  //Anotação JUnit5 (Teste automatizado)
    @DisplayName("Alíquota IOF deve ser 0.0038 (0,38%)")   // Nome amigavel do teste
    void deveTeraAliquotaCorreta() {                     //Criando o método
        assertEquals(0.0038, Tributavel.ALIQUOTA_IOF);   //Testando a ALIQUOTA_IOF na Classe(interface) se foi alterada
    }                                                   //na interface a variavel é constante(nunca muda)

    // ---2. calcularImposto()
    @Test
    @DisplayName("calcularImposto() deve retornar 0,38% do saldo")
    void deveCalcularImpostoCorretamente() {
        Tributavel t = criarTributavel(1000.0);     //Criando um objeto do tipo tributavel com saldo 1000, 
                                                          //criamos a class anonima que recebe uma parametro e instanciamos criando um objeto "t"
                                                          //contem o método calcularImposto

        assertEquals(3.80, t.calcularImposto(), 0.001);   //Verifica se o calculo é igual a 3.80 com tolerancia de 0.001"
    }

    @Test
    @DisplayName("calcularImposto() com saldo zero deve retornar zero")
    void deveRetornarZeroSaldoZero(){
        Tributavel t = criarTributavel(0.0);
        assertEquals(0.0, t.calcularImposto());
    }

    @Test
    @DisplayName("calcularImposto() deve escalar proporcionalmente ao saldo")   //se o saldo dobra -> o imposto deve dobrar
    void impostoDeveEscalarComSaldo(){
        Tributavel t1 = criarTributavel(1000.0);       //objetos criado e recebendo os valores para teste
        Tributavel t2 = criarTributavel(2000.0);
        assertEquals(t1.calcularImposto() * 2, t2.calcularImposto(), 0.001); 
        //t2 tem que ser o dobro de imposto de t1. para comparar multipliquei t1 x2, para darem iguais.
    }



}
