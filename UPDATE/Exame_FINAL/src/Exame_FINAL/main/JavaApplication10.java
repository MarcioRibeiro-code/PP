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
        System.out.println("1 - Gerir Construction Site");
        System.out.println("2 - Gerir Equipas");
        System.out.println("3 - Gerir Eventos");
        System.out.println("0 - Sair");
        System.out.print("opcao: ");
        key = scan.nextInt();
        while (key != 0) {

            switch (key) {
                case 1:
                    while (key != 0) {
                        System.out.println("1 - Adicionar Construction Site");
                        System.out.println("2 - Listar Construction Site [" + mn1.getConstruction_Site().length + "/" + mn1.getConstruction_Site_SIZE() + "]");
                        System.out.println("3 - Adicionar Equipa a um Construction Site");
                        System.out.print("opcao: ");
                        key = scan.nextInt();
                        switch (key) {
                            case 1:
                                System.out.print("Nome: ");
                                String Name = scan.next();
                                System.out.print("Location: ");
                                String Location = scan.next();

                                 {
                                    try {
                                        mn1.addConstruction_Site(Name, Location);
                                    } catch (ManagerException ex) {
                                        System.out.println(ex.toString());
                                    }
                                }
                                break;

                            case 2:

                                for (ConstructionSite cs1 : mn1.getConstruction_Site()) {
                                    System.out.println(cs1.toString());
                                }
                                break;

                        }
                    }
                    break;

                case 2:
                    while (key != 0) {
                        System.out.println("1 - Criar Equipa");
                        System.out.println("2 - Listar Equipas [" + mn1.getTeam().length + "]");
                        System.out.println("3 - Remover Funcionario de Uma Equipa");
                        System.out.print("opcao: ");
                        key = scan.nextInt();
                        switch (key) {
                            case 1:
                                System.out.print("Nome da Equipa: ");
                                String Name_Team = scan.next();
                                System.out.print("Numero de funcionarios: ");
                                int num_funcionarios = scan.nextInt();
                                Team_ tm1 = new Team_(Name_Team);

                                for (int i = 0; i < num_funcionarios; i++) {
                                    System.out.print("Nome do Funcionario: ");
                                    String Name = scan.next();
                                    System.out.println("Employee Type");
                                    System.out.println("Worker");
                                    System.out.println("Team Leader");
                                    System.out.println("Manager");
                                    System.out.print("Tipo: ");
                                    String tipo = scan.next();

                                    Employee_ empl;
                                    switch (tipo.toUpperCase()) {

                                        case "WORKER":
                                            empl = new Employee_(Name, EmployeeType.WORKER);
                                            break;
                                        case "TEAM LEADER":
                                            empl = new Employee_(Name, EmployeeType.TEAM_LEADER);
                                            break;
                                        case "MANAGER":
                                            empl = new Employee_(Name, EmployeeType.MANAGER);
                                            break;
                                        default:
                                            empl = new Employee_(Name, EmployeeType.WORKER);
                                            break;
                                    }
                                    try {
                                        tm1.addEmployees(empl);
                                        System.out.println(tm1.getNumberOfEmployees());
                                    } catch (TeamException ex) {
                                        System.out.println(ex.toString());
                                    }
                                }
                                 {
                                    try {
                                        mn1.addTeam(tm1);
                                    } catch (ManagerException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }
                                break;

                            case 2:
                                for (Team tmp : mn1.getTeam()) {
                                    System.out.println(tmp.toString());
                                }
                                break;

                            case 3:
                                int contador = 0;
                                System.out.println("Remover Funcionario de Uma Equipa");
                                for (Team tmp : mn1.getTeam()) {

                                    System.out.println((contador + 1) + tmp.getName());
                                    contador++;
                                }

                                System.out.println("0 - Sair");
                                System.out.print("opcao: ");
                                key = scan.nextInt();

                                if (key == 0) {
                                    break;
                                }

                                Team[] tm = mn1.getTeam();

                                contador = 0;

                                Employee[] emps = tm[key - 1].getEmployees();
                                for (int i = 0; i < tm[key - 1].getNumberOfEmployees(); i++) {

                                    System.out.println((contador + 1) + emps[i].toString());
                                }

                                System.out.println("0 - Sair");
                                System.out.print("Opcao:");
                                int emp_key = scan.nextInt();

                                if (emp_key == 0) {
                                    break;
                                }

                                Employee[] em = tm[key - 1].getEmployees();

                                 {
                                    try {
                                        tm[key - 1].removeEmployees(em[emp_key - 1]);
                                    } catch (TeamException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }

                                break;

                        }

                    }

                default:
                    break;
            }
            System.out.println("1 - Criar Equipa");
            System.out.println("2 - Listar Equipas [" + mn1.getTeam().length + "]");
            System.out.println("3 - Remover Funcionario de Uma Equipa");
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
