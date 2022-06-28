/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame_pp_2019.Divisions;

import exame_pp_2019.Interfaces.IDivision;
import java.util.UUID;

/**
 *
 * @author PC
 */
public class Division implements IDivision{
    
    private final int max_guests;
    private final String uuid;

    public Division(int max_guests) {
        this.max_guests = max_guests;
        this.uuid = IDivision.addUUID();
    }


    @Override
    public int getMax_guests() {
        return this.max_guests;
    }

   

    @Override
    public String toString() {
        return "Division{" + "max_guests=" + max_guests + ", uuid=" + uuid + '}';
    }

    @Override
    public UUID getUuid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
