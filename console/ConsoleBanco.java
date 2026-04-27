package banco.console;

/**
 * ConsoleBanco será o Controlador principal do console interativo;
 * Cobre 100% dos recursos so Sistema Bancário.
 * Antes utilizamos Main para Testar os recursos que criamos de regra de negocios com as Classe, faremos aqui.
 * A Class Main irá somente chamar de entrada esta Classe ConsoleBanco
 *  */ 
import banco.Banco;
import banco.ContaBancaria;
import banco.ContaCorrente;
import banco.ContaInvestimento;
import banco.ContaPoupanca;
import banco.ContaPremium;

import java.util.List;
import java.util.Optional;


public class ConsoleBanco {

    private final Banco banco;

    public ConsoleBanco() {
        this.banco = new Banco("Bradesco");
    }

    //=========================================
    // PONTO DE ENTRADA
    //=========================================

    public void iniciar() {
        System.out.println("\n  Bem-vindo ao BRADESCO!");
        int opcao;
        do {
            Menu.menuPrincipal();
            opcao = ConsoleUtils.lerInt("  Escolha: ");
            switch (opcao) {
                case 1 -> abrirConta();
                
                case 0 -> System.out.println("\n  Até logo!");
                default -> System.out.println("\n Opção inválida.");
            }
        } while (opcao != 0);
    }

    //==========================================
    // 1. Abrir Conta
    //==========================================

    private void abrirConta() {
        Menu.cabecalho("ABRIR NOVA CONTA");
        Menu.menuTipoConta();
        int tipo = ConsoleUtils.lerInt("  Tipo:");
        if (tipo == 0) return;

        String titular = ConsoleUtils.lerTexto(" Nome do Titular  :");
        double saldoInicial = ConsoleUtils.lerDouble("  Saldo inicial (R$): ");

        try {
            ContaBancaria novaConta = switch (tipo) {
                case 1 -> criarContaCorrente(titular, saldoInicial);
                case 2 -> new ContaPoupanca(titular, saldoInicial);     //não tem métodos auxiliares que faz perguntas ao usuário
                case 3 -> criarContaInvestimento(titular, saldoInicial);
                case 4 -> criarContaPremium(titular, saldoInicial);
                default -> null;
            };

            if (novaConta == null) {
                System.out.println("\n  Tipo de conta inválida.");
                return;
            }

            banco.adicionarConta(novaConta);
            System.out.println("\n Conta aberta com sucesso!");
            System.out.println(" Tipo: " + novaConta.getTipoConta());
            System.out.println("Titular: " + novaConta.getTitular());
            System.out.println("Agência: " + novaConta.getAgencia());
            System.out.println("Conta: " + novaConta.getNumeroConta());
            System.err.printf("Saldo: R$ %.2f%n", novaConta.getSaldo());

        } catch( IllegalArgumentException e){
            System.out.println("\n Erro: " + e.getMessage());
        }
        ConsoleUtils.pausar();
    }



}
