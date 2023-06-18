import java.util.*;


public abstract class Musica {
    private static int numMusicas = 0;
    private int id;
    private String titulo;
    private Duracao duracao;
    private String autores;
    private Date data;
    private String genero;

    public Musica(String titulo, Duracao duracao, String autores, Date data, String genero) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.autores = autores;
        this.data = data;
        this.genero = genero;
        numMusicas++;
        this.id = numMusicas;
    }

    public static int getNumMusicas() {
        return numMusicas;
    }

    public static void setNumMusicas(int numMusicas) {
        Musica.numMusicas = numMusicas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Duracao getDuracao() {
        return duracao;
    }

    public void setDuracao(Duracao duracao) {
        this.duracao = duracao;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Musica{" + "titulo=" + titulo + ", duracao=" + duracao + ", autores=" + autores + ", data=" + data + ", genero=" + genero + '}';
    }
}
