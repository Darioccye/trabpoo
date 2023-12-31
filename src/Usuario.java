import java.util.Collection;

//import java.io.File;
public abstract class Usuario {
    protected String nome;
    protected String identificador;//Padrao sem autenticar = "null"
    protected String login;
    protected String senha;
    protected Collection<Musica> musicas;
    protected Controle meuControle;

    public Musica buscaMusica(String nome){
        for(Musica m:this.musicas){
            if(m.getTitulo().compareTo(nome) == 0){
                return m;
            }
        }
        return null;
    };



    public void removeMusica(int id){}

    public void adicionaMusica(Musica musica) {}

    public abstract void excluirConta();
    public abstract void excluirConta(String login);

    public abstract void criarConta(String nome, String login, String senha);

    public Collection<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(Collection<Musica> musicas) {
        this.musicas = musicas;
    }

    public void visualizaMusica(Musica musica){
        if(musica == null){
            System.out.println("Sem musica a visualizar!");
            return;
        }
        System.out.println(musica);
    };


    public void autenticar(String login, String senha){
        if(this.identificador != "null"){
            System.out.println("Ja esta autenticado!");
            return;
        };

        this.identificador = this.meuControle.autenticarConta(login, senha);
        if(identificador == "null"){
            System.out.println("Nao autenticado!");
            return;
        };
        this.login = login;
        this.senha = senha;
        this.musicas = InputOutput.leituraBinarioColecao("src/playlist/"+identificador+".txt");
        System.out.println("Autenticado!");

        //Puxar nossa playlist da pasata playlist
    };
    

}
