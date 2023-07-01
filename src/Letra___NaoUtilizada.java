import java.io.*;
import java.util.logging.*;

public class Letra___NaoUtilizada {
    private String nomeArqLetra;
    private String texto;

    public Letra___NaoUtilizada(String nomeArqLetra) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            this.nomeArqLetra = nomeArqLetra;
            fr = new FileReader(nomeArqLetra);
            br = new BufferedReader(br);
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
    }

    public String getNomeArqLetra() {
        return nomeArqLetra;
    }

    public void setNomeArqLetra(String nomeArqLetra) {
        this.nomeArqLetra = nomeArqLetra;
    }

    @Override
    public String toString() {
        return "Letra{" + "nomeArqLetra=" + nomeArqLetra + ", texto=" + texto + '}';
    }




}