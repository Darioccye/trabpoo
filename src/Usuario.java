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

    public void removeMusica(Musica musica){
        if(this.musicas.contains(musica)) {
            this.musicas.remove(musica);
        }
    };
    public void adicionaMusica(Musica musica) {
        if (!this.musicas.contains(musica)) {
            this.musicas.add(musica);
        }
    }

    public abstract void excluirConta();
    public abstract void excluirConta(String login);

    public abstract void criarConta(String nome, String login, String senha);

    public Collection<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(Collection<Musica> musicas) {
        this.musicas = musicas;
    }

    public void autenticar(){
        if(this.identificador != "null"){
            System.out.println("Ja esta autenticado!");
            return;
        };
        //Entradas de login e senha a fazer antes de passar null---------------------------------------------FABIO
        this.identificador = this.meuControle.autenticarConta(login, senha);
        if(identificador == "null"){
            System.out.println("Nao autenticado!");
            return;
        };
        System.out.println("Autenticado!");
    };
    

}
