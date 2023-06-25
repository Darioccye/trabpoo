// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
        Criptografia inicializa = new Criptografia();//Preciso dele para comecar a ler e usar o sistema

        Controle controle = new Controle(inicializa);//Controle usado pelo usuario comum ou usuario administrador

        UsuarioComum user = new UsuarioComum(controle);
        UsuarioAdministrador admin = new UsuarioAdministrador(controle);
        //Quando quiser autenticar e receber o usuario, instanciar o objeto respectivo (comum ou administrador), e fazer suas manipulacoes
    }
}