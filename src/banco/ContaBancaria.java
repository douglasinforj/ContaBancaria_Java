package banco;

/*
   Classe Base que representa um conta bancaria genérica.
   Demonstrando: Encapsulamento, construtores, método de instancias
   Deve ser declarada com abstract, por que obriga as filhas a implementarem metodos tido nelas como abstract também.
 */

public abstract class ContaBancaria {

    // private - Acesso restritor a propria classe, não sendo acesso diretamente
    // Static - Pertence a classe não ao objeto, existe uma única cópia na memória, Todos os objetos compatilham o mesmo valor.
    private static int contadorAgencia = 1000;

    // Campos pertence a classe, não podem ser acessadas diretamente de fora, nunca poderão ser alterados após se definidas
    private final String numeroConta;
    private final String agencia;

    //modificado protected - acesso permitido a própria classe, classe do mesmo package, Subclasses(herança), mesmo em outros pacotes
    protected String titular;                    
    protected double saldo;

    //Método construtor da classe
    public ContaBancaria(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank()){
            throw new IllegalArgumentException("Titular não pode ser vazio");
        }
        if (saldoInicial <0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo");
        }
        this.titular = titular;
        this.saldo = saldoInicial;
        this.agencia = String.valueOf(contadorAgencia++);    //convert int para String, adiciona contador++  1000 para 1001 e assim vai
        this.numeroConta = gerarNumeroConta();               //Criar uma método para gerar numero de conta
    }

    //Gerador simples de numero de conta
    private String gerarNumeroConta() {
        return String.format("%05d-%d", (int)(Math.random() * 99999), (int)(Math.random() * 9));
    }

    // Método abstrato - Cada tipo de conta tem sua própria lógica para sacar

    //Sacar
    public abstract void sacar(double valor);

    
    //Métodos concretos compartilhados

    //Depositar
    public void depositar(double valor){
        if(valor <=0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }
        saldo += valor;
        System.out.printf("[DEPOSITO] R$ %.2f depositado. Novo saldo: R$ %.2f%n", valor, saldo);
    }

    //Tranferir
    public void transferir(double valor, ContaBancaria destino) {      // ContaBancaria destino, permite instancia de Conta bancaria para acessar sacar(), depositar()
        this.sacar(valor);
        destino.depositar(valor);
        System.out.printf("[TRANSPARENCIA] R$ %.2f transferido para %s%n", valor, saldo);

    }

    public void exibirExtrato() {
        System.out.println("=========================================");
        System.out.println(" Extrato - " + getTipoConta());
        System.out.println("=========================================");
        System.out.println(" Titular : " + titular);
        System.out.println(" Agência : " + agencia);
        System.out.println(" Conta: " + numeroConta);
        System.out.printf(" Saldo : R$ %.2f%n", saldo);
        System.out.println("=========================================");
        
    }

    //Método abstrato para identificar o tipo da conta
    public abstract String getTipoConta();


    /** Encapsulamentos com Getters e Setters
     * Dados (variáveis) de um objeto não devem ser acessados diretamente, só com regras que definimos
     * Controke de acesso
     * Evitar etados invalidos
     * Evolução do código, podendo adicionar logica, como para validar
    */

    //Getters
    public String getNumeroConta() { return numeroConta; }
    public String getAgencia() { return agencia; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }


    //Setter com validação para Titular
    public void setTitular(String novoTitular) {
        if (novoTitular == null || novoTitular.isBlank()) {
            throw new IllegalArgumentException("Titular inválido.");
        }
        this.titular = novoTitular;
    }

    /**
     * Sobrescrevendo método padrão de superclass do Sistema "toString"
     * 
     * Quando alguém imprimir esse objeto, mostre um resumo formatado e legível com os dados principais da conta.
     */
    @Override  //indica que vamos sobrescrever um método existente de uma classe pai (herança)
    public String toString(){
        return String.format("[%s] Tituar: %s | Agência: %s | Conta %s | Saldo: R$ %.2f",
                getTipoConta(),
                getTitular(),
                getAgencia(),
                getNumeroConta(),
                getSaldo()
        );
    }


}
