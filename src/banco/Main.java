package banco;



/**
 * APP CONTA BANCARIA
 * 
 * Conceitos Treinados
 * - Classe e Objetos
 * - Encapsulamento (private, getters/setters)
 * - Herança (ContaBancaria <-- ContaCorrente <--ContaPremium)
 * - Polimorfismo (sacar(), getTipoConta())
 * - Abstração (classe abstrata ContaBancaria)
 * - Intefaces (tributavel) contratos
 * - Tratamento de Exceções
 * - Coleções (List, stream, Optional)
 */

public class Main{
    
    public static void main(String[] args) {

        System.out.println("\n======================================");
        System.out.println("           APP CONTA BANCARIA");
        System.out.println("======================================");
        
        //1 - Criando o Banco
        Banco banco = new Banco("Bradesco");
        //System.out.printf("Banco: %s", banco.getName());

        //2 - Criando contas (polimorfismo)
        ContaCorrente cc = new ContaCorrente("Alice Silva", 1500);
        ContaPoupanca cp = new ContaPoupanca("Bob Santos", 3000.0);
        ContaInvestimento ci  = new ContaInvestimento("Carol Dias", 10000.00, 1.5, 12.0);
        ContaPremium prm = new ContaPremium("Daniel Costa", 5000.0);

        banco.adicionarConta(cc);
        banco.adicionarConta(cp);
        banco.adicionarConta(ci);
        banco.adicionarConta(prm);

        banco.listarContas();


    }

}
