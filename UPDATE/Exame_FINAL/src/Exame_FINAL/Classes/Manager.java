/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import Exame_FINAL.Exceptions.ManagerException;
import Exame_FINAL.Interface.IManager;
import estgconstroi.ConstructionSite;
import estgconstroi.Equipment;
import estgconstroi.EventManager;
import estgconstroi.Team;
import estgconstroi.exceptions.ConstructionSiteException;

/**
 *
 * @author PC
 */
public class Manager implements IManager {

    private ConstructionSite[] ConstructionSite;
    private Team[] Team;
    private Equipment[] Equipment;
    private EventManager EventManager;

    public Manager() {
        this.ConstructionSite = new ConstructionSite[4];
        this.Team = new Team[4];
        this.Equipment = new Equipment[4];
    }

    @Override
    public void addConstruction_Site(String Name, String Location) throws ManagerException {

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

    @Override
    public void removeConstruction_Site(ConstructionSite p0) throws ManagerException {

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

    @Override
    public ConstructionSite[] getConstruction_Site() {

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

    @Override
    public void addTeam(ConstructionSite p0, Team p1) {

        if (p0 == null || p1 == null) {
            return;
        }

        try {
            p0.addTeam(p1);
        } catch (ConstructionSiteException ex) {
            System.out.println(ex.toString());
        }

    }

    public Team[] getTeam() {
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

    public void addTeam(Team p0) throws ManagerException {

        if (p0 == null) {
            return;
        }

        //GET TEAM
        if (this.getTeam().length != 0) {
            for (Team tm1 : this.getTeam()) {
                ConstructionSite_ temp = (ConstructionSite_) tm1;
                if (temp.equals((tm1))) {
                    throw new ManagerException("Team is already in the software");
                }
            }
        }

        //Verificar TAMANHO ARRAY e aumentar se necessario
        if (this.getTeam().length + 1 >= this.Team.length) {
            Team[] temp = new Team[this.Team.length * 2];
            System.arraycopy(this.Team, 0, temp, 0, this.getTeam().length);
            this.Team = temp;
        }

        this.Team[this.getTeam().length] = p0;

    }
}
