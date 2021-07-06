/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pessoa;

import Enumeracoes.Genero;
import Enumeracoes.Tipo_Pessoa;
import Exceptions.Infecao_Exception;
import Movimentos.Movimentos;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Funcionario extends Pessoa {

    private static int contador = 0;
    private String cargo;

    public Funcionario(String nome, String cargo, LocalDate data_nascimento, Genero genero, String[] patologias, Fisonomia fisonomia) {
        super(nome, data_nascimento, genero, patologias, fisonomia, Tipo_Pessoa.Funcionario);
        Funcionario.contador++;
        this.cargo = cargo;
    }

    public void getFuncionario() {
        System.out.println("Nome - Funcionario: " + this.getNome());
        System.out.println("Data - Funcionario: " + this.getData_nascimento());
        System.out.println("Id: " + this.getId());
    }

    @Override
    public boolean addMovimento(double body_temp, LocalDateTime data, String divisao) throws Infecao_Exception {

        int contador = 0;

        for (Movimentos movimento : this.getmovimentos()) {
            if (movimento != null) {
                contador++;
            }
        }

        Movimentos[] aux_mov = this.getmovimentos();

        if (data != null && !divisao.isBlank()) {
            aux_mov[contador] = new Movimentos(body_temp, data, divisao,this.cargo);

        }

        System.out.println("Temp: " + aux_mov[contador].getBody_temp());
        if (aux_mov[contador].getBody_temp() > 38) {
            System.out.println("Febre porra");
            System.out.println("MOVIMENTO FUNCIONARIO: "+aux_mov[contador].getZona());
            throw new Infecao_Exception(aux_mov[contador].getTime(), this.getId(), aux_mov[contador].getZona());
        }
        return true;
    }

}

