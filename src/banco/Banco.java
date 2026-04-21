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

    
}
