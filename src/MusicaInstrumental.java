import java.util.*;

public class MusicaInstrumental extends Musica {
    public String nomeArqPartitura;

    public MusicaInstrumental() {
        super();
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
        return "MusicaInstrumental{" + s + "nomeArqPartitura=" + nomeArqPartitura + '}';
    }




}