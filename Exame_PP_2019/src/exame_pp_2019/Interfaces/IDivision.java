/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exame_pp_2019.Interfaces;

import java.util.UUID;

/**
 *
 * @author PC
 */
public interface IDivision {

    int getMax_guests();

    UUID getUuid();

    static String addUUID() {
        return UUID.randomUUID().toString();
    }

    @Override
    String toString();
}
