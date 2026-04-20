package banco;

/**
 * COnta Investimento herda ContaBancaria
 * Demonstra: herança, lógica de negócio com taxas, métodos adicionais
 */

public class ContaInvestimento extends ContaBancaria {

    private double taxaAdministracao;  // % sobre saldo ao mês
    private double rentabilidadeAnual; // % ao ano

    public ContaInvestimento(String titular, double saldoInicial, double taxaAdministracao, double rentabilidadeAnual){
        super(titular, saldoInicial);
        this.taxaAdministracao = taxaAdministracao;
        this.rentabilidadeAnual = rentabilidadeAnual;
    }

    /**
     * Saque com carência mínima de R$ 100 (regra simulada)
     */
    @Override
    public void sacar(double valor){
        if (valor <= 0){
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (valor > saldo){
            throw new IllegalStateException(
                String.format("Saldo insuficiente. Saldo atual: R$ %.2f", saldo)
            );
        }
        if ((saldo - valor) < 100.0 && saldo > 100.0) {
            throw new IllegalStateException(
                "Saldo mínimo de R$ 100,00 deve ser mantido na conta investimento."
            );
        }
        saldo -= valor;
        System.out.printf("[SAQUE CI] R$ %.2f sacado. Saldo restante: R$ %.2f%n",valor, saldo);
    }

    /**
     * Simula a rentabilidade mensal descontado a taxa de administração.
     */
    public void processarMes(){
        double rentMensal = rentabilidadeAnual / 12.0 / 100.0;
        double taxaMensal = taxaAdministracao / 12.0 / 100.0;
        double ganho = saldo * rentMensal;
        double taxa = saldo * taxaMensal;
        double liquido = ganho - taxa;
        saldo += liquido;
        System.out.printf("[INVESTIMENTO] Rentabilidade: +R$%.2f | Taxa adm: -R$ %.2f | Líquido: R$ %.2f", ganho, taxa, liquido );
        System.out.printf("Novo saldo R$ %.2f", saldo);
    }

    /**
     * Projetar o saldo após N Meses
     */
    public double projetarSaldo(int meses) {
        double rentMensal = rentabilidadeAnual / 12.0 / 100.0;
        double taxaMensal = taxaAdministracao / 12.0 / 100.0;
        double taxaLiquida = rentMensal - taxaMensal;
        return saldo * Math.pow(1 + taxaLiquida, meses);
    }

    @Override
    public String getTipoConta(){
        return "CONTA INVESTIMENTO";
    }

    @Override
    public void exibirExtrato() {
        super.exibirExtrato();
        System.out.printf(" Rentabilidade   : %.2f%% a.a%n",rentabilidadeAnual);
        System.out.printf(" Taxa de Admn: %.2f%% a.a%n", taxaAdministracao);
        System.out.printf(" Projeção 12 meses: R$: %.2f%n", projetarSaldo(12));
        System.out.println("=============================================");
    }
}
