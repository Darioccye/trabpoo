import java.io.*;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Random;

// Completa //

public class Controle {
    private SecretKey chave_SecretKey;
    private Criptografia tradutor;
    
    public Controle(Criptografia tradutor){
        this.tradutor = tradutor;
    };

    public String autenticarConta(String email, String senha){
        String caminho = "src/security/c_"+email+".txt";//Chave criptografada

        String chave_acesso = "a";
        try{
            FileReader bit_bit = new FileReader(caminho);
            BufferedReader buffer = new BufferedReader(bit_bit);
            String temporaria = buffer.readLine();
            buffer.close();

            chave_acesso = temporaria;
        }catch(IOException exception){
            System.out.println("Erro ao iniciar o arquivo");
        };

        //Codificando nossa chave para ler os seguintes acessos no tradutor
        byte[] decodedKey = Base64.getDecoder().decode(chave_acesso);
        this.chave_SecretKey  = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        //Iniciado o nosso tradutor que vai desinciptar os nossos logins
        //Toda vez que chamar este metodo, ele devera sumir com tradutor antigo(Com nova chave), e instanciar um novo para uso
        try{
            Criptografia traducao = new Criptografia(this.chave_SecretKey, "AES/CBC/PKCS5Padding");
            this.tradutor = traducao;
        }catch(Exception e){
            //Tratamento de erros do codigo.
        };
        

        String id_user = "null";

        try{
            id_user = tradutor.desencriptar(email, 1);
        }catch(Exception e){
            //Tratamento de erros do codigo.
        };

        String senhaComp = "null";

        try{
            senhaComp = tradutor.desencriptar(email, 2);
            if(senhaComp.compareTo(senha) == 0){
                return id_user;//Retornando o id do usuario
            };
        }catch(Exception e){
            //Tramento de erros do codigo.
        };
        return "null";
        
    };

    public void excluirConta(String email){
        String c = "src/security/c_"+ email+".txt";
        String i = "src/security/i_"+ email+".txt";

        String chave_acesso = InputOutput.leituraCriptografia(c);
        String myId = "null";

        //Codificando nossa chave para ler os seguintes acessos no tradutor
        byte[] decodedKey = Base64.getDecoder().decode(chave_acesso);
        SecretKey chave_temp  = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        try{
            Criptografia temp = new Criptografia(chave_temp, "AES/CBC/PKCS5Padding");
            myId = temp.desencriptar(email, 1);
        }catch(Exception e){
            //Tratar erros
        };
        File file = new File(c);//Exclusao da conta do usuario
        file.delete();

        file = new File(i);//Exclusao da conta do usuario
        file.delete();

        file = new File(file, "src/security/"+myId+".txt");//Exclusao do ID do usuario
        file.delete();
    };

    public void criarConta(SecretKey chave, String login, String id, String senha){
        Criptografia temp = null;
        try {
            temp = new Criptografia(chave, "AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            //Erros gerados
        }
        
        try{//Encriptar ID
            temp.encriptar(senha,id, login);//Ja escreve
        }catch(Exception e){
        };
        
        
    };

    public String gerarID(){
        Random aleatorio = new Random();
        File file;
        String caminho = "src/security/";

        while(true){
            int numero = aleatorio.nextInt();
            if(numero < 0) numero *= -1;
            if(numero == 0) continue;

            numero = numero % 1000;
            file = new File(caminho+numero+".txt");
            if(!file.exists()) return Integer.toString(numero);//Retornando o id criado em string
        }
    };
}



/*
--SecretKey para String:
// create new key
SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
// get base64 encoded version of the key
String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

--String para SecretKey:
// decode the base64 encoded string
byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
// rebuild key using SecretKeySpec
SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
 */