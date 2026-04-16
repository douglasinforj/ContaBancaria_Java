package banco;

/**
 * Conta Poupança - herda de Conca Bancaria
 * Desmonstra: herança, lógica de negócio específica (rendimento), restrição de saque (Limites de saques)
 */

public class ContaPoupanca extends ContaBancaria {

    private static final double TAXA_RENDIMENTO = 0.005;   // TR 5% ao mês
    private int saquesMensais;
    private static final int LIMITE_SAQUES_MENSAIS = 4;

    public ContaPoupanca(String titular, double saldoInicial) {
        super(titular, saldoInicial);
        this.saquesMensais = 0;
    }

    /**
     * Saque com limite de quantidade mensal (regra simplificada de poupança)
     */

    @Override
    public void sacar(double valor){
        if (valor <= 0){
            throw new IllegalArgumentException("Valor de saque deve ser positivo");
        }
        if (saquesMensais >= LIMITE_SAQUES_MENSAIS){
            throw new IllegalStateException(
                "Limite de " + LIMITE_SAQUES_MENSAIS + " saques mensais atingidos"
            );
        }
        if (valor > saldo){
            throw new IllegalStateException(
                String.format("Saldo insuficiente. Saldo atual: R$ %.2f", saldo)
            );
        }
        saldo -= valor;
        saquesMensais++;
        System.out.printf("[SAQUES CP] R$ %.2f sacado. Saldo: R$ %.2f | Saques este mês: %d/%d%n",
            valor, saldo, saquesMensais, LIMITE_SAQUES_MENSAIS
        );
    }


    /**
     * Aplica rendimentos mensal ao saldo
     * 
     */
    public void aplicarRendimento() {
        double rendimento = saldo * TAXA_RENDIMENTO;
        saldo += rendimento;
        saquesMensais = 0;      //reset mensal
        System.out.printf("[RENDIMENTO] R$ %.2f creditados. Novo saldo: R$ %.2f%n", rendimento, saldo);
    }

    @Override
    public String getTipoConta() {
        return "CONTA PUPANÇA";
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();       //exibi o comportamento do método direto da classe pai
        System.out.printf(" Taxa de Rendimento: %.1%% a.m.%.", TAXA_RENDIMENTO * 100);
        System.out.printf(" Saques este mês   : %d%d%n", saquesMensais, LIMITE_SAQUES_MENSAIS);
        System.out.println("========================================================");
    }

    //getters (encapsulamento)
    public int getSaquesMensais() { return saquesMensais; }



}
