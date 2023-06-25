//import javax.annotation.processing.Generated;

import java.io.File;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UsuarioAdministrador extends Usuario{

    public void adicionaMusica(){};
    public void removeMusica(){};
    public void buscaMusica(String titulo){};
    public void atualizaMusica(){};
    public void adicionaUsuario(){};
    public void removeUsuario(){};
    public void buscaUsuario(String login){};

    public UsuarioAdministrador(Controle meuControle){
        this.nome = null;
        this.identificador = "null";//Padrao sem autenticar = "null"
        this.login = null;
        this.senha = null;
        this.meuControle = meuControle;
    };

    public void excluirConta(){//return;
    };
    public void excluirConta(String email){
        this.meuControle.excluirConta(email);
    };

    public void criarConta(String nome, String login, String senha){
        String caminho = "security/c_"+login+".txt";
        File file = new File(caminho);
        if(file.exists()){
            System.out.println("Usuario ja registrado!");
            return;
        };
        String id_user = this.meuControle.gerarID();

        SecretKey secretKey = null;

        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (Exception e) {
            // Tratar erros
        }

        this.meuControle.criarConta(secretKey, login, id_user, senha);
        //Inicializar a playslist ou criar aqui em memoria principal------------------------------------------------------Dario
    };

}
