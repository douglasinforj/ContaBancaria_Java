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

    public void iniciar(){
        System.out.println("\n Bem-Vindo ao Bradesco!");
        int opcao;
        do {
            Menu.menuPrincipal();
            //opcao = ConsoleUtils.lerInt(" Escolha: ");
            switch (opcao) {
                case 1 -> abrirConta();
                case 0 -> System.out.println("\n Até Logo!");
                default -> System.out.println("\n Opção inválida");

            }
        } while ( opcao != 0);
    }


}
