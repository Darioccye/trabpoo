public class UsuarioComum extends Usuario{

    public void criaColecao(){};
    public void adicionaMusica(){};
    public void removeMusica(){};
    public void visualizaMusica(){};
    public void cadastraSelf(){};
    //public void removeSelf(){}//Estou comentando e fazendo o metodo abstrato que e diferente aos dois usuarios

    public void excluirConta(){
        this.meuControle.excluirConta(this.login);

        this.nome = null;
        this.identificador = null;
        this.login = "false";
        this.senha = null;
        this.meuControle = null;
    }
    public void excluirConta(String email){
        //return;
    };



}