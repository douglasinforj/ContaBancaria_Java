package banco;

/**
 *  Conta Premium  - Herda ContaCorrente e implementa Tributavel
 *  Demonstra:
 *  - Herança multipla de comportamento via Interface
 *  - Composição de funcionalidade
 */

public class ContaPremium extends ContaBancaria implements Tributavel{

    private double bonusAnual;

    public ContaPremium(String titulo, double saldoInicial) {
        super(titulo, saldoInicial);
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



}
