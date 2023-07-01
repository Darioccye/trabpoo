import java.util.*;

public class ColecaoMusicas {
    private int numMusicas;
    private Collection<Musica> colecao;

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

    public Collection<Musica> getColecao() {
        return colecao;
    }

    public void setColecao(Collection<Musica> colecao) {
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
