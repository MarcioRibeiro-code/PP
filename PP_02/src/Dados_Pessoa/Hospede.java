/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados_Pessoa;

import Dados_Pessoa.d_aux.Fisonomia;
import Exceptions.Infecao_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Genero;
import Hotel.Enum.Tipo_Pessoa;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Hospede extends Pessoa {

    private static int contador = 0;
    private boolean flag_quarto;

    public Hospede(String nome, LocalDate data_nascimento, Genero genero, String[] patologias, Fisonomia fisonomia, Tipo_Pessoa tipo) {
        super(nome, data_nascimento, genero, patologias, fisonomia, tipo, contador);
        contador++;
    }

    public boolean isFlag_quarto() {
        return flag_quarto;
    }

    public void setFlag_quarto(boolean flag_quarto) {
        this.flag_quarto = flag_quarto;
    }

    @Override
    public boolean addMovimento(double body_temp, LocalDateTime data, String Zona, String divisao) throws Infecao_Exception {
        int contador = 0;

        for (Movimentos movimento : this.getmovimentos()) {
            if (movimento != null) {
                contador++;
            }
        }

        Movimentos[] aux_mov = this.getmovimentos();

        if (data != null && !divisao.isBlank()) {
            aux_mov[contador] = new Movimentos(body_temp, data, Zona, divisao);

        }

        System.out.println("Temp Hospede: " + aux_mov[contador].getBody_temp());
        if (aux_mov[contador].getBody_temp() > 38) {
            System.out.println("Febre porra 1");
            throw new Infecao_Exception(aux_mov[contador].getTime(), this.getId(), aux_mov[contador].getZona(),aux_mov[contador].getDivisao(),"Possivel Infecao");
        }
        return true;
    }

  

}
