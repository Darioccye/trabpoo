import java.io.File;
import java.util.Collection;
import java.util.Scanner;
import java.io.IOException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class UsuarioComum extends Usuario{

    public void criaColecao(){

    };

    public void removeMusica(int id){
        Musica musica = null;
        for(Musica m:this.musicas){
            if(m.getId() == id){
                musica = m;
            }
        }
        this.musicas.remove(musica);
        System.out.println("Música removida");
        InputOutput.escritaBinarioColecao("scr/playlist/" + this.identificador + ".txt", this.musicas);
    }

    public void adicionarMusica(UsuarioAdministrador admin){
        Collection<Musica> principal = admin.getMusicas();
        System.out.println("Escolha um ID: ");
        System.out.println(principal);
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        boolean adicionou = false;
        for(Musica m:principal){
            if(m.getId() == i && !this.musicas.contains(m)){
                adicionou = this.musicas.add(m);
                if(adicionou){
                    System.out.println("Música Adicionada!");
                    InputOutput.escritaBinarioColecao("scr/playlist/" + this.identificador + ".txt", this.musicas);
                    break;
                }
            }
        }
        if(!adicionou) {
            System.out.println("ID não encontrado!");
        }
    }


    public UsuarioComum(Controle meuControle){
        this.nome = null;
        this.identificador = "null";//Padrao sem autenticar = "null"
        this.login = null;
        this.senha = null;
        this.meuControle = meuControle;
    };

    public void excluirConta(){
        this.meuControle.excluirConta(this.login);

        this.nome = null;
        this.identificador = "null";
        this.login = null;
        this.senha = null;
        //this.meuControle = null;
    };
    public void excluirConta(String email){//return;
    };

    public void criarConta(String nome, String login, String senha){
        if(this.identificador != "null"){
            System.out.println("Ja esta logado no sistema");
            return;
        };
        String caminho = "src/security/c_"+login+".txt";
        File file = new File(caminho);
        if(file.exists()){
            System.out.println("Usuario ja registrado!");
            return;
        };
        this.login = login;
        this.nome = nome;
        this.identificador = this.meuControle.gerarID();
        this.senha = senha;

        SecretKey secretKey = null;

        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (Exception e) {
            // Tratar erros
        }

        this.meuControle.criarConta(secretKey, this.login, this.identificador, senha);
        File arquivo = new File("src/playlist/"+identificador+".txt");
        try {
			arquivo.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        System.out.println("Registrado com sucesso no sistema!");

    };


}