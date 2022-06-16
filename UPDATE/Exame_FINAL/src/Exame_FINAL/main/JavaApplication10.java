/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.main;

import Exame_FINAL.Classes.ConstructionSite_;
import Exame_FINAL.Classes.Employee_;
import Exame_FINAL.Classes.Equipment_;
import Exame_FINAL.Classes.Team_;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.exceptions.ConstructionSiteException;
import estgconstroi.exceptions.TeamException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class JavaApplication10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Team_ tm1 = new Team_("EXemplo");
        Employee_ emp1 = new Employee_("marcio", EmployeeType.WORKER);
        Employee_ emp2 = new Employee_("marcio", EmployeeType.WORKER);
        try {
            tm1.addEmployees(emp1);
        } catch (TeamException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tm1.addEmployees(emp2);
        } catch (TeamException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        try {
            tm1.removeEmployees(emp1);
        } catch (TeamException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(tm1.toString());
        //System.out.println(emp1.toString());
         */

        ConstructionSite_ cs1 = new ConstructionSite_("EXEMPLO 1", "local 1");
        Equipment_ eq1 = new Equipment_("Retro", EquipmentType.EQUIPMENT, EquipmentStatus.OPERATIVE);
        try {
            cs1.addTeam(tm1);

        } catch (ConstructionSiteException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cs1.addTeam(null);

        } catch (ConstructionSiteException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cs1.addEquipment(null);

        } catch (ConstructionSiteException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cs1.addEquipment(null);

        } catch (ConstructionSiteException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(cs1.toString());
    }

}
