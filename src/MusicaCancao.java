import java.util.*;

public class MusicaCancao extends Musica {
    private Letra letra;

    public MusicaCancao(String titulo, Duracao duracao, String autores, Date data, String genero, Letra letra) {
        super(titulo, duracao, autores, data, genero);
        this.letra = letra;
    }


    @Override
    public String toString() {
        String s = super.toString();
        return "Cancao{" + s + "letra=" + letra + '}';
    }
}