package banco.console;

import java.util.InputMismatchException;    //Valida tipo digitado x tipo esperado pelo Scanner
import java.util.Scanner;

/**
 * Utilitário para leitura segura do console
 * Centraliza toda interação com Scanner
 * Nenhuma outra classe precisa se preocupar com InputMismatchException ou buffer sujo
 */

public class ConsoleUtils {

    private static final Scanner scanner = new Scanner(System.in);
    
    //Constutor privado - classe utilizatária, não instancia
    private ConsoleUtils(){

    }

    /* Lê um interio com mensgaem. Repete até o usuário digitar número válido */
    public static int lerInt(String mensagem){
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = scanner.nextInt();
                scanner.nextLine();            //Limpando o buffer após nextInt
                return valor;
            }catch(InputMismatchException e){
                scanner.nextLine();               //descarta entrada errada
                System.out.println(" gite apenas números inteiros");
            } 
        }
    }

    /* Lê double com Mensagem. Repete até o valor válido e positivo */
    public static double lerDouble(String mensagem){
        while (true) {
            try{
                System.out.print(mensagem);
                String entrada = scanner.nextLine().replace(",", "."); //aceita tanto o ponto quanto a virgula
                double valor = Double.parseDouble(entrada);                                 //garante a conversão
                if(valor < 0){
                    System.out.println("Digite um valor positivo.");
                    continue;
                }
                return valor;
            }catch(NumberFormatException e){
                System.out.println("Valorinválido. Use números (ex: 150.00 ou 150.0)");

            }
        }
    }

    /* Lê uma String não vazia com mensagem (campo Obrigatório) */
    public static String lerTexto(String mensagem){
        while (true){
            System.out.print(mensagem);
            String texto = scanner.nextLine().trim();   //Trim remove espaços em branco no inicio e no fim
        }
    }


}
