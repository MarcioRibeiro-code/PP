/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL;

import Exame_FINAL.Classes.Employee_;
import Exame_FINAL.Classes.Team_;
import estgconstroi.Employee;
import estgconstroi.enums.EmployeeType;
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

        try {
            tm1.removeEmployees(emp1);
        } catch (TeamException ex) {
            Logger.getLogger(JavaApplication10.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(tm1.toString());
        //System.out.println(emp1.toString());
    }

}
