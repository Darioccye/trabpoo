import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Criptografia {


    private SecretKey chave;
    private Cipher cipher;

    Criptografia(SecretKey chave, String cipher) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.chave = chave;
        this.cipher = Cipher.getInstance(cipher);
    }

    void encriptar(String content, String file) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] iv = cipher.getIV();

        try (
                FileOutputStream fileOut = new FileOutputStream(file);
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)
        ) {
            fileOut.write(iv);
            cipherOut.write(content.getBytes());
        }

    }

    String desencriptar(String file) throws InvalidAlgorithmParameterException, InvalidKeyException, IOException {

        String mensagem;

        try (FileInputStream fileIn = new FileInputStream(file)) {
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, chave, new IvParameterSpec(fileIv));

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
                mensagem = sb.toString();
            }

        }
        return mensagem;
    }

}
