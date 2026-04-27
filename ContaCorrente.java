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

    /**
     * Método Abstract herdado de ContaBancaria, utilizaremos o @override para sobrescrever
     * Saque com uso de cheque especial
     * Cliente pode sacar até saldo + limiteEspecial
     */

    @Override
    public void sacar(double valor){
        if (valor <=0){
            throw new IllegalArgumentException("Valor de saque deve ser positivo");
        }
        double saldoDisponivel = saldo + limiteEspecial;
        if (valor > saldoDisponivel){
            throw new IllegalStateException(
                String.format("Saldo insuficiente. Disponível (com limite): R$ %.2f", saldoDisponivel)
            );
        }
        saldo -= valor;
        System.out.printf("[SAQUE CC] R$ %.2f sacado. Novo saldo: R$ %.2f%n", valor, saldo);
        if(saldo < 0){
            System.out.printf("[AVISO] Você está usando R$ %.2f do cheque especial.%n", Math.abs(saldo));
        }

    }

    @Override
    public String getTipoConta() {
        return "CONTA CORRENTE";
    }

    @Override
    public void exibirExtrato(){  //Metodo vindo por herança do pai,
        super.exibirExtrato();    //Chamando os comportamento na classe pai
        System.out.printf(" Limite Especial: R$ %.2f%n ", limiteEspecial);   //Adicionando limiteEspcial que é especifica desta classe.
    }

    //Getters e Setters

    //Encapsular a variavel LimiteEspecial
    public double getLimiteEspecial() { return limiteEspecial; }


    //Ecapsulamento e metodo para alterar o valor do Limite Especial
    public void setLimiteEspecial(double novoLimite){
        if (novoLimite < 0){
            throw new IllegalArgumentException("Limite não pode ser negativo.");
        }
        this.limiteEspecial = novoLimite;
    }


}
