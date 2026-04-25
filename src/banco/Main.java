package banco;
 
import banco.console.ConsoleBanco;
 
/**
 * Ponto de entrada do sistema.
 * Agora delega tudo para ConsoleBanco — Main fica com 1 responsabilidade só.
 */
public class Main {
 
    public static void main(String[] args) {
        ConsoleBanco console = new ConsoleBanco();
        console.iniciar();                              //será chamada em ConsoleBanco
    }
}