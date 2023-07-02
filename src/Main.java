// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        Criptografia inicializa = new Criptografia();//Preciso dele para comecar a ler e usar o sistema

        Controle controle = new Controle(inicializa);//Controle usado pelo usuario comum ou usuario administrador

        UsuarioComum user = new UsuarioComum(controle);
        UsuarioAdministrador admin = new UsuarioAdministrador(controle);
      //  admin.adicionaMusica();
        ColecaoMusicas colecao = new ColecaoMusicas();
        colecao.setColecao(InputOutput.leituraBinarioColecao("src/playlist/784512.txt"));
        System.out.println(colecao);
        /*-+-+-+-+-+-+-+-+-+Login e senha da conta principal do sistema+-+-+-+-+-+-+-+-+-
         * login: admin
         * senha: fromIncludeImport01
         */

         
    }
}