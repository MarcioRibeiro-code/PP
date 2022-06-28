/* 
* Nome: Marcio Samuel Santos Ribeiro
* Número: 8200408
* Turma: LEI2T4
* 
* Nome: Hugo Miguel Gomes Alves Ribeiro
* Número: 8200441
* Turma: LEI2T3
*/
package Exame_FINAL.Classes;

import Exame_FINAL.Exceptions.ManagerException;
import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Equipment;
import estgconstroi.Event;
import estgconstroi.InsuranceReporter;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.ConstructionSiteException;
import estgconstroi.exceptions.EventManagerException;
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
    private EventManager_ EventManager;

    /**
     *
     */
    public Manager() {
        this.ConstructionSite = new ConstructionSite[4];
        this.Team = new Team[4];
        this.Employee = new Employee[4];
        this.Equipment = new Equipment[4];
        this.EventManager = new EventManager_();
    }

    /**
     * Sub-Menu where Constructions can be managed, like adding a new
     * Construction Site to the software, remove it. The same goes for the Teams
     * and Equipemnt.
     */
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
            System.out.print("Opcao:");
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
                            System.out.println("Adicionado com Sucesso");
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
                            System.out.println("Removido com Sucesso");
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

                    ConstructionSite_ cs = (ConstructionSite_) this.getConstruction_Site()[key - 1];
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
                            System.out.print("Data (yyyy-MM-dd): ");
                            String date = scan.next();

                            //Problemas em Transformar para dd-MM-YY, throws Exception
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

                    cs = (ConstructionSite_) this.getConstruction_Site()[key - 1];

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

                    cs = (ConstructionSite_) this.getConstruction_Site()[key - 1];

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

                    cs = (ConstructionSite_) this.getConstruction_Site()[key - 1];

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

                    cs = (ConstructionSite_) this.getConstruction_Site()[key - 1];

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

    /**
     * Adds an Construction Site to the Manager.
     *
     *
     * @param Name - Name of the ConstructionSite
     * @param Location - Location of the Construction Site
     * @throws ManagerException - if the ConstructionSite already exists.
     */
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
        }

        this.ConstructionSite[this.getConstruction_Site().length] = p0;

    }

    /**
     * Removes an Construction Site from the Manager.
     *
     * @param p0 - the Construction Site to be removed
     * @throws ManagerException - if the ConstructionSite is not in the Manager.
     */
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

    /**
     * Returns all the Construction Sites
     *
     * @return ConstructionSite - all the Construction Sites contained in the
     * Manager.
     */
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

    /**
     * Sub-Menu where Teams can be managed, like adding a new Team to the
     * software, remove it. The same goes for the adding an Employee to a Team.
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

    /**
     * Adds an Team to The Manager
     *
     * @param Name - Name of the Team to be created.
     * @throws ManagerException - if the Team already exists.
     */
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
        }

        this.Team[this.getTeam().length] = p0;

    }

    /**
     * Returns all the Teams contained in the Manager.
     *
     * @return Team[] - All the Team contained in the Manager.
     */
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
     * Sub-Menu where Employess can be managed, like adding a new Employee to
     * the software, remove it and altering data.
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
                    contador = 1;
                    for (Employee empl : this.getEmployee()) {
                        System.out.println((contador++) + "- " + empl.getName() + ";" + empl.getUUID());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");

                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

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

    /**
     * Adds an Employee to the Manager
     *
     * @param Name - Name of the Employee to be created.
     * @param Type - EmployeeType of the Employee to be created
     */
    private void addEmployee(String Name, EmployeeType Type) {

        if (Name.isEmpty() || Type == null) {
            return;
        }

        if (this.getEmployee().length + 1 >= this.Employee.length) {
            Employee[] temp = new Employee[this.Employee.length * 2];
            System.arraycopy(this.Employee, 0, temp, 0, this.getTeam().length);
        }

        this.Employee[this.getEmployee().length] = new Employee_(Name, Type);
    }

    /**
     * Returns all the Employees contained in the Manger
     *
     * @return Employee - All the Employees contained in the Manager.
     */
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

    /**
     * Removes an Employee from the Team
     *
     * @param empl - The employee to be removed
     * @throws ManagerException - if the employee doesnt exist
     */
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
     * Sub-Menu where Equipment can be managed, like adding a new Equipment to
     * the software, remove it and alter data.
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

    /**
     * Adds an Equipment to the Manager.
     *
     * @param Name - Name of the Equipment to be created.
     * @param Type - Equipment Type
     * @param Status - Equipemnt Status
     */
    private void addEquipment(String Name, EquipmentType Type, EquipmentStatus Status) {

        if (Name.isEmpty() || Type == null || Status == null) {
            return;
        }

        if (this.getEquipment().length + 1 >= this.Equipment.length) {
            Equipment[] temp = new Equipment[this.Equipment.length * 2];
            System.arraycopy(this.Equipment, 0, temp, 0, this.getEquipment().length);

        }

        this.Equipment[this.getEquipment().length] = new Equipment_(Name, Type, Status);
    }

    /**
     * Returns all the Equipemnt contained in the Manager.
     *
     * @return Equipment - Equipment contained in the Manager.
     */
    private Equipment[] getEquipment() {
        int contador = 0;

        if (this.Equipment.length != 0) {
            for (Equipment empl : this.Equipment) {
                if (empl != null) {
                    contador++;
                }
            }
        }
        Equipment[] temp = new Equipment[contador];
        System.arraycopy(this.Equipment, 0, temp, 0, contador);

        return temp;
    }

    /**
     * Remove a Equipment from the Manager.
     *
     * @param eqm - Equipment to be removed.
     * @throws ManagerException - if the equipment doesnt exist.
     */
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

    /**
     * Sub-Menu where Events can be managed, like reporting a new Event to the
     * software, remove it and Report to the Insurance using an API.
     *
     */
    public void Manage_Event() {

        int key = -1;

        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("\n--Manage Event--");
            System.out.println("1 - Report Event");
            System.out.println("2 - Report to Insurance");
            System.out.println("3 - Listar Todos Events");
            System.out.println("4 - Remover Evento (todos/um)");
            System.out.println("5 - Remover Todos os Eventos da API");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            key = scan.nextInt();

            switch (key) {
                case 1:
                    int contador = 1;
                    System.out.println("\n--New Event--");
                    System.out.println("--Construction Site--");
                    for (ConstructionSite cs : this.getConstruction_Site()) {
                        System.out.println((contador++) + " - Nome:" + cs.getName() + "; Location:" + cs.getLocation());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao:");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    ConstructionSite cs = this.getConstruction_Site()[key - 1];

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

                    Team tm = cs.getTeams()[key - 1];
                    System.out.println("--Reporter--");
                    contador = 1;
                    for (Employee empl : tm.getEmployees()) {
                        System.out.println((contador++) + " - Name:" + empl.getName() + "; Uuid:" + empl.getUUID());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao: ");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    Employee empl = tm.getEmployees()[key - 1];

                    System.out.print("Details:");
                    String Details = scan.next();
                    System.out.print("Notification Message:");
                    String NotificationMessage = scan.next();
                    System.out.print("Title:");
                    String Title = scan.next();

                    contador = 1;
                    System.out.println("\n--Event Priority--");
                    for (EventPriority ep : EventPriority.values()) {
                        System.out.println((contador++) + "- " + ep.toString());
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao:");
                    key = scan.nextInt();

                    EventPriority evPriority = EventPriority.values()[key - 1];
                    if (key == 0) {
                        break;
                    }

                    System.out.println("\n--Event Type--");
                    System.out.println("1 - Failure");
                    System.out.println("2 - Incident");
                    System.out.println("3 - Accident");
                    System.out.println("0 - Sair");
                    System.out.print("Opcao:");
                    int ev_type = scan.nextInt();

                    if (ev_type == 0) {
                        break;
                    }
                    switch (ev_type) {
                        //FAILURE
                        case 1:

                            contador = 1;
                            System.out.println("--Equipment--");
                            for (Equipment eqpmnt : cs.getEquipment()) {
                                System.out.println((contador++) + " - Name:" + eqpmnt.getName() + "; Type:" + eqpmnt.getType());
                            }
                            System.out.println("0 - Sair");
                            System.out.print("Opcao:");
                            key = scan.nextInt();

                            if (key == 0) {
                                break;
                            }
                            Equipment eqpmnt = cs.getEquipment()[key - 1];

                             {
                                try {

                                    this.EventManager.reportEvent(new Failure_(eqpmnt, cs, Details, NotificationMessage, EventPriority.values()[key - 1], Title, empl));
                                } catch (EventManagerException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                            break;
                        //INCIDENT
                        case 2: {
                            try {
                                this.EventManager.reportEvent(new Incident_(cs, Details, NotificationMessage, EventPriority.values()[key - 1], Title, empl));
                            } catch (EventManagerException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                        break;

                        case 3:

                            System.out.println("--Injuried Employee--");
                            contador = 1;
                            for (Employee empl_in : tm.getEmployees()) {
                                System.out.println((contador++) + " - Name:" + empl_in.getName() + "; Uuid:" + empl_in.getUUID());
                            }
                            System.out.println("0 - Sair");
                            System.out.print("Opcao: ");
                            key = scan.nextInt();

                            if (key == 0) {
                                break;
                            }

                             {
                                try {
                                    this.EventManager.reportEvent(new Accident_(tm.getEmployees()[key - 1], cs, Details, NotificationMessage, evPriority, Title, empl));
                                } catch (EventManagerException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }

                            break;

                        default:
                            break;

                    }

                    break;

                //Report Insurance
                case 2:

                    contador = 1;
                    System.out.println("\n--Report To Insurance--");

                    for (Event evnt : this.EventManager.getEvent()) {

                        if (evnt instanceof Failure_) {
                            if (((Failure_) evnt).isInsurance_Notified() == false) {
                                System.out.println((contador++) + "- Title:" + evnt.getTitle() + "; Uuid:" + evnt.getUuid());
                            }
                        } else if (evnt instanceof Accident_) {
                            if (((Accident_) evnt).isInsurance_Notified() == false) {
                                System.out.println((contador++) + "- Title:" + evnt.getTitle() + "; Uuid:" + evnt.getUuid());
                            }
                        } else if (evnt instanceof Incident_) {
                            if (((Incident_) evnt).isInsurance_Notified() == false) {
                                System.out.println((contador++) + "- Title:" + evnt.getTitle() + "; Uuid:" + evnt.getUuid());
                            }
                        }
                    }
                    System.out.println("0 - Sair");
                    System.out.print("Opcao:");
                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                     {
                        try {
                            this.EventManager.Report_toInsurance(this.EventManager.getEvent()[key - 1]);
                        } catch (EventManagerException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                    break;
                //Listar Eventos
                case 3:
                    for (Event envt : this.EventManager.getEvent()) {
                        /* if (envt instanceof Failure_) {
                            System.out.println(((Failure_) envt).toString());
                        } else if (envt instanceof Incident_) {
                            System.out.println(((Incident_) envt).toString());
                        } else if (envt instanceof Accident_) {
                            System.out.println(((Accident_) envt).toString());
                        }*/
                        System.out.println(envt.toString());
                    }

                    break;
                //Remover Eventos
                case 4:
                    System.out.println("\n--Remove Event--");
                    System.out.println("1 - Todos");
                    System.out.println("2 - Apenas um");
                    System.out.println("0 - Sair");
                    System.out.print("Opcao:");

                    key = scan.nextInt();

                    if (key == 0) {
                        break;
                    }

                    switch (key) {
                        case 1:
                            this.EventManager.removeAllEvents();
                            System.out.println("Removido todos os eventos");
                            break;
                        case 2:
                            contador = 1;
                            for (Event evnt : this.EventManager.getEvent()) {
                                System.out.println((contador++) + "title:" + evnt.getTitle() + "; Uuid:" + evnt.getUuid() + "; Data:" + evnt.getDate().toString());
                            }
                            System.out.println("0 - Sair");
                            System.out.print("Opcao:");

                            key = scan.nextInt();
                            if (key == 0) {
                                break;
                            }

                             {
                                try {
                                    this.EventManager.removeEvent(this.EventManager.getEvent()[key - 1]);
                                    System.out.println("Retirado com sucesso");
                                } catch (EventManagerException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }

                            break;

                    }

                    break;
                case 5: {
                    try {
                        InsuranceReporter.resetEvents("GywNgSAn4bCxayZ", "Grupo3");
                    } catch (IOException | InterruptedException ex) {
                        System.out.println("Error - Removing the Events in the API");
                    }
                }
                break;

            }

        } while (key != 0);

    }

}
