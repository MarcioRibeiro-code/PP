/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.Employee;
import estgconstroi.enums.EmployeeType;

/**
 *
 * @author PC
 */
public class Employee_ extends Employee {

    private final String Name;
    private EmployeeType Type;

    public Employee_(String Name, EmployeeType Type) {
        super();
        this.Name = Name;
        this.Type = Type;
    }

    @Override
    public String getUUID() {
        return super.getUUID();
    }

    @Override
    public String getName() {
        return this.Name;
    }

    @Override
    public EmployeeType getType() {
        return this.Type;
    }

    @Override
    public void setType(EmployeeType et) {
        this.Type = et;
    }

    @Override
    public String toString() {
        return "--Employee--" + "\nName:" + this.getName() + "\nUUID:" + this.getUUID() + "\nType:" + this.getType().toString();
    }

}
