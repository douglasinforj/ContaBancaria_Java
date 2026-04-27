package banco;

/**
 *  Conta Premium  - Herda ContaCorrente e implementa Tributavel
 *  Demonstra:
 *  - Herança multipla de comportamento via Interface
 *  - Composição de funcionalidade
 *  - OBS.: Esta conta é originári da conta corrente ou seja contaCorrente com elevação de Premium
 */

public class ContaPremium extends ContaCorrente implements Tributavel{     //Atenção a Herança

    private double bonusAnual;

    public ContaPremium(String titulo, double saldoInicial) {
        super(titulo, saldoInicial, 2000.0);     //Informando o limite para ContaPremium, herdando de ContaCorrente
        this.bonusAnual = 0;
    }

    /**
     * Calcula IOF com base no saldo atual
     */
    @Override
    public double calcularImposto() {
        return saldo * ALIQUOTA_IOF;
    }

    /**
     * Crédito de bônus anual (cachback simulado)
     */
    public void creditarBonus(double percentualDoSaldo){
        bonusAnual = saldo * (percentualDoSaldo / 100.0);
        saldo += bonusAnual;
        System.out.printf("[BÔNUS PREMIUM] R$ %.2f creditados (%.1f%% do saldo).%n", bonusAnual, percentualDoSaldo);

    }

    @Override
    public String getTipoConta(){
        return "CONTA PREMIUM";
    }

    @Override
    public void exibirExtrato(){
        super.exibirExtrato();    //Herdado os dados de Conta Corrente(Atenção)
        System.out.println("============Dados Premium=================");
        System.out.println("  " + descricaoTributo());        //interface Tributavel
        System.out.printf("  Último bônus:    + R$ %.2f%n", bonusAnual);
        System.out.println("============================================");
    }



}
