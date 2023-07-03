import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
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
              str = str + "\n";
            DataOutputStream outputStream = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(arquivo, true)));
            byte[] strToBytes = str.getBytes();
            outputStream.write(strToBytes);

            outputStream.close();}
          catch(IOException ie){
              System.out.println(ie.toString());
              return false;
          }
          return true;
    }


    public static boolean escritaBinarioMusica(String arquivo, Musica musica){
        String tag = musica.getTag().toString();
        String id = musica.getId().toString();
        String dmin = musica.getDuracaomin().toString();
        String dseg = musica.getDuracaoseg().toString();
        escritaBinarioString(arquivo, tag);
        escritaBinarioString(arquivo, id);
        escritaBinarioString(arquivo, dmin);
        escritaBinarioString(arquivo, dseg);
        escritaBinarioString(arquivo, musica.getTitulo());
        escritaBinarioString(arquivo, musica.getAutores());
        escritaBinarioString(arquivo, musica.getData());
        escritaBinarioString(arquivo, musica.getGenero());
        return true;
    }

    public static boolean escritaBinarioColecao(String arquivo, Collection<Musica> colecao){
        try{  // Limpa o Arquivo para escrever a Collection depois.
            PrintWriter writer = new PrintWriter(arquivo);
            writer.close();
        } catch(IOException ie){
            System.out.println(ie.toString());
        }
        for(Musica m:colecao){
            escritaBinarioMusica(arquivo, m);
        }
        return true;
    }


    public static String leituraBinarioString(String arquivo){
        Scanner scanner = new Scanner(arquivo);
        String in = scanner.nextLine();
        return in;
    }

    public static Collection<Musica> leituraBinarioColecao(String arquivo){
        try {
            RandomAccessFile raf = new RandomAccessFile(arquivo, "r");
            String i = "a";
            Musica musica = null;
            Collection<Musica> colecao = new ArrayList<Musica>();
            while((i = raf.readLine()) != null){
                int tag = Integer.parseInt(i);
                i = raf.readLine();
                int id = Integer.parseInt(i);
                i = raf.readLine();
                int dmin = Integer.parseInt(i);
                i = raf.readLine();
                int dseg = Integer.parseInt(i);
                i = raf.readLine();
                String titulo = i;
                i = raf.readLine();
                String autores = i;
                i = raf.readLine();
                String data = i;
                i = raf.readLine();
                String genero = i;
                if(tag == 1){
                    String patitura = "src/instrumental/"+id+".txt";
                    musica = new MusicaInstrumental(tag, id, dmin, dseg, titulo, autores, data, genero, patitura);
                }
                else if(tag == 2){
                    String letra = "src/cancao/"+id+".txt";
                    musica = new MusicaCancao(tag, id, dmin, dseg, titulo, autores, data, genero, letra);
                }
                colecao.add(musica);
            }
            raf.close();
            return colecao;
        } catch(IOException ie){
            System.out.println(ie.toString());
            System.out.println("Não foi possível encontrar o arquivo ou estava vazio.");
        }
        return null;
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
