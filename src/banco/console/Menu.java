package banco.console;

/**
 * Responsável por exibir todos os menus do sistema
 * Não faz lógica - só apresenta opções na tela 
 * Separar a "Tela" da "Logica" é o primeiro passo do padrão MVC 
 * Será chamado pelo "ConsoleBanco"
 */


public class Menu {

    private Menu();

    public static void menuPrincipal() {
        System.out.println("\n=======================================");
        System.out.println("||               Bradesco              ||");
        System.out.println("||=====================================||");
        System.out.println("||   1. Abrir nova conta               ||");
        System.out.println("||   2. Acessar conta existente        ||");
        System.out.println("||   3. Listar todas as contas         ||");
        System.out.println("||   4. Patrimônio total do banco      ||");
        System.out.println("||   0. Sair                           ||");
        System.out.println("=========================================");
    }

    public static void menuTipoConta() {
        System.out.println("\n Tipo de conta:");
        System.out.println("1. Conta Corrente");
        System.out.println("2. Conta Poupança");
        System.out.println("3. Conta Investimento");
        System.out.println("4. Conta Premium");
        System.out.println("0. Voltar");
    }

    public static void menuOperacoes(String titular, String tipoConta){
        System.out.println("=====================================");
        System.out.printf("||%-33s||%n", titular);
        System.out.printf("||%-33s||%n", tipoConta);
        System.out.println("||   1. Depositar                  ||");
        System.out.println("||   2. Sacar                      ||");
        System.out.println("||   3. Transferir                 ||");
        System.out.println("||   4. Ver extrato completo       ||");
        System.out.println("||   5. Operações especiais        ||");
        System.out.println("||   6. Consultar dados da conta   ||");
        System.out.println("||   7. Alterar titular            ||");
        System.out.println("||   0. Voltar ao menu principal   ||");
        System.out.println("=====================================");



    }



}
