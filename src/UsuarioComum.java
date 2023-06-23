import java.io.File;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UsuarioComum extends Usuario{

    public void criaColecao(){};
    public void adicionaMusica(){};
    public void removeMusica(){};
    public void visualizaMusica(){};
    //public void cadastraSelf(){};//Estou fazendo o cadastrar conta abaixo
    //public void removeSelf(){}//Estou comentando e fazendo o metodo abstrato que e diferente aos dois usuarios

    public void excluirConta(){
        this.meuControle.excluirConta(this.login);

        this.nome = null;
        this.identificador = null;
        this.login = "null";
        this.senha = null;
        this.meuControle = null;
    };
    public void excluirConta(String email){//return;
    };

    public void criarConta(String nome, String login, String senha){
        if(this.login != "null"){
            System.out.println("Ja esta registrado no sistema");
            return;
        };
        String caminho = "security/c_"+login+".txt";
        File file = new File(caminho);
        if(file.exists()){
            System.out.println("Usuario ja registrado!");
            return;
        };
        this.login = login;
        this.nome = nome;
        this.identificador = this.meuControle.gerarID();

        SecretKey secretKey = null;

        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (Exception e) {
            // Tratar erros
        }

        this.meuControle.criarConta(secretKey, this.login, this.identificador, senha);
        //Inicializar a playslist ou criar aqui------------------------------------------------------Dario
    };


}