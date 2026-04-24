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

        //3 - Operações na Conta Corrente
        secao("CONTA CORRENTE - Alice");
        cc.depositar(500.0);
        cc.sacar(200.00);
        cc.sacar(1900.00);
        cc.exibirExtrato();

        // 4 - Operação na Poupança
        secao("CONTA PAUPANÇA - Bob");
        cp.depositar(1000.0);
        cp.sacar(500.0);
        cp.aplicarRendimento();   //simulando a virada de mês
        cp.exibirExtrato();

        //5 - Operação na Conta Investimento
        secao("CONTA INVESTIMENTO - Carol");
        ci.depositar(2000.0);
        ci.processarMes();
        System.out.printf("Projeto 12 meses: R$ %.2f%n", ci.projetarSaldo(12));
        ci.exibirExtrato();

        //6 - Conta Premium + Interface Tributável
        secao("CONTA PREMIUM - Daniel");
        prm.depositar(3000.0);
        prm.creditarBonus(2.0);
        System.out.println(prm.descricaoTributo()); //metodo da interface (contrato)
        prm.exibirExtrato();

        //7 - Transferência Entre Contas
        secao("TRANSFERÊNCIA ENTRE CONTAS");
        cc.transferir(300.00, cp);

        //8 Polimorfismo - laço com tipo abstrato
        secao("Sacar R$ 100 em todas as contas");
        for (ContaBancaria conta : banco.getContas()) {
            try {
                conta.sacar(100.0);
                System.out.printf(" %-20s -> saldo: R$ %.2f%n", conta.getTipoConta(), conta.getSaldo());
            } catch (IllegalStateException e) {
                System.out.printf("  %-20s -> ERRO: %s%n", conta.getTipoConta(), e.getMessage());
            }
        }
    }

    private static void secao(String titulo) {
        System.out.println("\n=========================================");
        System.out.println(" " + titulo);
        System.out.println("========================================");
    }

}
