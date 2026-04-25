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

    
}
