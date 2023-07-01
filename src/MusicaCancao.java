import java.util.*;

public class MusicaCancao extends Musica {
    private String letra;
    private String letraString;

    public MusicaCancao() {
        super();
        System.out.println("Qual o arquivo da Letra? ");
        String letra = InputOutput.leituraConsoleString();
        this.letra = letra;
        this.letraString = InputOutput.leituraArquivoTexto(letra);
    }


    @Override
    public String toString() {
        String s = super.toString();
        return "(Cancao) " + s + ", Caminho da letra: " + letra + ", \nLetra em String: " + letraString;
    }
}