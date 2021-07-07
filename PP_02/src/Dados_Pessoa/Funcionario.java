/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados_Pessoa;

import Dados_Pessoa.d_aux.Fisonomia;
import Exceptions.Infecao_Exception;
import Exceptions.Pessoa_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Genero;
import Hotel.Enum.Tipo_Pessoa;
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
        super(nome, data_nascimento, genero, patologias, fisonomia, Tipo_Pessoa.Funcionario, contador);
        Funcionario.contador++;
        this.cargo = cargo;
    }

    public void getFuncionario() {
        System.out.println("Nome - Funcionario: " + this.getNome());
        System.out.println("Data - Funcionario: " + this.getData_nascimento());
        System.out.println("Id: " + this.getId());
    }

    /**
     *
     * @param body_temp Temperatura Corporal obtida dos sensores
     * @param data Data atual
     * @param Zona ID da Zona
     * @param divisao Id da divisao
     * @return verdade ou falso, se for adicionado ou nao
     * @throws Infecao_Exception
     */
    @Override
    public boolean addMovimento(double body_temp, LocalDateTime data, String Zona, String divisao) throws Infecao_Exception, Pessoa_Exception {

        int contador = 0;

        for (Movimentos movimento : this.getmovimentos()) {
            if (movimento != null) {
                contador++;
            }
        }

        Movimentos[] aux_mov = this.getmovimentos();
        Movimentos a = new Movimentos(body_temp, data, Zona, divisao, this.cargo);

        for (Movimentos aux : aux_mov) {
            if (aux != null) {
                if (aux.equals(a)) {
                    throw new Pessoa_Exception("Movimento repetido");
                }
            }
        }

        if (data != null && !divisao.isBlank()) {
            aux_mov[contador] = new Movimentos(body_temp, data, Zona, divisao, this.cargo);

        }

        System.out.println("Temp: " + aux_mov[contador].getBody_temp());
        if (aux_mov[contador].getBody_temp() >= 38) {
            System.out.println("Febre");
            System.out.println("MOVIMENTO FUNCIONARIO: " + aux_mov[contador].getZona());
            throw new Infecao_Exception(aux_mov[contador].getTime(), this.getId(), aux_mov[contador].getZona(), aux_mov[contador].getDivisao(), "Possivel Infecao");
        }
        return true;
    }

}
