package banco;

/**
 * Conta Corrente - herda de Conta Corrente.
 * Demonstra: herança, override, atributos extra da classe (limite cheque especial)
  */

public class ContaCorrente  extends ContaBancaria{

    //Atributo para tratar do limite do cheque especial
    private double limiteEspecial;



    //Método construtor completo ContaCorrente
    public ContaCorrente(String titular, double saldoInicial, double limiteEspecial){
        //chamando atributos da classe pai
        super(titular, saldoInicial);
        if (limiteEspecial < 0){
            throw new IllegalArgumentException("Limite especial não pode ser negativo.");
        }
        this.limiteEspecial = limiteEspecial;
    }

    /**
     * Método construtor como sobrecargar(Overload), permitido em java para evitar repetir código
     * Você pode criar a conta de duas formas: completa ou simplificada — e a simplificada usa um valor padrão por baixo.
     * Importante que os parametros seja diferentes
    */
    public ContaCorrente(String titular, double saldoInicial){
        this(titular, saldoInicial, 500.0);          //Limite padrão (limiteEspecial) 
    }


}
