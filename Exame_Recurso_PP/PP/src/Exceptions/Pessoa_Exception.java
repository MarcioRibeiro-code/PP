/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author PC
 */
public class Pessoa_Exception extends Exception{

    private String str;
    
    public Pessoa_Exception(String encontrado_retorna_falso) {
        str=encontrado_retorna_falso;
    }

    public String getStr() {
        return str;
    }
    
    @Override
    public String toString() {
        return this.str; //To change body of generated methods, choose Tools | Templates.
    }
    
}
