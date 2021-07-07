/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados_Pessoa.d_aux;


/**
 *
 * @author PC
 */
public class Fisonomia implements Ifisonomia{
    private final double peso;
    private final double altura;

    public Fisonomia(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
    }

    @Override
    public double getPeso() {
    return this.peso;    
    }

    @Override
    public double getAltura() {
    return this.altura;  
    }
    
    
}
