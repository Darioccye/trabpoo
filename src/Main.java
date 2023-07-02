// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        int tipo = 0;
        while (tipo != 1 && tipo != 2) {
            System.out.println("Qual o tipo de Usuário?\nComum (1) ou Administrador (2)?");
            tipo = scanner.nextInt();

/*
            if(tipo == 1){
                System.out.println("Qual o login?");
                String login = scanner.nextLine();
                System.out.println("Qual a senha?");
                String senha = scanner.nextLine();
                user.autenticar(login, senha);
            }
            else if(tipo == 2){
                System.out.println("Qual o login?");
                String login = scanner.nextLine();
                System.out.println("Qual a senha?");
                String senha = scanner.nextLine();
                admin.autenticar(login, senha);
            }
            else{
                System.out.println("Opção não existente!");
            }
        }
*/

            if (tipo == 1) {
                boolean programa = true;
                System.out.println("Qual opção você quer acessar?");
                System.out.println("1 - Adicionar Música\n2 - Remover Mùsica\n3 - Buscar Música\n4 - Visualizar Música\n5 - Checar sua Playlist\n6 - Checar a Coleção Principal\n7 - Excluir sua Conta\n8 - Criar Nova Conta\n9 - Mostrar Opções\n10 - Sair do Programa");
                System.out.println("Lembre-se: Essas opções só aparecerão de novo caso a opção 9 seja escolhida.");
                while (programa) {
                    int opcao = scanner.nextInt();
                    Musica m;
                    boolean playlist = true;
                    if(user.getMusicas() == null){
                        playlist = false;
                    }
                    if (opcao == 1) {
                        if(!playlist){
                            System.out.println("Você ainda não possui playlist");
                        }
                        else{
                            user.adicionaMusica(admin);
                    }}
                    if (opcao == 2) {
                        if(!playlist){
                            System.out.println("Você ainda não possui playlist");
                        }
                        else{
                            System.out.println("Qual o ID da música que você quer retirar?");
                            int removeID = scanner.nextInt();
                            user.removeMusica(removeID);
                    }}
                    if (opcao == 3) {
                        if(!playlist){
                            System.out.println("Você ainda não possui playlist");
                        }
                        else{
                            System.out.println("Qual o nome da música que você quer buscar?");
                            String blank = scanner.next();
                            String nomemusica = scanner.nextLine();
                            m = user.buscaMusica(nomemusica);
                    }}
                    if (opcao == 4) {
                        if(!playlist){
                            System.out.println("Você ainda não possui playlist");
                        }
                        else{
                            System.out.println("Qual o ID da música você quer visualizar?");
                            int visID = scanner.nextInt();
                            user.visualizaMusica(visID);
                    }}

                    if (opcao == 5) {
                        if(!playlist){
                            System.out.println("Você ainda não possui playlist");
                        }
                        else{
                            System.out.println("Playlist do Usuário:");
                            System.out.println(user.getMusicas());
                    }}
                    if (opcao == 6) {
                        System.out.println("Coleção Principal:");
                        System.out.println(admin.getMusicas());
                    }
                    if (opcao == 7) {
                        user.excluirConta();
                    }
                    if (opcao == 8) {
                        System.out.println("Digite seu nome: ");
                        String blank = scanner.next();
                        String nome = scanner.nextLine();
                        System.out.println("Digite seu email: ");
                        String email = scanner.nextLine();
                        System.out.println("Digite sua senha: ");
                        String senha = scanner.nextLine();
                        user.criarConta(nome, email, senha);
                        System.out.println("Operação Completa, digite outra opção.");
                    }
                    if (opcao == 9){
                        System.out.println("1 - Adicionar Música\n2 - Remover Mùsica\n3 - Buscar Música\n4 - Visualizar Música\n5 - Checar sua Playlist\n6 - Checar a Coleção Principal\n7 - Excluir sua Conta\n8 - Criar Nova Conta\n9 - Mostrar Opções\n10 - Sair do Programa");
                    }
                    if (opcao == 10) {
                        programa = false;
                        System.out.println("Saindo do programa...");
                    }
                }
            }
            if (tipo == 2){
                boolean programa = true;
                System.out.println("Qual opção você quer acessar?");
                System.out.println("1 - Adicionar Música à Coleção\n2 - Remover Música da Coleção\n3 - Buscar Música da Coleção\n4 - Atualizar Música da Coleção\n5 - Buscar Usuário\n6 - Excluir Conta de Usuário\n7 - Criar Conta de Usuário\n8 - Checar a Coleção Principal\n9 - Mostrar Opções\n10 - Sair do Programa");
                while (programa){
                    int opcao = scanner.nextInt();
                    Musica m = null;
                    if (opcao == 1){
                        System.out.println("Você criará uma nova música a ser adicionada na Coleção.");
                        admin.adicionaMusica();
                    }
                    if (opcao == 2){
                        System.out.println("Qual o ID da música?");
                        int removeID = scanner.nextInt();
                        admin.removeMusica(removeID);
                    }
                    if (opcao == 3){
                        System.out.println("Qual o nome da música que você quer buscar?");
                        String blank = scanner.next();
                        String nomemusica = scanner.nextLine();
                        m = admin.buscaMusica(nomemusica);
                    }
                    if (opcao == 4){
                        System.out.println("Você atualizará uma música. Para isso, crie a música, com o mesmo ID, e faça suas modificações.");
                        m = admin.criaMusica();
                        admin.atualizaMusica(m);
                    }
                    if (opcao == 5){
                        System.out.println("Digite o login do Usuário a ser buscado:");
                        String blank = scanner.next();
                        String login = scanner.nextLine();
                        admin.buscarUsuario(login);
                    }
                    if (opcao == 6){
                        System.out.println("Digite o login do Usuário a ser excluído");
                        String blank = scanner.next();
                        String login = scanner.nextLine();
                        admin.excluirConta(login);
                    }
                    if (opcao == 7){
                        System.out.println("Digite seu nome: ");
                        String nome = scanner.nextLine();
                        System.out.println("Digite seu email: ");
                        String email = scanner.nextLine();
                        System.out.println("Digite sua senha: ");
                        String senha = scanner.nextLine();
                        admin.criarConta(nome, email, senha);
                        System.out.println("Operação Completa, digite outra opção.");
                    }
                    if (opcao == 8){
                        System.out.println("Coleção Principal:");
                        System.out.println(admin.getMusicas());
                    }
                    if (opcao == 9){
                        System.out.println("1 - Adicionar Música à Coleção\n2 - Remover Música da Coleção\n3 - Buscar Música da Coleção\n4 - Atualizar Música da Coleção\n5 - Buscar Usuário\n6 - Excluir Conta de Usuário\n7 - Criar Conta de Usuário\n8 - Checar a Coleção Principal\n9 - Mostrar Opções\n10 - Sair do Programa");
                    }
                    if (opcao == 10){
                        programa = false;
                        System.out.println("Saindo do programa...");
                    }
                }
            }

            /*-+-+-+-+-+-+-+-+-+Login e senha da conta principal do sistema+-+-+-+-+-+-+-+-+-
             * login: admin
             * senha: fromIncludeImport01
             */


        }
    }
}