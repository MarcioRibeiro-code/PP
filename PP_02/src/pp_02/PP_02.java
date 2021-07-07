/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp_02;

import Csv.csv;
import Dados_Pessoa.Funcionario;
import Dados_Pessoa.Hospede;
import Dados_Pessoa.Pessoa;
import Dados_Pessoa.d_aux.Fisonomia;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Genero;
import Hotel.Hotel1;
import Hotel.divisao.Divisao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PC
 */
public class PP_02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Hotel1 hotel = new Hotel1("1", "Hotel Penafiel");

        LocalDateTime localDate6 = LocalDateTime.parse("2018-07-22 10:20", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String[] str = new String[1];
        str[0] = "Problema Rins";
        Fisonomia fis = new Fisonomia(75.4, 1.8);
        Fisonomia fis1 = new Fisonomia(78, 1.9);
        LocalDate data_nas = LocalDate.parse("2018-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Funcionario func = new Funcionario("Funcionario 1", "Limpeza", data_nas, Genero.Masculino, str, fis);
        Funcionario func1 = new Funcionario("Funcionario 2", "Limpeza", data_nas, Genero.Masculino, str, fis);

        hotel.addFuncionario(func);
        hotel.addFuncionario(func1);

        hotel.addZona("QA", "Quartos");
        hotel.addZona("LA", "Lazer");
        hotel.addZona("RE", "Restaurante");

        hotel.addDivisao("QA", 2, "Quarto 202");
        hotel.addDivisao("QA", 2, "Quarto 201");

        hotel.addDivisao("LA", 4, "Jogos Arcade");

        Divisao aux = hotel.getDivisaobyZona("QA", "Quarto 201");

        hotel.checkin("QA", "Quarto 202", localDate6, "Márcio 1", data_nas, Genero.Masculino, str, fis);

        localDate6 = LocalDateTime.parse("2018-07-22 10:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        //hotel.addMovimentos("LA", "Jogos Arcade", 38.1, "HO0", localDate6);
        hotel.addMovimentos("LA", "Jogos Arcade", 38.1, "HO0", localDate6);

        Divisao aux_div = hotel.getDivisaobyZona("LA", "Jogos Arcade");
        System.out.println("Contador Pessoas na Divisao JOGOS: " + aux_div.getContador());

        aux_div = hotel.getDivisaobyZona("QA", "Quarto 202");
        System.out.println("Contador Pessoas na Divisao QUARTO: " + aux_div.getContador());

        localDate6 = LocalDateTime.parse("2018-07-22 10:40", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        hotel.addMovimentos("QA", "Quarto 202", 38.1, "FU0", localDate6);

       // hotel.addMovimentos("QA", "Quarto 202", 38.1, "HO0", localDate6);
        
        aux_div = hotel.getDivisaobyZona("LA", "Jogos Arcade");
        System.out.println("Contador Pessoas na Divisao JOGOS: " + aux_div.getContador());

        aux_div = hotel.getDivisaobyZona("QA", "Quarto 202");
        System.out.println("Contador Pessoas na Divisao QUARTO: " + aux_div.getContador());

        hotel.relatorio_movimentos("FU0");

        hotel.Exception_toStr();
        //Hospede hp_aux = aux_div.getPessoaDivisao("Ho2");


        /*
        Pessoa[] aux1 = hotel.getPessoasByDivisao("QA", "Quarto 201");

        hotel.addMovimentos("LA", "Jogos Arcade", 38.1, "HO2");

        if (aux1 != null) {
            System.out.print('\u000C');
            for (Pessoa aux11 : aux1) {
                if (aux11 != null) {

                    System.out.println("Tipo: " + aux11.getTipo());
                    for (Movimentos mov1 : aux11.getmovimentos()) {
                        if (mov1 != null) {
                            System.out.println(mov1.getZona());
                        }
                    }
                }
            }

        }

        hotel.Exception_toStr();
         */
    }

}
