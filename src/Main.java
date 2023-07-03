import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException{
        Criptografia inicializa = new Criptografia();//Preciso dele para comecar a ler e usar o sistema

        Controle controle = new Controle(inicializa);//Controle usado pelo usuario comum ou usuario administrador

        UsuarioComum user = new UsuarioComum(controle);
        UsuarioAdministrador admin = new UsuarioAdministrador(controle);


        while(true){//Loop principal, nenhum autenticado

            System.out.println("Qual o tipo de Usuário?\nComum (1) ou Administrador (2)?");
            int tipoDeConta = InputOutput.leituraConsoleint();

            if(tipoDeConta != 1 && tipoDeConta != 2){
                System.out.println("-----------------Opcao invalida!-----------------\n");
                continue;
            }

            System.out.println("Qual o login?");
            String login = InputOutput.leituraConsoleString();
            System.out.println("Qual a senha?");
            String senha = InputOutput.leituraConsoleString();



            if(tipoDeConta == 1){//Usuario Comum
                System.out.println("Voce quer criar conta ou entrar 1 - Criar\t2 - Entrar?");
                int conta = InputOutput.leituraConsoleint();
                if(conta != 1 && conta != 2){
                    System.out.println("Opcao invalida! Retornando ao inicio...\n");
                    continue;
                };

                if(conta == 1){
                    System.out.print("Digite o seu nome: ");
                    String nome = InputOutput.leituraConsoleString();
                    user.criarConta(nome, login, senha);
                }else{
                    user.autenticar(login, senha);
                };

                if(user.identificador != "null"){//Foi autenticado ou nao

                    //Chamar aqui a iniciacao da playlist--------------------------------------------------------------------------
                    while(true){
                        System.out.println("Qual opção você quer acessar?");
                        System.out.println("1 - Adicionar Música\n2 - Remover Música\n3 - Buscar e Visualizar Música\n4 - Checar existencia de Música\n5 - Checar a Coleção Principal\n6 - Excluir sua Conta\n7 - Sair do Programa");
                        
                        int opcaoUsuario = InputOutput.leituraConsoleint();

                        if(opcaoUsuario == 1){
                            user.adicionarMusica(admin);
                            continue;
                        };
                        if(opcaoUsuario == 2){
                            if(user.musicas == null){
                                System.out.println("Nao tem musicas a remover!");
                            }else{
                                System.out.println("Digite um ID a remover!");
                                int id = InputOutput.leituraConsoleint();
                                user.removeMusica(id);
                            };
                            continue;
                        };
                        if(opcaoUsuario == 3){
                            if(user.musicas == null){
                                System.out.println("Nao tem musicas a buscar!");
                            }else{
                                System.out.println("Qual o nome da musica a buscar?");
                                String nome = InputOutput.leituraConsoleString();
                                Musica musica = user.buscaMusica(nome);
                                user.visualizaMusica(musica);
                            };
                            continue;
                        };
                        if(opcaoUsuario == 4){
                            if(user.musicas == null){
                                System.out.println("Sem musicas a checar!");
                            }else{
                                System.out.println("Qual musica (nome) voce quer checar?");
                                String nome = InputOutput.leituraConsoleString();
                                Musica musica = user.buscaMusica(nome);
                                if(musica == null){
                                    System.out.println("Musica nao contida!");
                                }else{
                                    System.out.println("Musica contida!");
                                }
                            };
                            continue;
                        };
                        if(opcaoUsuario == 5){
                            System.out.println("---------------Colecao Principal---------------");
                            System.out.println(admin.getMusicas());
                            continue;
                        };
                        if(opcaoUsuario == 6){
                            user.excluirConta();
                            System.out.println("Saiu do sistema!");
                            break;
                        };
                        if(opcaoUsuario == 7){
                            System.out.println("Saiu do sistema!");
                            break;
                        };
                    }
                    continue;
                }else continue;//Nao foi autenticado
            };


            if(tipoDeConta == 2){//Usuario Admin
                admin.autenticar(login, senha);
                if(admin.identificador != "null"){//Autenticado ou nao autenticado
                    while(true){
                        System.out.println("Qual opcao voce quer acessar Administrador?");
                        System.out.println("1 - Adicionar Musica\n2 - Remover Musica\n3 - Buscar e visualizar Musica\n4 - Atualizar Musica\n5 - Buscar e visualizar usuario\n6 - Excluir Conta de usuario\n7 - Criar conta de usuario\n8 - Checar a colecao principal\n9 - Sair do Programa");
                        
                        int opcaoAdmin = InputOutput.leituraConsoleint();
                        
                        if(opcaoAdmin == 1){
                            System.out.println("Criar nova musica!");
                            admin.adicionarMusica();
                            continue;
                        };
                        if(opcaoAdmin == 2){
                            System.out.println("Qual ID da musica voce quer remover?");
                            int id = InputOutput.leituraConsoleint();
                            admin.removeMusica(id);
                            continue;
                        };
                        if(opcaoAdmin == 3){
                            System.out.println("Qual musica buscar/visualizar?");
                            String nome = InputOutput.leituraConsoleString();
                            Musica musica;
                            musica = admin.buscaMusica(nome);
                            admin.visualizaMusica(musica);
                            continue;
                        };
                        if(opcaoAdmin == 4){
                            System.out.println("A musica a ser atualizada sera substituida à nova a ser criada");
                            Musica musica = admin.criaMusica();
                            admin.atualizaMusica(musica);
                            continue;
                        };
                        if(opcaoAdmin == 5){//Retorna o Id do usuario
                            System.out.println("Digite o login do usuario!");
                            String loginTemp = InputOutput.leituraConsoleString();
                            String idTemp = admin.buscarUsuario(loginTemp);
                            System.out.println("O id do usuario "+loginTemp+"e "+idTemp);
                            continue;
                        };
                        if(opcaoAdmin == 6){
                            System.out.println("Digite o login do usuario a ser excluido");
                            String nomeUsuario = InputOutput.leituraConsoleString();
                            admin.excluirConta(nomeUsuario);
                            continue;
                        };
                        if(opcaoAdmin == 7){
                            System.out.println("Digite usuario a ser adicionado");
                            String nome = InputOutput.leituraConsoleString();
                            System.out.println("Digite o email");
                            String emailUser = InputOutput.leituraConsoleString();
                            String senhaUser = InputOutput.leituraConsoleString();
                            admin.criarConta(nome, emailUser, senhaUser);
                            continue;
                        };
                        if(opcaoAdmin == 8){
                            System.out.println("Colecao Principal");
                            System.out.println(admin.getMusicas());
                            continue;
                        };
                        if(opcaoAdmin == 9){
                            System.out.println("Saindo do Programa...");
                            break;
                        }
                    }
                }else continue;
            }
        }
    }
}


/*-+-+-+-+-+-+-+-+-+Login e senha da conta principal do sistema+-+-+-+-+-+-+-+-+-
* login: admin
* senha: fromIncludeImport01
*/