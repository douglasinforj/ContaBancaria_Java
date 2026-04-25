package banco.console;

/**
 * ConsoleBanco será o Controleador principal do console interativo;
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
        carregarDadosIniciais();
    }



    //=========================================
    // DADOS INICIAIS
    //=========================================
    private void carregarDadosIniciais(){
        banco.adicionarConta(new ContaCorrente("Alice", 1500.00));
        banco.adicionarConta(new ContaPoupanca(null, 0));
        banco.adicionarConta(new ContaInvestimento("Carol Dias", 10000.00, 1.5, 12));
        banco.adicionarConta(new ContaPremium("Daniel Costa", 5000.00));
        System.out.println(" 4 contas de exemplo carregadas. Voce pode adicionar mais.");
    }
}
