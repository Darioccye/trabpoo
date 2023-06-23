import javax.annotation.processing.Generated;

public class UsuarioAdministrador extends Usuario{

    public void adicionaMusica(){};
    public void removeMusica(){};
    public void buscaMusica(String titulo){};
    public void atualizaMusica(){};
    public void adicionaUsuario(){};
    public void removeUsuario(){};
    public void buscaUsuario(String login){};

    public void excluirConta(){
        //return;
    };
    public void excluirConta(String email){
        meuControle.excluirConta(email);
    };

}
