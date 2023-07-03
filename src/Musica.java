import java.io.Serializable;

public abstract class Musica implements Serializable {
    protected Integer tag;
    protected Integer id;
    protected Integer duracaomin;
    protected Integer duracaoseg;
    protected String titulo;
    protected String autores;
    protected String data;
    protected String genero;

    public Musica(){

    }

    public Musica(int Criar) {
        if(Criar == 1){
            System.out.println("ID da Nova Música: ");
            int ident = InputOutput.leituraConsoleint();
            System.out.println("Título da Música: ");
            String titulo = InputOutput.leituraConsoleString();
            System.out.println("Duração da Música, em segundos: ");
            int duracao = InputOutput.leituraConsoleint();
            int duracaomin = duracao/60;
            int duracaoseg = duracao - duracaomin*60;
            System.out.println("Autores da Música: ");
            String autores = InputOutput.leituraConsoleString();
            System.out.println("Data de lançamento (dd/mm/aa): ");
            String data = InputOutput.leituraConsoleString();
            System.out.println("Gênero da Música: ");
            String genero = InputOutput.leituraConsoleString();
            this.titulo = titulo;
            this.duracaomin = duracaomin;
            this.duracaoseg = duracaoseg;
            this.autores = autores;
            this.data = data;
            this.genero = genero;
            this.id = ident;
        }
    }

    public Musica(Integer tag, Integer id, Integer duracaomin, Integer duracaoseg, String titulo, String autores, String data, String genero){
        this.tag = tag;
        this.titulo = titulo;
        this.duracaomin = duracaomin;
        this.duracaoseg = duracaoseg;
        this.autores = autores;
        this.data = data;
        this.genero = genero;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracaomin() {
        return duracaomin;
    }

    public void setDuracaomin(Integer duracaomin) {
        this.duracaomin = duracaomin;
    }

    public Integer getDuracaoseg() {
        return duracaoseg;
    }

    public void setDuracaoseg(Integer duracaoseg) {
        this.duracaoseg = duracaoseg;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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
        return "ID: " + id + ", Título: " + titulo + ", Duração: " + duracaomin + " minutos e " + duracaoseg + " segundos" + ", Autores: " + autores + ", Data de Lançamento: " + data + ", Gênero: " + genero;
    }
}
