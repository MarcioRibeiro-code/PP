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
public interface IMovimentos {

    public double getBody_temp();

    public LocalDateTime getTime();

    public String getZona();
    
    public String getDivisao();

    public boolean equals(Object arg0);
}
