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
public class Zona_Exception extends Exception {

    private LocalDateTime data;
    private String id;
    private String divisao;

    private String str;

    public Zona_Exception(String dados_Invalidos) {
        this.str = dados_Invalidos;
    }

    public Zona_Exception(LocalDateTime data, String id, String divisao) {
        this.data = data;
        this.id = id;
        this.divisao = divisao;
        this.str = data+" "+id+" "+divisao;
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

    
    
    
    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return this.str; //To change body of generated methods, choose Tools | Templates.
    }

}
