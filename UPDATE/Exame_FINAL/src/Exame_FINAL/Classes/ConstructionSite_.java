/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Equipment;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;
import java.time.LocalDate;
import estgconstroi.exceptions.ConstructionSiteException;
//UTILIZADO NO TOSTRING
import java.util.Arrays;

/**
 *
 * @author PC
 */
public class ConstructionSite_ implements ConstructionSite {

    private final String Name;
    private final String Location;
    private String Permit;
    private LocalDate PermitExpirationDate;
    private Employee Responsible;
    private Team[] Team;
    private Equipment[] Equipment;
    // private EquipmentType equipment;

    public ConstructionSite_(String Name, String Location) {
        this.Name = Name;
        this.Location = Location;
        this.Team = new Team[4];
        this.Equipment = new Equipment[10];
    }

    /**
     * Returns the name of the construction site.
     *
     * @return this.Name - returns the name of the construction site.
     */
    @Override
    public String getName() {
        return this.Name;
    }

    /**
     * Returns the location of the construction site.
     *
     *
     * @return this.Location - returns the location of the construction. site.
     */
    @Override
    public String getLocation() {
        return this.Location;
    }

    @Override
    public String getPermit() {
        return this.Permit;
    }

    @Override
    public LocalDate getPermitExpirationDate() {
        return this.PermitExpirationDate;
    }

    /**
     * Sets the identifier and expiration date of the construction permit.
     *
     *
     * @param string - The permit to be set.
     * @param ld - The expiration date of the permit.
     */
    @Override
    public void setPermit(String string, LocalDate ld) {
        this.Permit = string;
        this.PermitExpirationDate = ld;
    }

    @Override
    public Employee getResponsible() {
        return this.Responsible;
    }

    @Override
    public void setResponsible(Employee empl) throws ConstructionSiteException {
        if (empl.getType() != EmployeeType.MANAGER) {
            throw new ConstructionSiteException("The employee is not a Manager");
        }

    }

    @Override
    public void addTeam(Team team) throws ConstructionSiteException {

        if (team == null) {
            return;
        }

        if (this.getTeams().length != 0) {
            for (Team Team1 : this.getTeams()) {
                Team_ temp = (Team_) Team1;
                if (temp.equals((team))) {
                    throw new ConstructionSiteException("Team is already in the construction site");
                }
            }
        }

        //Verificar TAMANHO ARRAY e aumentar se necessario
        if (this.getTeams().length + 1 >= this.Team.length) {
            Team[] temp = new Team[this.Team.length * 2];
            System.arraycopy(this.Team, 0, temp, 0, this.getTeams().length);
            this.Team = temp;
        }

        this.Team[this.getTeams().length] = team;
    }

    @Override
    public void removeTeam(Team team) throws ConstructionSiteException {
        int pos = -1;

        if (this.getTeams().length != 0) {
            for (int i = 0; i < this.getTeams().length; i++) {
                Team_ temp = (Team_) this.Team[i];
                if (temp.equals(team)) {
                    pos = i;
                    break;
                }
            }
        }

        if (pos != -1) {
            Team[] temp = new Team[this.Team.length];
            for (int i = 0, k = 0; i < this.getTeams().length; i++) {

                if (i != pos) {
                    temp[k++] = this.Team[i];
                }

            }
            this.Team = temp;
        } else {
            throw new ConstructionSiteException("Team is not in the Construction Site");
        }
    }

