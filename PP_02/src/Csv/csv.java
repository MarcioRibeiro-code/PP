/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Csv;

import Exceptions.Movimentos_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Tipo_Alerta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class csv {

    public void relatorio_alertas(Tipo_Alerta tp_a, LocalDateTime data, String cod, String divisao, String Zona) {

        // System.out.println("CSV");
        //System.out.println("ZONA csv: " + Zona + " " + divisao);
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
                sb.append("Data , Hora , TipoAlerta , CodigoIndividuo, Zona ,Divisao\n");
            }

            DateTimeFormatter a = DateTimeFormatter.ofPattern("\ndd/MM/yyyy, ");
            sb.append(data.format(a));
            a = DateTimeFormatter.ofPattern("HH:mm, ");
            sb.append(data.format(a));
            sb.append(tp_a + ", ");
            sb.append(cod + ", ");

            sb.append(Zona + ", ");
            sb.append(divisao);
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            Logger.getLogger(csv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void Monitorizacao_acesso(String id_pessoa, Movimentos[] mon) throws Movimentos_Exception {
        int contador = 0;
        for (int i = 0; i < mon.length; i++) {
            if (mon[i] != null) {
                System.out.println(mon[i].getDivisao());
                contador++;
            }
        }
        System.out.println("Contador mon: " + contador);

        if (mon == null) {
            throw new Movimentos_Exception("Array null");
        }

        try {

            File file = new File("relatorio/mon_acesso.csv");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            PrintWriter pw = new PrintWriter(bw);
            StringBuilder sb = new StringBuilder();

            if (file.length() == 0) {
                sb.append("Data , Hora , CodigoIndividuo, Zona ,Divisao, Tempo\n");
            }

            for (int i = 0; i < contador - 1; i++) {

                DateTimeFormatter a = DateTimeFormatter.ofPattern("\ndd/MM/yyyy, ");
                sb.append(mon[i].getTime().format(a));
                a = DateTimeFormatter.ofPattern("HH:mm, ");
                sb.append(mon[i].getTime().format(a));
                sb.append(id_pessoa + ", ");
                sb.append(mon[i].getZona() + ", ");
                sb.append(mon[i].getDivisao() + ", ");
                Duration duration;

                if (mon[i + 1] == null) {
                    duration = Duration.ZERO;
                } else {
                    duration
                            = Duration.between(mon[i].getTime(), mon[i + 1].getTime());
                }

                sb.append(duration.toMinutes());
                System.out.println("DURACAO EM MINUTOS: " + duration.toMinutes());
            }
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            Logger.getLogger(csv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
