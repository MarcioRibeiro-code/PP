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
    private String zona;
    private String str;

    public Infecao_Exception(String str) {
        this.str = str;
    }

    public Infecao_Exception(LocalDateTime data, String id, String zona, String divisao, String str) {
        this.data = data;
        this.id = id;
        this.divisao = divisao;
        this.zona = zona;

        this.str = str + " " + data + " " + id + " " + divisao + " " + zona;
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
        return "Possivel Infecao: ID Pessoa: " + this.id + ".Data: " + this.data + ".Local: " + this.divisao;
    }

    public String getZona() {
        return this.zona;
    }

}