    @Override
    public Team[] getTeams(String string) {

        Team[] temp = new Team[this.getTeams().length];
        int k = 0;

        for (int i = 0; i < this.getTeams().length; i++) {
            if (this.Team[i].getName().equals(string)) {
                temp[k++] = this.Team[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
    }

    @Override
    public Team[] getTeams() {
        //percorrer o array e obter as posicoes nao nulls
        int contador = 0;

        for (Team Team1 : this.Team) {
            if (Team1 != null) {
                contador++;
            }
        }

        Team[] temp = new Team[contador];

        System.arraycopy(this.Team, 0, temp, 0, contador);

        return temp;
    }

    private boolean allTeams_haveLeaders() {

        for (Team tm1 : this.getTeams()) {
            for (Employee empl1 : tm1.getEmployees()) {
                if (empl1.getType() != EmployeeType.MANAGER) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void addEquipment(Equipment eqpmnt) throws ConstructionSiteException {

        if (eqpmnt == null) {
            return;
        }

        if (this.getEquipment().length != 0) {
            for (Equipment equ1 : this.getEquipment()) {
                Equipment_ temp = (Equipment_) equ1;
                if (temp.equals((eqpmnt))) {
                    throw new ConstructionSiteException("The equipment is already in the construction site.");
                }
            }
        }

        //Verificar TAMANHO ARRAY e aumentar se necessario
        if (this.getEquipment().length + 1 >= this.Equipment.length) {
            Equipment[] temp = new Equipment[this.Equipment.length * 2];
            System.arraycopy(this.Equipment, 0, temp, 0, this.getEquipment().length);
            this.Equipment = temp;
        }

        this.Equipment[this.getEquipment().length] = eqpmnt;

    }

    @Override
    public void removeEquipment(Equipment eqpmnt) throws ConstructionSiteException {
        int pos = -1;

        if (this.getEquipment() != null) {
            for (int i = 0; i < this.getEquipment().length; i++) {
                Equipment eqpmt1 = (Equipment) this.Equipment[i];
                if (eqpmt1.equals(eqpmnt)) {
                    pos = i;
                    break;
                }
            }
        }

        if (pos != -1) {
            Equipment[] eqpmt1 = new Equipment[this.Equipment.length];
            for (int i = 0, k = 0; i < this.getEquipment().length; i++) {

                if (i != pos) {
                    eqpmt1[k++] = this.Equipment[i];
                }

            }
            this.Equipment = eqpmt1;
        }
    }

    /**
     * Returns the Equipment on the Construction Site with the given name.
     *
     *
     * @param string - Equipment name.
     * @return The Equipment on the Construction Site with the given name.
     */
    @Override
    public Equipment[] getEquipment(String string) {
        Equipment[] temp = new Equipment[this.getEquipment().length];
        int k = 0;

        for (int i = 0; i < this.getEquipment().length; i++) {
            if (this.Equipment[i].getName().equals(string)) {
                temp[k++] = this.Equipment[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
    }

    /**
     * Return the Equioment on the Construction Site with the given Status.
     *
     *
     * @param es - EquipmentStatus
     * @return The Equipment on the Construction Site with the given Status.
     */
    @Override
    public Equipment[] getEquipment(EquipmentStatus es) {
        Equipment[] temp = new Equipment[this.getEquipment().length];
        int k = 0;

        for (int i = 0; i < this.getEquipment().length; i++) {
            if (this.Equipment[i].getStatus().equals(es)) {
                temp[k++] = this.Equipment[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
    }

    /**
     * Returns the equipment of the construction site with the given type.
     *
     *
     * @param et - EquipmentType
     * @return The Equipment on the Construction Site with the given type.
     */
    @Override
    public Equipment[] getEquipment(EquipmentType et) {

        Equipment[] temp = new Equipment[this.getEquipment().length];
        int k = 0;

        for (int i = 0; i < this.getEquipment().length; i++) {
            if (this.Equipment[i].getType().equals(et)) {
                temp[k++] = this.Equipment[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
    }

    /**
     * Return the equipment on the construction site.
     *
     *
     * @return The Equipments on the Construction Site.
     */
    @Override
    public Equipment[] getEquipment() {
        int contador = 0;

        for (Equipment equ1 : this.Equipment) {
            if (equ1 != null) {
                contador++;
            }
        }

        Equipment[] temp = new Equipment[contador];

        System.arraycopy(this.Equipment, 0, temp, 0, contador);

        return temp;
    }

    /**
     * Verifies if the construction site is valid. To be valid the following
     * conditions must be met:
     * <ul>
     * <li>The responsible for the construction site must be a Manager.</li>
     * <li> At least on team must be present All teams must have a Team leader
     * TEAM_LEADER.</li>
     * <li> At least one equipment must be present with a status of
     * Operative.</li>
     * <li> The permit expiration date must the lower then the current date</li>
     * </ul>
     *
     *
     *
     *
     *
     * @return true if the construction site is valide, false otherwise.
     */
    @Override
    public boolean isValid() {
        if (this.Responsible.getType() != EmployeeType.MANAGER) {
            return false;
        }
        if (this.getTeams().length < 1) {
            return false;
        }

        if (this.allTeams_haveLeaders() == false) {
            return false;
        }

        if (this.getEquipment(EquipmentStatus.OPERATIVE).length == 0) {
            return false;
        }

        if (this.getPermitExpirationDate().compareTo(LocalDate.now()) > 0) {
            return false;
        }

        //FUNCAO OBTER SE AS EQUIPAS TEM TEAM_LEADER
        return true;
    }

    @Override
    public String toString() {

        String Team_s = "";
        for (Team tm1 : this.getTeams()) {
            Team_s += tm1.toString();
        }

        return "ConstructionSite_{" + "Name=" + Name + ", Location=" + Location + ", Permit=" + Permit + ", PermitExpirationDate=" + PermitExpirationDate + ", Responsible=" + Responsible + ", Team=" + Team_s + ", TEAM SIZE=" + this.Team.length + ", Equipment=" + Arrays.toString(this.getEquipment()) + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConstructionSite_ other = (ConstructionSite_) obj;
        if (!this.Name.equals(other.Name)) {
            return false;
        }
        if (!this.Location.equals(other.Location)) {
            return false;
        }
        if (!this.Permit.equals(other.Permit)) {
            return false;
        }
        return true;
    }

}
