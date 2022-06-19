/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.Employee;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.exceptions.TeamException;
//USADO NO TOSTRING
import java.util.Arrays;

/**
 *
 * @author PC
 */
public class Team_ implements Team {

    private final String Name;
    private int NumberOfEmployess;
    private Employee_[] Employes;

    public Team_(String Name) {
        this.NumberOfEmployess = 0;
        this.Name = Name;
        this.Employes = new Employee_[10];
    }

    /**
     * java.lang.String getName() returns the team's name
     *
     *
     * @return the team's name
     *
     */
    @Override
    public String getName() {

        return this.Name;
    }

    /**
     * int getNumberOfEmployees() returns the team's size
     *
     * @return the team's size
     */
    @Override
    public int getNumberOfEmployees() {

        return this.NumberOfEmployess;
    }

    /**
     *
     * void addEmployees​(Employee employee) throws TeamException adds an
     * employee to the team
     *
     * @param empl - the employee to be added
     *
     * @throws TeamException - if the employee is already in the team
     */
    @Override
    public void addEmployees(Employee empl) throws TeamException {
        for (int i = 0; i < this.getNumberOfEmployees(); i++) {
            if (Employes[i].getUUID().equals(empl.getUUID())) {
                throw new TeamException("Employee is already in the team");
            }
        }

        // Expand array if this.getNumberOfEmployess() + 1 >= this
        if (this.getNumberOfEmployees() + 1 >= this.Employes.length) {
            Employee_[] temp = new Employee_[this.Employes.length * 2];
            System.arraycopy(this.Employes, 0, temp, 0, this.getNumberOfEmployees());
            this.Employes = temp;
        }

        this.Employes[this.getNumberOfEmployees()] = (Employee_) empl;
        this.NumberOfEmployess++;
    }

    /**
     * void removeEmployees​(Employee employee) throws TeamException
     *
     * removes an employess from the team
     *
     * @param empl - the employee to be removed
     * @throws TeamException - if the employee is not in the team
     *
     */
    @Override
    public void removeEmployees(Employee empl) throws TeamException {

        int pos = -1;

        for (int i = 0; i < this.getNumberOfEmployees(); i++) {
            if (Employes[i].getUUID().equals(empl.getUUID())) {

                pos = i;
                break;
            }
        }
        if (pos != -1) {
            Employee_[] temp = new Employee_[this.Employes.length];
            for (int i = 0, k = 0; i < this.getNumberOfEmployees(); i++) {

                if (i != pos) {
                    temp[k++] = this.Employes[i];
                }

            }
            this.Employes = temp;
            this.NumberOfEmployess--;
        } else {
            throw new TeamException("Employee is not in the team");
        }

    }

    @Override
    public Employee[] getEmployees(String string) {
        int ARRAY_SIZE = 4;
        Employee[] temp_empl = new Employee[ARRAY_SIZE];
        int contador = 0;

        for (int i = 0; i < this.getNumberOfEmployees(); i++) {
            if (this.Employes[i].getName().equals(string)) {

                //Verificar TAMANHO se ++Contador = temp.empl.size Aumentar
                temp_empl[contador++] = this.Employes[i];
            }
        }
        return temp_empl;
    }

    @Override
    public Employee[] getEmployees(EmployeeType et) {
        int ARRAY_SIZE = 4;
        Employee[] temp_empl = new Employee[ARRAY_SIZE];
        int contador = 0;

        for (int i = 0; i < this.getNumberOfEmployees(); i++) {
            if (this.Employes[i].getType().equals(et)) {
                //Verificar TAMANHO se ++Contador = temp.empl.size Aumentar

                temp_empl[contador++] = this.Employes[i];
            }
        }
        return temp_empl;
    }

    @Override
    public Employee[] getEmployees() {
        return this.Employes;
    }

    @Override
    public String toString() {

        String Employee_S = "";

        for (int i = 0; i < this.getNumberOfEmployees(); i++) {
            Employee_S += this.Employes[i].toString() + ("\n");
        }

        return "--Team--" + "\nName:" + Name + "\nNumberOfEmployess:" + NumberOfEmployess + "\nEmployes:" + Employee_S;
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
        final Team_ other = (Team_) obj;

        if (!this.Name.equals(other.Name)) {
            return false;
        }
        return true;
    }

}
