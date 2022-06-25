/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.main;

import Exame_FINAL.Classes.ConstructionSite_;
import Exame_FINAL.Classes.Employee_;
import Exame_FINAL.Classes.Equipment_;
import Exame_FINAL.Classes.Manager;
import Exame_FINAL.Classes.Team_;
import Exame_FINAL.Exceptions.ManagerException;
import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.exceptions.ConstructionSiteException;
import estgconstroi.exceptions.TeamException;
import java.util.Scanner;
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

        int key = -1;
        Manager mn1 = new Manager();

        Scanner scan = new Scanner(System.in);
        System.out.println("--MENU PRINCIPAL--");
        System.out.println("1 - Gerir Construction Site");
        System.out.println("2 - Gerir Equipas");
        System.out.println("3 - Gerir Funcionarios");
        System.out.println("4 - Gerir Equipamentos");
        System.out.println("0 - Sair");
        System.out.print("opcao: ");
        key = scan.nextInt();
        while (key != 0) {

            switch (key) {
                case 1:
                    mn1.Manage_ConstructionSite();
                    break;
                case 2:
                    mn1.manage_Team();
                    break;
                case 3:
                    mn1.Manage_Employee();
                    break;
                case 4:
                    mn1.ManageEquipment();
                    break;
            }
            System.out.println("\n1 - Gerir Construction Site");
            System.out.println("2 - Gerir Equipas");
            System.out.println("3 - Gerir Funcionarios");
            System.out.println("4 - Gerir Eventos");
            System.out.println("0 - Sair");
            System.out.print("opcao: ");
            key = scan.nextInt();
        }

        /*
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
         */
    }

}
