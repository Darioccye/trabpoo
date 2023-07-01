import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputOutput {

    public static String leituraConsoleString(){
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    };

    public static int leituraConsoleint(){
        Scanner teclado = new Scanner(System.in);
        return teclado.nextInt();
    }

    public static boolean escritaBinarioString(String arquivo, String str){
          try{
            FileOutputStream outputStream = new FileOutputStream(arquivo, true);
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);
            System.out.println("Escrita com sucesso");

            outputStream.close();}
          catch(IOException ie){
              System.out.println(ie.toString());
              return false;
          }
          return true;
    }

    public static boolean escritaBinarioMusica(String arquivo, Musica musica){
        String id = musica.getId().toString();
        String dmin = musica.getDuracaomin().toString();
        String dseg = musica.getDuracaoseg().toString();
        escritaBinarioString(arquivo, id);
        escritaBinarioString(arquivo, dmin);
        escritaBinarioString(arquivo, dseg);
        escritaBinarioString(arquivo, musica.getTitulo());
        escritaBinarioString(arquivo, musica.getAutores());
        escritaBinarioString(arquivo, musica.getData());
        escritaBinarioString(arquivo, musica.getGenero());
        return true;
    }



    public static <T> void escritaArquivoBinario(String arquivo, T objeto){ // aparentemente não funciona
        try(
                FileOutputStream fos = new FileOutputStream("arquivo");){
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(objeto);
                oos.close();
        } catch(IOException ex){
            System.out.println(ex.toString());
        }
    }

    public static String leituraArquivoTexto(String nomeArq){
            FileReader fr = null;
            BufferedReader br = null;
            String texto = "";

            try {
                fr = new FileReader(nomeArq);
                br = new BufferedReader(fr);
                boolean eof = false;

                do{
                    String s = br.readLine();
                    if (s==null){
                        eof = true;
                    }
                    else{
                        texto = texto + s + "\n";
                    }
                }while (!eof);

            } catch (IOException ex) {
                Logger.getLogger(Letra___NaoUtilizada.class.getName()).log(Level.SEVERE, null, ex);
            }
            return texto;
    }

    public static String leituraCriptografia(String path){
        FileReader fr;
        BufferedReader br;
        String line = "null";
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            line = br.readLine();
            fr.close();
            br.close();
            
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return line;
    };

    public static void escritaCriptografia(String path, String mensagem){
        FileWriter fw;
        PrintWriter pw;
        try {
            fw = new FileWriter(path);
            pw = new PrintWriter(fw);
            
            pw.println(mensagem);//Escrita no arquivo
            
            fw.close();
            pw.close();
            
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } 
    };
}
