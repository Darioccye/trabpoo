import java.io.File;
public abstract class Usuario {
    private String nome;
    private String identificador;
    private String login;
    private String senha;

    public void alterarDado(){

    };
    public boolean excluirConta(String arquivo){
        File file = new File(arquivo);
        boolean result = file.delete();
        if (result) {
            System.out.println("Conta retirada do sistema!");
        }
        else {
            System.out.println("Erro, conta nao excluida!");
        };
        return result;
    };

}
