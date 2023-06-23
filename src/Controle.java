import java.io.*;
import java.util.Base64;
//import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class Controle {
    private SecretKey chave_SecretKey;
    private Criptografia tradutor;
    
    public Controle(){
        
    };

    /*public static Scanner teclado(){
        Scanner teclado = new Scanner(System.in);
        return teclado;
    };*/


    public String autenticarUsuario(String email, String senha){
        String caminho = "security/c_"+email+".txt";//Chave criptografada

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
            Criptografia traducao = new Criptografia(chave_SecretKey, "AES");
            tradutor = traducao;
        }catch(Exception e){
            //Tratamento de erros do codigo.
        };
        

        String id_user = "null";
        caminho = "security/i_"+email+".txt";

        try{
            id_user = tradutor.desencriptar(caminho);
        }catch(Exception e){
            //Tratamento de erros do codigo.
        };

        String senhaComp = "null";
        caminho = "security/"+id_user+".txt";

        try{
            senhaComp = tradutor.desencriptar(caminho);
            if(senhaComp.compareTo(senha) == 0){
                return id_user;//Retornando o id do usuario
            };
        }catch(Exception e){
            //Tramento de erros do codigo.
        };

        return "false";
        
    };





    public void excluirConta(String email){//Fazer o Override no UsuarioAdministrador para que ele passe como parametro o ID do usuario a ser excluido
        String c = "security/c_"+ email+".txt";
        String i = "security/i_"+ email+".txt";


        String chave_acesso = "null";
        String myId = "null";
        try{
            FileReader bit_bit = new FileReader(c);
            BufferedReader buffer = new BufferedReader(bit_bit);
            String temporaria = buffer.readLine();
            buffer.close();

            chave_acesso = temporaria;
        }catch(IOException exception){
            System.out.println("Erro ao ecluir o arquivo");
        };

        //Codificando nossa chave para ler os seguintes acessos no tradutor
        byte[] decodedKey = Base64.getDecoder().decode(chave_acesso);
        SecretKey chave_temp  = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        try{
            Criptografia temp = new Criptografia(chave_temp, "AES");
            myId = temp.desencriptar(i);
        }catch(Exception e){
            //Tratar erros
        };
        File file = new File(c);//Exclusao da conta do usuario
        boolean result = file.delete();

        file = new File(i);//Exclusao da conta do usuario
        result = file.delete();

        file = new File(file, "security/"+myId+".txt");
        result = file.delete();
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