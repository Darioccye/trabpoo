import java.util.*;

public class ColecaoMusicas {
    private int numMusicas;
    private ArrayList<Musica> colecao;

    public ColecaoMusicas(){
        numMusicas = 0;
        colecao = new ArrayList<Musica>();
    }

    public int getNumMusicas(){
        return numMusicas;
    }

    public void setNumMusicas(int numMusicas) {
        this.numMusicas = numMusicas;
    }

    public ArrayList<Musica> getColecao() {
        return colecao;
    }

    public void setColecao(ArrayList<Musica> colecao) {
        this.colecao = colecao;
    }

    @Override
    public String toString() {
        return "ColecaoMusicas{" +
                "numMusicas=" + numMusicas +
                ", colecao=" + colecao +
                '}';
    }
}
