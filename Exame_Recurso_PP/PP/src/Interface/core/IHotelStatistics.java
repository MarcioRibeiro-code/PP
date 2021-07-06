/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.core;

import Pessoa.Pessoa;
import java.time.LocalDateTime;

/**
 *
 * @author PC
 */
public interface IHotelStatistics {

    public Pessoa[] getPessoasByDivisao(String arg0, String arg1);

    public Pessoa getPessoaByDivisao(String arg0, String arg1,String id);

}
