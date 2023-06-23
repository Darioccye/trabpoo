import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
//import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;

public class Criptografia {

    private SecretKey chave;
    private Cipher cipher;//"AES"

    Criptografia(SecretKey chave, String cipher) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.chave = chave;
        this.cipher = Cipher.getInstance(cipher);//Qual tipo de criptografia vou usar, passar como parametro o modelo
    };

    public void encriptar(String content, String file) throws InvalidKeyException, IOException{//Aqui ele encripta somente uma unica string em um unico arquivo
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] iv = cipher.getIV();

        try (
                FileOutputStream fileOut = new FileOutputStream(file);
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)
        ) {
            fileOut.write(iv);
            cipherOut.write(content.getBytes());
        };
    };

    public String desencriptar(String file) throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {//Retorna a mensagem de um arquivo todo

        String mensagem;

        try (FileInputStream fileIn = new FileInputStream(file)) {//Tenta abrir o arquivo
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, chave, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
                ) {//Tenta ler o arquivo

                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    line = reader.readLine();
                    sb.append(line);
                    line = reader.readLine();
                };
                mensagem = sb.toString();
            };
        };
        return mensagem;
    };
};
