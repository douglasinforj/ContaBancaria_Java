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
                case 2 -> acessarConta();
                
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
        int tipo = ConsoleUtils.lerInt(" Tipo:");
        if (tipo == 0) return;

        String titular = ConsoleUtils.lerTexto("Nome do Titular  :");
        double saldoInicial = ConsoleUtils.lerDouble("Saldo inicial (R$): ");

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
            System.out.println("Tipo: " + novaConta.getTipoConta());
            System.out.println("Titular: " + novaConta.getTitular());
            System.out.println("Agência: " + novaConta.getAgencia());
            System.out.println("Conta: " + novaConta.getNumeroConta());
            System.err.printf("Saldo: R$ %.2f%n", novaConta.getSaldo());

        } catch( IllegalArgumentException e){
            System.out.println("\n Erro: " + e.getMessage());
        }
        ConsoleUtils.pausar();
    }


    //Opções de abertura de Contas

    private ContaCorrente criarContaCorrente(String titular, double saldo) {
        System.out.printf(" Limite especial padrao: R$ 500,00%n");
        boolean custom = ConsoleUtils.lerConfirmacao("Deseja definir um limite personalizado?");
        if (custom) {
            double limite = ConsoleUtils.lerDouble(" Limite especial (R$): ");
            return new ContaCorrente(titular, saldo, limite);
        }
        return new ContaCorrente(titular, saldo);
    }

    private ContaPremium criarContaPremium(String titular, double saldo) {
        System.out.printf("Limite especial padrao: R$ 2.000,00%n");
        boolean custom = ConsoleUtils.lerConfirmacao("Deseja definir um limite personalizado?");
        ContaPremium premium = new ContaPremium(titular, saldo);
        if (custom) {
            double limite = ConsoleUtils.lerDouble("Limite especial (R$): ");
            premium.setLimiteEspecial(limite);
        }
        return premium;

    }

    private ContaInvestimento criarContaInvestimento(String titular, double saldo) {
        System.out.println(" [Sugestao] Rentabilidade: 12% a.a | Taxa adm: 1,5% a.a");
        double rent = ConsoleUtils.lerDouble("Rentabilidade anual (%): ");
        double taxa = ConsoleUtils.lerDouble("Taxa de administracao (%): ");
        return new ContaInvestimento(titular, saldo, taxa, rent);
    }


    //===================================================
    // 2. Acessar Conta
    //===================================================

    private void acessarConta() {
        Menu.cabecalho("ACESSAR CONTA");
        System.out.println("  1. Buscar por numero da conta");
        System.out.println("  2. Buscar por nome do titular");
        System.out.println("  0. Voltar");
        int opcao = ConsoleUtils.lerInt("Escolha: ");

        ContaBancaria conta = switch (opcao) {
            case 1 -> buscarPorNumero();
            case 2 -> buscarPorTitular();
            default -> null;
        };
        if (conta != null) menuOperacoes(conta);
    }

    private ContaBancaria buscarPorNumero() {
        String numero = ConsoleUtils.lerTexto("NUmero da conat: ");
        Optional<ContaBancaria> resultado = banco.buscarPorNumeroConta(numero);
        if (resultado.isEmpty()) {
            System.out.println("\n Conta não encontrada");
            ConsoleUtils.pausar();
            return null;
        }
        return resultado.get();
    }

    //Vamos trata como se o Titular tem varias contas com o mesmo nome, ele poderá escolhar quais vai fazer as transações
    private ContaBancaria buscarPorTitular() {
        String nome = ConsoleUtils.lerTexto("Nome do Titular");
        List<ContaBancaria> contas = banco.buscarPorTitular(nome);

        if (contas.isEmpty()){
            System.out.println("\n Nenhuma conta encontrada para: " + nome);
            ConsoleUtils.pausar();
        }

        if (contas.size() == 1) return contas.get(0);

        System.out.println("\n " + contas.size() + " contas encontradas: ");
        for (int i = 0; i < contas.size(); ++i) {
            System.out.printf(" %d. [%-18s] Conta: %-9s | Saldo: R$ %.2f%n",
                i + 1,
                contas.get(i).getTipoConta(),
                contas.get(i).getNumeroConta(),
                contas.get(i).getSaldo());
        }
        //Escolha de contas caso tenha mais de uma
        int escolha = ConsoleUtils.lerInt("Qual conta deseja acessar? ");
        if (escolha < 1 || escolha > contas.size()){
            System.out.println("\n Opcao invalida.");
            return null;
        }
        return contas.get(escolha - 1);
    }

}
