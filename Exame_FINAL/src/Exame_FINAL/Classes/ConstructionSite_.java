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

    public ConstructionSite_(String Name, String Location) {
        this.Name = Name;
        this.Location = Location;
        this.Team = new Team[10];
        this.Equipment = new Equipment[10];
    }

    public ConstructionSite_(String Name, String Location, String Permit, LocalDate PermitExpirationDate) {
        this.Name = Name;
        this.Location = Location;
        this.Permit = Permit;
        this.PermitExpirationDate = PermitExpirationDate;
        this.Team = new Team[10];
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

        if (this.getTeams() != null) {
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

        this.Team[this.getTeams().length + 1] = team;
    }

    @Override
    public void removeTeam(Team team) throws ConstructionSiteException {
        int pos = -1;

        if (this.getTeams() != null) {
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

    @Override
    public void addEquipment(Equipment eqpmnt) throws ConstructionSiteException {

    }

    @Override
    public void removeEquipment(Equipment eqpmnt) throws ConstructionSiteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipment[] getEquipment(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipment[] getEquipment(EquipmentStatus es) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Equipment[] getEquipment(EquipmentType et) {

        Equipment[] temp = new Equipment[this.getTeams().length];
        int k = 0;

        for (int i = 0; i < this.getTeams().length; i++) {
            if (this.Equipment[i].getType().equals(et)) {
                temp[k++] = this.Equipment[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
    }

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

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
