package banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gerenciador do banco - controla todas as contas do Banco
 * Demostração:
 *  - Coleções - List (ordenada, permite duplicatas), 
 *               Set (não ordenada, não permite duplicatas) e 
 *               Map (pares chave-valor, não faz parte da interface Collection raiz, mas faz parte do Framework).
 * 
 *  - Generics - Permitem que classes, interfaces e métodos operem sobre tipos parametrizados (definição de tipos em tempo de compilação
 *  
 * - Optional - Uma classe contêiner introduzida no Java 8 que pode ou não conter um valor não nulo 
 *               Evitar o temido NullPointerException ao projetar APIs, forçando o programador a tratar 
 *               o caso onde o valor está ausente (Optional.empty()). Oferece métodos funcionais como ifPresent(), 
 *               orElse(), e map() para processar valores de forma segura
 * 
 *  - Stream   - básico permite o processamento funcional de sequências de elementos (como coleções) de forma declarativa, sem a necessidade 
 *               de loops imperativos (for ou while)
 *               Características: Não armazenam dados, são consumíveis (só podem ser usados uma vez) e não alteram a fonte de dados original.
 *               Composto por Fonte (ex: .stream()), Operações Intermediárias (ex: .filter(), .map()) e Operação Terminal (ex: .collect(), .forEach())
 */



public class Banco {    

    private final String nome;
    private final List<ContaBancaria> contas;

    //Construtor da Class
    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    /**
     *  public void adicionarConta(ContaBancaria conta) - exemplo de injeção de dependência por método (ou Stter injection, se for usado para definir um campo)
     *  Em vez de criar a ContaBancaria dentro de método usando 'new' recebemos pronta de quem chamou o método, reduzindo acoplamento
     *  
     * Pontos chaves:
     *  - Desacoplamento: A classe que contém o método 'adicionarConta' não precisa saber como criar uma ContaBancaria, apenas usar-la
     *  - Controle Externo: A responsabilidade de instanciar o objeto ContaBancaria passa para um componente externo (quem chama o método),
     *    o que é a essência da INVERSÃO DE CONTROLE
     *  - Em resumo, qualquer forma de passar um objeto necessário para dentro de uma classe, em vez de criá-lo internamente, 
     *    é considerada INJEÇÃO DE DEPENDÊNCIA.
     */
    public void adicionarConta(ContaBancaria conta) {  
        contas.add(conta);
        System.out.printf("[BANCO] Conta aberta para %s (%s)%n",
            conta.getTitular(), conta.getTipoConta()
         );
    }

    public Optional<ContaBancaria> buscarPorNumeroConta(String numero) {  // farei busca pelo titular
        return contas.stream()
                .filter(c -> c.getNumeroConta().equals(numero))  // para cada c 'conta' verifica se c.getNumeroConta é igual ao numero informado
                .findFirst();   //retorna o primeiro elemento encontrado 'retorno é um Optional<ContaBancaria>'
    }



    
}
