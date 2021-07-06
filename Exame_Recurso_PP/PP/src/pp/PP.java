/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pp;

import Csv.csv;
import Divisoes.Divisao;
import Divisoes.Zona;
import Enumeracoes.Genero;
import Enumeracoes.Tipo_Alerta;
import Enumeracoes.Tipo_Pessoa;
import Exceptions.Divisao_Exception;
import Exceptions.Hotel_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Exceptions.Zona_Exception;
import Hotel.Hotel;
import Movimentos.Movimentos;
import Pessoa.Fisonomia;
import Pessoa.Funcionario;
import Pessoa.Pessoa;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class PP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        Divisao div = new Divisao(1, "Quarto 201", "Quartos 2 andar");

        LocalDate localDate6 = LocalDate.parse("2018-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String[] str = new String[1];
        str[0] = "Problema Rins";
        Fisonomia fis = new Fisonomia(75.4, 1.8);
        Fisonomia fis1 = new Fisonomia(78, 1.9);

        Pessoa pessoa = new Pessoa("Márcio 1", localDate6, Genero.Masculino, str, fis, Tipo_Pessoa.Cliente);
        Pessoa pessoa1 = new Pessoa("Márcio 2", localDate6, Genero.Masculino, str, fis1, Tipo_Pessoa.Cliente);
        System.out.println(pessoa.getNome());
       
        try {
            div.addPessoa(pessoa);
            div.addPessoa(pessoa1);
            div.addPessoa(pessoa);
            div.addPessoa(pessoa);
        } catch (Pessoa_Exception ex) {
            System.out.println("Ja exisiste");
        } catch (Divisao_Exception ex1) {
            System.out.println("Divisao cheia");
        }

        Pessoa[] p=div.getPessoasDivisao();
        
        System.out.println(p[0].getNome());
        
        
        System.out.println(div.getId());
         
        Zona zona = new Zona("QA", "Quartos");
        try {
            zona.addDivisao(1, "Restaurante 1");
            zona.addDivisao(2, "Quarto201");
        } catch (Zona_Exception ex1) {
            System.out.println("Ja existe essa divisao");
        }

        try {
            System.out.println(zona.addPessoa("Restaurante 1", pessoa));
            System.out.println(zona.addPessoa("Quarto201", pessoa1));
        } catch (Pessoa_Exception ex) {
            System.out.println("Ja exisiste");
        } catch (Divisao_Exception ex1) {
            System.out.println("Divisao Cheia");
        }

        try {
            zona.addMovimento(0, 40, LocalDateTime.now());

            //System.out.println(div.getPessoasDivisao().length);
        } catch (Pessoa_Exception ex) {
            System.out.println("ID invalido");
        } catch (Infecao_Exception ex1) {

            System.out.println("EXCECAO Divisao:" + ex1.getDivisao());

            csv write_csv = new csv();

            write_csv.relatorio_alertas(Tipo_Alerta.P_INFECAO, ex1.getData(), ex1.getId(), ex1.getDivisao());
            // write_csv.relatorio_alertas(Tipo_Alerta.ACESSO,LocalDateTime.now(),1,"Quarto 201");

            System.out.println("Temperatura corporal superior - teste necessario.");
        }
         */
        Hotel hotel = new Hotel("1", "Hotel Penafiel");

        String[] str = new String[1];
        str[0] = "Problema Rins";
        Fisonomia fis = new Fisonomia(75.4, 1.8);
        Fisonomia fis1 = new Fisonomia(78, 1.9);
        LocalDate localDate6 = LocalDate.parse("2018-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Funcionario func = new Funcionario("Funcionario 1", "Limpeza", localDate6, Genero.Masculino, str, fis);
        Funcionario func1 = new Funcionario("Funcionario 2", "Limpeza", localDate6, Genero.Masculino, str, fis);

        hotel.addFuncionario(func);
        hotel.addFuncionario(func1);

        hotel.addZona("QA", "Quartos");
        hotel.addZona("LA", "Lazer");
        hotel.addZona("RE", "Restaurante");

        hotel.addDivisao("QA", 2, "Quarto 202");
        hotel.addDivisao("QA", 2, "Quarto 201");

        hotel.addDivisao("LA", 4, "Jogos Arcade");

        Divisao aux = hotel.getDivisaobyZona("QA", "Quarto 201");

        hotel.checkin("QA", "Quarto 201", "Márcio 1", localDate6, Genero.Masculino, str, fis);

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

    }

}
