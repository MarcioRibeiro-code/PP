/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import Exame_FINAL.Exceptions.ManagerException;
import Exame_FINAL.Interface.IManager;
import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Equipment;
import estgconstroi.EventManager;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.exceptions.ConstructionSiteException;
import estgconstroi.exceptions.TeamException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Manager {

    private ConstructionSite[] ConstructionSite;
    private Team[] Team;
    private Employee[] Employee;
    private Equipment[] Equipment;
    private EventManager EventManager;

    public Manager() {
        this.ConstructionSite = new ConstructionSite[4];
        this.Team = new Team[4];
        this.Employee = new Employee[4];
        this.Equipment = new Equipment[4];
    }

    public void Manage_ConstructionSite() {
        int key = -1;

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n--Manage ConstructionSite--");
            System.out.println("1- Adicionar ConstructionSite");
            System.out.println("2- Remover ConstructionSite");
            System.out.println("3- Adicionar Permit/Responsible");
            System.out.println("4- Adicionar Equipa a um ConstructionSite");
            System.out.println("5- Remover Equipa de um ConstructionSite");
            System.out.println("6- Adicionar Equipment a um ConstructionSite");
            System.out.println("7- Remover Equipemnt de um ConstructionSite");
            System.out.println("8- isValid");
            System.out.println("9- Listar ConstructionSites");
            System.out.println("0 - Sair");
            key = scan.nextInt();

            switch (key) {
                //ADICIONAR CONSTRUCTION SITE
                case 1:
                    System.out.println("\n--Adicionar ConstructionSite--");
                    System.out.print("Name:");
                    String Name = scan.next();
                    System.out.print("Location:");
                    String Location = scan.next();

                     {
                        try {
                            this.addConstruction_Site(Name, Location);
                        } catch (ManagerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                //Remover ConstructionSite
                case 2:
                    int contador = 1;
                    System.out.println("\n--Remover ConstructionSite--");
                    for (ConstructionSite cs1 : this.getConstruction_Site()) {
                        System.out.println((contador++) + "- " + "Nome:" + cs1.getName() + ";Location:" + cs1.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            this.removeConstruction_Site(this.getConstruction_Site()[key - 1]);
                        } catch (ManagerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                //ADICIONAR PERMIT / RESPONSIBLE
                case 3:
                    System.out.println("\n--Adicionar Permit/Responsible--");
                    contador = 1;
                    for (ConstructionSite cs1 : this.getConstruction_Site()) {
                        System.out.println((contador++) + "- " + "Nome:" + cs1.getName() + ";Location:" + cs1.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    ConstructionSite cs = this.getConstruction_Site()[key - 1];
                    System.out.println("1- Adicionar Permit");
                    System.out.println("2- Adicionar Responsible");
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    switch (key) {
                        case 1:

                            System.out.print("Permit String:");
                            String string = scan.next();
                            System.out.println("Expiration date of the permit: ");
                            System.out.print("Dia: ");
                            String date = scan.next() + "/";
                            System.out.print("Mes: ");
                            date += scan.next() + "/";
                            System.out.print("Ano: ");
                            date += scan.next();

                            // SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            cs.setPermit(string, LocalDate.parse(date));
                            break;
                        case 2:
                            contador = 1;
                            for (Employee empl : this.getEmployee()) {
                                System.out.println((contador++) + " - Name:" + empl.getName() + ";Uuid" + empl.getUUID() + ";Type:" + empl.getType());
                            }
                            System.out.println("0 - Sair");
                            System.out.print("Opcao: ");
                            key = scan.nextInt();

                            if (key == 0) {
                                break;
                            }

                             {
                                try {
                                    cs.setResponsible(this.getEmployee()[key - 1]);
                                } catch (ConstructionSiteException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }

                            break;

                    }
                    break;

                //Adicionar uma Equipa;
                case 4:
                    System.out.println("\n--Adicionar Equipa a um ConstructionSite--");

                    contador = 1;
                    for (ConstructionSite cs1 : this.getConstruction_Site()) {
                        System.out.println((contador++) + "- " + "Nome:" + cs1.getName() + ";Location:" + cs1.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    cs = this.getConstruction_Site()[key - 1];

                    contador = 1;
                    for (Team tm : this.getTeam()) {
                        System.out.println((contador++) + "- Nome:" + tm.getName());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            cs.addTeam(this.getTeam()[key - 1]);
                        } catch (ConstructionSiteException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                //Remover Equipa
                case 5:
                    System.out.println("\n--Remover Equipa de um ConstructionSite--");

                    contador = 1;
                    for (ConstructionSite cs1 : this.getConstruction_Site()) {
                        System.out.println((contador++) + "- " + "Nome:" + cs1.getName() + ";Location:" + cs1.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    cs = this.getConstruction_Site()[key - 1];

                    contador = 1;
                    for (Team tm : cs.getTeams()) {
                        System.out.println((contador++) + "- Nome:" + tm.getName());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            cs.removeTeam(cs.getTeams()[key - 1]);
                        } catch (ConstructionSiteException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                //Adicionar Equipment
                case 6:
                    System.out.println("\n--Adicionar Equipment a um ConstructionSite--");
                    contador = 1;
                    for (ConstructionSite cs1 : this.getConstruction_Site()) {
                        System.out.println((contador++) + "- " + "Nome:" + cs1.getName() + ";Location:" + cs1.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    cs = this.getConstruction_Site()[key - 1];

                    contador = 1;
                    for (Equipment eqpm : this.getEquipment()) {
                        System.out.println((contador++) + " - Nome:" + eqpm.getName() + "; Tipo:" + eqpm.getType());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            cs.addEquipment(this.getEquipment()[key - 1]);
                        } catch (ConstructionSiteException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                //Remover Equipment
                case 7:
                    System.out.println("\n--Remover Equipemnt de um ConstructionSite--");

                    contador = 1;
                    for (ConstructionSite cs1 : this.getConstruction_Site()) {
                        System.out.println((contador++) + "- " + "Nome:" + cs1.getName() + ";Location:" + cs1.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    cs = this.getConstruction_Site()[key - 1];

                    contador = 1;
                    for (Equipment eqpm : cs.getEquipment()) {
                        System.out.println((contador++) + " - Nome:" + eqpm.getName() + "; Tipo:" + eqpm.getType());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            cs.addEquipment(cs.getEquipment()[key - 1]);
                        } catch (ConstructionSiteException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                //isValid()
                case 8:
                    System.out.println("\n--isValid--");
                    for (ConstructionSite cs_valid : this.getConstruction_Site()) {
                        System.out.println("Is Valid:" + cs_valid.isValid());
                    }
                    break;

                //Listar Dados
                case 9:
                    System.out.println("\n--Listar ConstructionSites--");
                    for (ConstructionSite cs_list : this.getConstruction_Site()) {
                        System.out.println(cs_list.toString());
                    }
                    break;
                default:
                    break;

            }
            //   key = -1;

        } while (key != 0);

    }

    private void addConstruction_Site(String Name, String Location) throws ManagerException {

        if ((Name == null || Name.isBlank()) && (Location == null || Location.isBlank())) {
            return;
        }

        ConstructionSite p0 = new ConstructionSite_(Name, Location);

        if (this.getConstruction_Site().length != 0) {
            for (ConstructionSite cs1 : this.getConstruction_Site()) {
                ConstructionSite_ temp = (ConstructionSite_) cs1;
                if (temp.equals((cs1))) {
                    throw new ManagerException("ConstructionSite is already in the software");
                }
            }
        }

        //Verificar TAMANHO ARRAY e aumentar se necessario
        if (this.getConstruction_Site().length + 1 >= this.ConstructionSite.length) {
            ConstructionSite[] temp = new ConstructionSite[this.ConstructionSite.length * 2];
            System.arraycopy(this.ConstructionSite, 0, temp, 0, this.getConstruction_Site().length);
            this.ConstructionSite = temp;
        }

        this.ConstructionSite[this.getConstruction_Site().length] = p0;

    }

    private void removeConstruction_Site(ConstructionSite p0) throws ManagerException {

        int pos = -1;

        if (this.getConstruction_Site().length != 0) {
            for (int i = 0; i < this.getConstruction_Site().length; i++) {
                ConstructionSite_ temp = (ConstructionSite_) this.ConstructionSite[i];
                if (temp.equals(p0)) {
                    pos = i;
                    break;
                }
            }
        }

        if (pos != -1) {
            ConstructionSite[] temp = new ConstructionSite[this.ConstructionSite.length];
            for (int i = 0, k = 0; i < this.getConstruction_Site().length; i++) {

                if (i != pos) {
                    temp[k++] = this.ConstructionSite[i];
                }

            }
            this.ConstructionSite = temp;
        } else {
            throw new ManagerException("ConstructionSite isn't in the software");
        }

    }

    private ConstructionSite[] getConstruction_Site() {

        //percorrer o array e obter as posicoes nao nulls
        int contador = 0;

        if (this.ConstructionSite.length != 0) {
            for (ConstructionSite cs1 : this.ConstructionSite) {
                if (cs1 != null) {
                    contador++;
                }
            }
        }

        ConstructionSite[] temp = new ConstructionSite[contador];

        System.arraycopy(this.ConstructionSite, 0, temp, 0, contador);

        return temp;
    }

    public int getConstruction_Site_SIZE() {
        return this.ConstructionSite.length;
    }

    private void addTeam(ConstructionSite p0, Team p1) {

        if (p0 == null || p1 == null) {
            return;
        }

        try {
            p0.addTeam(p1);
        } catch (ConstructionSiteException ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     *
     * TEAM
     *
     *
     *
     *
     *
     *
     *
     *
     */
    public void manage_Team() {
        int key = -1;

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n--Manage Team--");
            System.out.println("1 - Criar Equipa");
            System.out.println("2 - Adicionar Funcionario a uma Equipa");
            System.out.println("3 - Remover Funcionario de uma Equipa");
            System.out.println("4 - Listar Dados");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            key = scan.nextInt();

            switch (key) {
                case 1:

                    System.out.print("Nome Equipa: ");
                    String nome = scan.next();
                     {
                        try {
                            this.addTeam(nome);
                        } catch (ManagerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                case 2:

                    if (this.getTeam().length == 0 || this.getEmployee().length == 0) {
                        break;
                    }
                    int contador = 1;
                    System.out.println("--TEAM--");
                    for (Team tm : this.getTeam()) {
                        System.out.println((contador++) + "- " + tm.getName());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    Team tms_temp = this.getTeam()[key - 1];

                    contador = 1;
                    System.out.println("\n--Employee--");
                    for (Employee empl : this.getEmployee()) {
                        System.out.println((contador++) + " - Name:" + empl.getName() + "; Uuid: " + empl.getUUID());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            tms_temp.addEmployees(this.getEmployee()[key - 1]);
                        } catch (TeamException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;

                case 3:
                    if (this.getTeam().length == 0) {
                        break;
                    }
                    contador = 1;
                    System.out.println("--TEAM--");
                    for (Team tm : this.getTeam()) {
                        System.out.println((contador++) + "- " + tm.getName());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    tms_temp = this.getTeam()[key - 1];

                    if (tms_temp.getEmployees().length == 0) {
                        break;
                    }

                    contador = 1;
                    System.out.println("\n--Employee--");
                    for (Employee empl : tms_temp.getEmployees()) {
                        System.out.println((contador++) + " - Name:" + empl.getName() + "\nUuid" + empl.getUUID());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            tms_temp.removeEmployees(tms_temp.getEmployees()[key - 1]);
                        } catch (TeamException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;

                case 4:
                    for (Team tm : this.getTeam()) {
                        System.out.println(tm.toString());
                    }

                    break;

                default:
                    break;
            }
        } while (key != 0);

    }

    private void addTeam(String Name) throws ManagerException {

        if ((Name == null || Name.isBlank())) {
            return;
        }

        Team p0 = new Team_(Name);

        if (this.getTeam().length != 0) {
            for (Team cs1 : this.getTeam()) {
                Team_ temp = (Team_) cs1;
                if (temp.equals((p0))) {
                    throw new ManagerException("Team is already in the software");
                }
            }
        }

        //Verificar TAMANHO ARRAY e aumentar se necessario
        if (this.getTeam().length + 1 >= this.Team.length) {
            Team[] temp = new Team[this.ConstructionSite.length * 2];
            System.arraycopy(this.Team, 0, temp, 0, this.getTeam().length);
            this.Team = temp;
        }

        this.Team[this.getTeam().length] = p0;

    }

    private Team[] getTeam() {
        int contador = 0;

        if (this.Team.length != 0) {
            for (Team tm1 : this.Team) {
                if (tm1 != null) {
                    contador++;
                }
            }
        }

        Team[] temp = new Team[contador];

        System.arraycopy(this.Team, 0, temp, 0, contador);

        return temp;

    }

    /**
     * \
     *
     *
     * EMPLOYEE
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
    public void Manage_Employee() {
        int key = -1;

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n--Manage Employee--");
            System.out.println("1 - Criar Funcionario");
            System.out.println("2 - Remover Funcionario");
            System.out.println("3 - Alterar EmployeeType de um Funcionario");
            System.out.println("4 - Listar Dados");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            key = scan.nextInt();

            Employee[] temp_Employee = this.getEmployee();
            switch (key) {
                case 1:
                    System.out.println("\n--Criar Funcionario--");
                    System.out.print("Nome: ");
                    String name = scan.next();

                    int contador = 1;
                    System.out.println("EMPLOYEE TYPE");
                    for (EmployeeType emplType : EmployeeType.values()) {
                        System.out.println((contador++) + "- " + emplType);
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    this.addEmployee(name, EmployeeType.values()[key - 1]);
                    break;

                case 2:
                    System.out.println("\n--Remover Funcionario--");
                    contador = 0;
                    for (Employee empl : this.getEmployee()) {
                        System.out.println((contador + 1) + "- " + empl.getName() + ";" + empl.getUUID());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");

                    key = scan.nextInt();

                     {
                        try {
                            this.removeEmployee(temp_Employee[key - 1]);
                        } catch (ManagerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--Alterar EmployeeType de um Funcionario--");
                    contador = 1;
                    for (Employee empl : this.getEmployee()) {
                        System.out.println((contador++) + "- " + empl.getName() + ";" + empl.getUUID());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");

                    key = scan.nextInt();

                    Employee empl = temp_Employee[key - 1];

                    contador = 1;
                    for (EmployeeType emplType : EmployeeType.values()) {
                        System.out.println((contador++) + "- " + emplType);
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    switch (key) {
                        case 1:
                            empl.setType(EmployeeType.WORKER);
                            break;
                        case 2:
                            empl.setType(EmployeeType.MANAGER);
                            break;
                        case 3:
                            empl.setType(EmployeeType.TEAM_LEADER);
                            break;

                    }
                    break;

                case 4:
                    System.out.println("\n--Listar Dados--");
                    for (Employee empl_listing : this.getEmployee()) {
                        System.out.println(empl_listing.toString());
                    }
                    break;
                default:
                    break;
            }

        } while (key != 0);

    }

    private void addEmployee(String Name, EmployeeType Type) {

        if (Name.isEmpty() || Type == null) {
            return;
        }

        if (this.getEmployee().length + 1 >= this.Employee.length) {
            Employee[] temp = new Employee[this.Employee.length * 2];
            System.arraycopy(this.Employee, 0, temp, 0, this.getTeam().length);
            this.Employee = temp;
        }

        this.Employee[this.getEmployee().length] = new Employee_(Name, Type);
    }

    private Employee[] getEmployee() {
        int contador = 0;

        if (this.Employee.length != 0) {
            for (Employee empl : this.Employee) {
                if (empl != null) {
                    contador++;
                }
            }
        }

        Employee[] temp = new Employee[contador];

        System.arraycopy(this.Employee, 0, temp, 0, contador);

        return temp;
    }

    private void removeEmployee(Employee empl) throws ManagerException {
        int pos = -1;

        if (this.getEmployee().length != 0) {
            for (int i = 0; i < this.getEmployee().length; i++) {
                Employee_ temp = (Employee_) this.Employee[i];
                if (temp.equals(empl)) {
                    pos = i;
                    break;
                }
            }
        }

        if (pos != -1) {
            Employee[] temp = new Employee[this.Employee.length];
            for (int i = 0, k = 0; i < this.getEmployee().length; i++) {

                if (i != pos) {
                    temp[k++] = this.Employee[i];
                }

            }
            this.Employee = temp;
        } else {
            throw new ManagerException("Employe isn't in the software");
        }

    }

    /**
     *
     * EQUIPEMNT
     *
     *
     *
     *
     */
    public void ManageEquipment() {

        int key = -1;

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\nManage Equipment");
            System.out.println("1 - Criar Equipamento");
            System.out.println("2 - Remover Equipamento");
            System.out.println("3 - Alterar Status de um Equipamento");
            System.out.println("4 - Listar Dados");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            key = scan.nextInt();

            switch (key) {
                case 1:
                    System.out.print("Name:");
                    String name = scan.next();

                    int contador = 1;
                    for (EquipmentType eqType : EquipmentType.values()) {
                        System.out.println((contador++) + "- " + eqType);
                    }

                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    int eqType = scan.nextInt();

                    if (eqType == 0) {
                        break;
                    }

                    contador = 1;
                    for (EquipmentStatus eqStatus : EquipmentStatus.values()) {
                        System.out.println((contador++) + "- " + eqStatus);
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    int eqStatus = scan.nextInt();

                    if (eqStatus == 0) {
                        break;
                    }

                    this.addEquipment(name, EquipmentType.values()[eqType - 1], EquipmentStatus.values()[eqStatus - 1]);

                    break;
                case 2:
                    contador = 1;
                    for (Equipment eqpm : this.getEquipment()) {
                        System.out.println((contador++) + " - Nome:" + eqpm.getName() + "; Tipo:" + eqpm.getType());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            this.removeEquipment(this.getEquipment()[key - 1]);
                        } catch (ManagerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;

                case 3:
                    contador = 1;
                    for (Equipment eqpm : this.getEquipment()) {
                        System.out.println((contador++) + " - Nome:" + eqpm.getName() + "; Tipo:" + eqpm.getType());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    contador = 1;
                    for (EquipmentStatus eqStatus_tmp : EquipmentStatus.values()) {
                        System.out.println((contador++) + "- " + eqStatus_tmp);
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    eqStatus = scan.nextInt();

                    if (eqStatus == 0) {
                        break;
                    }

                    this.Equipment[key - 1].setStatus(EquipmentStatus.values()[eqStatus - 1]);

                    break;
                case 4:

                    for (Equipment eqpmnt : this.getEquipment()) {
                        System.out.println(eqpmnt.toString());
                    }

                    break;
            }

        } while (key != 0);

    }

    private void addEquipment(String Name, EquipmentType Type, EquipmentStatus Status) {

        if (Name.isEmpty() || Type == null || Status == null) {
            return;
        }

        if (this.getEquipment().length + 1 >= this.Equipment.length) {
            Equipment[] temp = new Equipment[this.Equipment.length * 2];
            System.arraycopy(this.Equipment, 0, temp, 0, this.getEquipment().length);
            this.Equipment = temp;
        }

        this.Equipment[this.getEquipment().length] = new Equipment_(Name, Type, Status);
    }

    private Equipment[] getEquipment() {
        int contador = 0;

        Equipment[] temp = new Equipment[this.Employee.length];

        if (this.Equipment.length != 0) {
            for (Equipment empl : this.Equipment) {
                if (empl != null) {
                    temp[contador++] = empl;
                }
            }
        }

        System.arraycopy(this.Equipment, 0, temp, 0, contador);

        return temp;
    }

    private void removeEquipment(Equipment eqm) throws ManagerException {
        int pos = -1;

        if (this.getEquipment().length != 0) {
            for (int i = 0; i < this.getEquipment().length; i++) {
                Equipment_ temp = (Equipment_) this.Equipment[i];
                if (temp.equals(eqm)) {
                    pos = i;
                    break;
                }
            }
        }

        if (pos != -1) {
            Equipment[] temp = new Equipment[this.Equipment.length];
            for (int i = 0, k = 0; i < this.getEquipment().length; i++) {

                if (i != pos) {
                    temp[k++] = this.Equipment[i];
                }

            }
            this.Equipment = temp;
        } else {
            throw new ManagerException("Equipment isn't in the software");
        }

    }

}
