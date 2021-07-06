/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Infecao_Exception extends Exception {

    private LocalDateTime data;
    private String id;
    private String divisao;

    public Infecao_Exception(LocalDateTime data, String id, String divisao) {
        this.data = data;
        this.id = id;
        this.divisao = divisao;

    }

    public LocalDateTime getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public String getDivisao() {
        return divisao;
    }


    @Override
    public String toString() {
        return "Possivel Infecao: ID Pessoa: "+this.id+".Data: "+this.data+".Local: "+this.divisao;
    }

    
}
