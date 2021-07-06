/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Csv;

import Enumeracoes.Tipo_Alerta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class csv {

    public void relatorio_alertas(Tipo_Alerta tp_a, LocalDateTime data, String cod, String divisao) {

        System.out.println("CSV");

        try {

            File file = new File("relatorio/relatorio.csv");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            PrintWriter pw = new PrintWriter(bw);
            StringBuilder sb = new StringBuilder();

            if (file.length() == 0) {
                sb.append("Data , Hora , TipoAlerta , CodigoIndividuo, Divisao\n");
            }

            DateTimeFormatter a = DateTimeFormatter.ofPattern("\ndd/MM/yyyy, ");
            sb.append(data.format(a));
            a = DateTimeFormatter.ofPattern("HH:mm, ");
            sb.append(data.format(a));
            sb.append(tp_a + ", ");
            sb.append(cod + ", ");
            sb.append(divisao);

            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            Logger.getLogger(csv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
