package banco;

/* 
    - Inteface que representa contas sujeitas a tributação
    - Didática demonstrada: interface, polimorfismo por interface
*/

public interface Tributavel {

    double ALIQUOTA_IOF = 0.0038; // 0,38%

    double calcularImposto();

    default String descricaoTributo(){
        return String.format("IOF calculado: R$ %.2f (alíquota: %.2f%%)", calcularImposto(), ALIQUOTA_IOF * 100);
    }
}



