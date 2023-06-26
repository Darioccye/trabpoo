//import java.io.File;
public abstract class Usuario {
    protected String nome;
    protected String identificador;//Padrao sem autenticar = "null"
    protected String login;
    protected String senha;
    protected Controle meuControle;


    public abstract void excluirConta();
    public abstract void excluirConta(String login);

    public abstract void criarConta(String nome, String login, String senha);

    public void autenticar(){
        if(this.identificador != "null"){
            System.out.println("Ja esta autenticado!");
            return;
        };
        //Entradas de login e senha a fazer antes de passar null---------------------------------------------FABIO
        this.identificador = this.meuControle.autenticarConta(login, senha);
        if(identificador == "null"){
            System.out.println("Nao autenticado!");
            return;
        };
        System.out.println("Autenticado!");
    };
    

}
