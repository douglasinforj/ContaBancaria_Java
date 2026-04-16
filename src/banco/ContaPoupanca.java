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






}
