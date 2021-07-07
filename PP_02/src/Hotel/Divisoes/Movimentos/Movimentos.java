/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hotel.Divisoes.Movimentos;

import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public class Movimentos implements IMovimentos {

    private final double body_temp;
    private final LocalDateTime data;
    private final String zona;
    private final String divisao;
    private String cargo;

    public Movimentos(double body_temp, LocalDateTime data, String zona, String divisao, String cargo) {
        this.body_temp = body_temp;
        this.data = data;
        this.zona = zona;
        this.divisao = divisao;
        this.cargo = cargo;
    }

    public Movimentos(double body_temp, LocalDateTime data, String zona, String divisao) {
        this.body_temp = body_temp;
        this.data = data;
        this.zona = zona;
        this.divisao = divisao;
    }

    
    
    @Override
    public LocalDateTime getTime() {
        return this.data;
    }

    @Override
    public String getZona() {
        return this.zona;
    }

    @Override
    public String getDivisao() {
        return this.divisao;
    }

    @Override
    public boolean equals(Object arg0) {

        Movimentos aux = (Movimentos) arg0;

        if (this.data.equals(aux.data)
                && this.zona.equals(aux.zona) && this.divisao.equals(aux.divisao)) {
            return true;
        }
        return false;
    }

    @Override
    public double getBody_temp() {
        return this.body_temp;
    }

}
