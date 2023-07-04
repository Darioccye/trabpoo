//import javax.annotation.processing.Generated;

import java.util.*;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class UsuarioAdministrador extends Usuario{

    public void atualizaMusica(Musica musica){
        boolean atualizou = false;
        for(Musica m:this.musicas){
            if(m.getId() == musica.getId()) {
                this.musicas.remove(m);
                this.musicas.add(musica);
                atualizou = true;
            }
        }
        if(atualizou) System.out.println("Música Atualizada!");
        else System.out.println("ID não existente na Coleção!");
    };


    public Musica criaMusica(){
        System.out.println("Música Instrumental (1) ou Canção (2)?");
        int tipoMusica = InputOutput.leituraConsoleint();
        Musica musica = null;
        if(tipoMusica == 1){
            musica = new MusicaInstrumental(1);
        }
        else if(tipoMusica == 2){
            musica = new MusicaCancao(2);
        }
        else{
            System.out.println("Opção não suportada.");
        }
        return musica;
    }

    public void adicionarMusica(){
        Musica novaMusica = criaMusica();
        System.out.println(novaMusica.toString());
        if(this.getMusicas().contains(novaMusica)){
            System.out.println("Música já cadastrada!");
        }
        else{
            this.getMusicas().add(novaMusica);
        }
        InputOutput.escritaBinarioMusica("src/playlist/784512.txt", novaMusica);
    }

    public void removeMusica(int id){
        Musica musica = null;
        boolean removeu = false;
        for(Musica m:this.musicas){
            if(m.getId() == id){
                musica = m;
                removeu = true;
            }
        }
        this.musicas.remove(musica);
        if(removeu) System.out.println("Música removida com sucesso");
        else System.out.println("ID não existente na Coleção");
        InputOutput.escritaBinarioColecao("src/playlist/784512.txt", this.musicas);

    };

    public String buscarUsuario(String login) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, BadPaddingException{
        String encodedKey = InputOutput.leituraCriptografia("src/security/c_"+login+".txt");//Chave
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        Criptografia desc = new Criptografia(originalKey, "AES/CBC/PKCS5Padding");
        return desc.desencriptar(encodedKey, 1);//Retorna o ID do usuario

    };

    public UsuarioAdministrador(Controle meuControle){
        this.nome = null;
        this.identificador = "null";
        this.login = null;
        this.senha = null;
        this.meuControle = meuControle;
        this.musicas = InputOutput.leituraBinarioColecao("src/playlist/784512.txt");
    };

    public void excluirConta(){//Vazio o metodo
    };
    public void excluirConta(String email){
        this.meuControle.excluirConta(email);
    };

    public void criarConta(String nome, String login, String senha){
        String caminho = "src/security/c_"+login+".txt";
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
        File fp = new File("src/playlist/"+id_user+".txt");
        try {
			fp.createNewFile();			
		} catch (IOException e) {
			e.printStackTrace();
		}
    };

}
