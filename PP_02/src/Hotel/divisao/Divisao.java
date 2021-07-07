/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.divisao;

import Dados_Pessoa.Hospede;
import Dados_Pessoa.Pessoa;
import Exceptions.Divisao_Exception;
import Exceptions.Infecao_Exception;
import Exceptions.Movimentos_Exception;
import Exceptions.Pessoa_Exception;
import Hotel.Divisoes.Movimentos.Movimentos;
import Hotel.Enum.Tipo_Pessoa;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Divisao  {

    private final int num_max_suporte;
    private String Nome_Divisao;
    private int contador;

    public Divisao(int num_max_suporte, String identificador) {
        this.num_max_suporte = num_max_suporte;
        this.Nome_Divisao = identificador;

    }


    public String getNomeDivisao() {
        return this.Nome_Divisao;
    }

    
    public int getNumMaxSuporte() {
        return this.num_max_suporte;
    }

    public int getContador() {
        return contador;
    }

    public void incContador() {
        this.contador++;
    }

    public void decContador() {
        this.contador--;
    }

    public boolean addPessoa(String Zona, String Divisao) throws Divisao_Exception {

        int contador_pessoa = this.getContador();

        if (contador_pessoa + 1 > this.getNumMaxSuporte()) {
            throw new Divisao_Exception("Divisao Cheia: " + Zona + " " + Divisao);
        }

        //System.out.println("Contador: " + (contador_pessoa));
        //this.incContador();

        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Divisao)) {
            return false;
        }
        Divisao aux_div = (Divisao) obj;

        if (this.Nome_Divisao.equals(aux_div.Nome_Divisao)) {
            return true;
        }
        return false;
    }

}
