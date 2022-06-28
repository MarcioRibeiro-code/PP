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

import estgconstroi.Employee;
import estgconstroi.enums.EmployeeType;

/**
 *
 * @author PC
 */
public class Employee_ extends Employee {

    private final String Name;
    private EmployeeType Type;

    /**
     *
     * @param Name
     * @param Type
     */
    public Employee_(String Name, EmployeeType Type) {
        super();
        this.Name = Name;
        this.Type = Type;
    }

    /**
     * Getter of uuid(Universal Identifier), a 128-bit bvalue used to uniquely
     * identify an object or entity on the internet.
     *
     * @return the uuid.
     */
    @Override
    public String getUUID() {
        return super.getUUID();
    }

    /**
     * Getter for name
     * @return the name.
     */
    @Override
    public String getName() {
        return this.Name;
    }

    /**
     * Getter for type
     * @return  the type.
     */
    @Override
    public EmployeeType getType() {
        return this.Type;
    }

    /**
     * Sets the employees type
     * @param et - the employee's type.
     */
    @Override
    public void setType(EmployeeType et) {
        this.Type = et;
    }

    /**
     * Returns a string representation of the Employee.
     * @overrides toString in class java.lang.object
     * @return a string representation of the employee.
     */
    @Override
    public String toString() {
        return "\n--Employee--" + "\nName:" + this.getName() + "\nUUID:" + this.getUUID() + "\nType:" + this.getType().toString();
    }

}
