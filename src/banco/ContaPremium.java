package banco;

/**
 *  Conta Premium  - Herda ContaCorrente e implementa Tributavel
 *  Demonstra:
 *  - Herança multipla de comportamento via Interface
 *  - Composição de funcionalidade
 */

public class ContaPremium extends ContaBancaria implements Tributavel{

    private double bonusAnual;

    public ContaPremium(String titula, double saldoInicial) {
        super(titulo, saldoInicial);
        this.bonusAnual = 0;
    }


}
