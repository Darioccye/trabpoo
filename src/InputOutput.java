import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class InputOutput {

    public static String leituraConsole(){
        Scanner teclado = new Scanner(System.in);
        return teclado.nextLine();
    };

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
