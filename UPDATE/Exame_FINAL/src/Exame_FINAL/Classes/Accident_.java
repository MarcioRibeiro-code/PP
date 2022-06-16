/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.Accident;
import estgconstroi.Employee;

/**
 *
 * @author PC
 */
public class Accident_ extends Incident_ implements Accident {

    private final Employee Employee;

    public Accident_(Employee Employee, estgconstroi.ConstructionSite ConstructionSite, String Details, String NotificationMessage) {
        super(ConstructionSite, Details, NotificationMessage);
        this.Employee = Employee;
    }

    
    /**
     * Returns the Employee that was injured in the incident.
     * 
     * @return The employee that was injured in the incident.
     */
    @Override
    public Employee getEmployee() {
        return this.Employee;
    }

}
