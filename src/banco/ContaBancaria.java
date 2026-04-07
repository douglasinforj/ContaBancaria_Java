package banco;

/*
   Classe Base que representa um conta bancaria genérica.
   Demonstrando: Encapsulamento, construtores, método de instancias
 */

public class ContaBancaria {

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

    

    


}
