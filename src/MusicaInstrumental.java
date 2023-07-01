import java.util.*;

public class MusicaInstrumental extends Musica {
    public String nomeArqPartitura;

    public MusicaInstrumental(Integer tag, Integer id, Integer duracaomin, Integer duracaoseg, String titulo, String autores, String data, String genero, String nomeArqPartitura){
        this.tag = tag;
        this.titulo = titulo;
        this.duracaomin = duracaomin;
        this.duracaoseg = duracaoseg;
        this.autores = autores;
        this.data = data;
        this.genero = genero;
        this.id = id;
    }


    public MusicaInstrumental(Integer tag) {
        super(1);
        this.setTag(1);
        System.out.println("Qual o nome do arquivo da partitura? ");
        String nomeArqPartitura = InputOutput.leituraConsoleString();
        this.nomeArqPartitura = nomeArqPartitura;
    }



    public String getNomeArqPartitura() {
        return nomeArqPartitura;
    }

    public void setNomeArqPartitura(String nomeArqPartitura) {
        this.nomeArqPartitura = nomeArqPartitura;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "(MusicaInstrumental) " + s + ", Nome do Arquivo da Partitura: " + nomeArqPartitura + "\n";
    }




}