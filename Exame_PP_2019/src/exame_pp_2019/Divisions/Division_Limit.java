/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame_pp_2019.Divisions;

import exame_pp_2019.Interfaces.IDivision_Limit;

/**
 *
 * @author PC
 */
public class Division_Limit implements IDivision_Limit {

    private String uuid;
    private Guest[] Guest;

    public Division_Limit(int max_guests) {
        super();
        this.Guest = new Guest[4];
    }

    @Override
    public Guest[] getGuest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add_Guest(Guest Guest) throws DivisionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove_Guest(Guest Guest) throws DivisionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMax_guests() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UUID getUuid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
