/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Interface;

import Exame_FINAL.Exceptions.ManagerException;
import estgconstroi.ConstructionSite;
import estgconstroi.Team;

/**
 *
 * @author PC
 */
public interface IManager {

    void addConstruction_Site(String Name, String Location) throws ManagerException;

    void removeConstruction_Site(final ConstructionSite p0) throws ManagerException;

    ConstructionSite[] getConstruction_Site();

    void addTeam(final ConstructionSite p0, final Team p1);
}
