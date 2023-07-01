import java.util.*;

public class MusicaCancao extends Musica {
    private String letra;
    private String letraString;


    public MusicaCancao(Integer tag) {
        super(1);
        System.out.println("Qual o arquivo da Letra? ");
        String letra = InputOutput.leituraConsoleString();
        this.setTag(2);
        this.letra = letra;
        this.letraString = InputOutput.leituraArquivoTexto(letra);
    }


    public MusicaCancao(Integer tag, Integer id, Integer duracaomin, Integer duracaoseg, String titulo, String autores, String data, String genero, String letra){
        this.tag = tag;
        this.titulo = titulo;
        this.duracaomin = duracaomin;
        this.duracaoseg = duracaoseg;
        this.autores = autores;
        this.data = data;
        this.genero = genero;
        this.id = id;
        this.letra = letra;
        this.letraString = "0";
        System.out.println("construtor 1");
    }



    @Override
    public String toString() {
        String s = super.toString();
        return "(Cancao) " + s + ", Caminho da letra: " + letra + ", \nLetra em String: " + letraString + "\n";
    }
}