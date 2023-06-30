import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
//import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Criptografia {

    private SecretKey chave;
    private Cipher cipher;//"AES"

    Criptografia(SecretKey chave, String cipher) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.chave = chave;
        this.cipher = Cipher.getInstance(cipher);//Qual tipo de criptografia vou usar, passar como parametro o modelo
    };
    
    Criptografia() throws NoSuchAlgorithmException, NoSuchPaddingException{
        this.chave = KeyGenerator.getInstance("AES").generateKey();
        this.cipher =  Cipher.getInstance("AES/CBC/PKCS5Padding");
    };

    public void encriptar(String senha, String id, String login) throws InvalidKeyException, IOException{//Aqui ele encripta somente uma unica string em um unico arquivo
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream("security/"+id+".txt");//Escrita da senha
        CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);
            cipherOut.write(senha.getBytes());
        }

        String encodedKey = Base64.getEncoder().encodeToString(chave.getEncoded());
        InputOutput.escritaCriptografia("security/c_"+login+".txt", encodedKey);//Escrita da chave

        try (FileOutputStream fileOut = new FileOutputStream("security/i_"+login+".txt");//Escrita do id
        CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);
            cipherOut.write(id.getBytes());
        }
    };

    public String desencriptar(String email, int escolha) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException, InvalidAlgorithmParameterException {
        String content;
        String encodedKey = InputOutput.leituraCriptografia("security/c_"+email+".txt");//Chave
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

        try (FileInputStream fileIn = new FileInputStream("security/i_"+ email +".txt")) {//Descriptografou a chave
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
                ) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }

        }
        if(escolha == 1) return content;

        try (FileInputStream fileIn = new FileInputStream("security/"+ content +".txt")) {//Descriptografou o ID
            content = null;
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
                ) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }

        }

        return content;
    }

};
