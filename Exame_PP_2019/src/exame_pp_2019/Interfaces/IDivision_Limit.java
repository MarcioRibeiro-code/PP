/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame_pp_2019.Interfaces;

/**
 *
 * @author PC
 */
public interface IDivision_Limit extends IDivision{
    
    Guest[] getGuest();
    void add_Guest(Guest Guest) throws DivisionException;
    void remove_Guest(Guest Guest) throws DivisionException;
}
