/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.Equipment;
import estgconstroi.Failure;
import estgconstroi.enums.EquipmentStatus;

/**
 *
 * @author PC
 */
public class Failure_ extends Incident_ implements Failure {

    /**
     * The Failure interface provides the methods for an failure on an
     * equipment. Upon Creation of a Failure the {link equipment must be changed
     * to INOPERATIVE.
     */
    private final Equipment Equipment;
    
    public Failure_(Equipment Equipment, estgconstroi.ConstructionSite ConstructionSite, String Details, String NotificationMessage) {
        super(ConstructionSite, Details, NotificationMessage);
        this.Equipment = Equipment;
        this.Equipment.setStatus(EquipmentStatus.INOPERATIVE);
    }

    /**
     * Returns the Equipment that has failed.
     *
     *
     * @return The Equipment that has failed.
     */
    @Override
    public Equipment getEquipment() {
        return this.Equipment;
    }
    
}
