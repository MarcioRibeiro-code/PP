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

import estgconstroi.Equipment;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EquipmentType;

/**
 *
 * @author PC
 */
public class Equipment_ implements Equipment {

    private final String Name;
    private final EquipmentType Type;
    private EquipmentStatus Status;

    /**
     *
     * @param string
     * @param es
     * @param et
     */
    public Equipment_(String Name, EquipmentType Type, EquipmentStatus Status) {
        this.Name = Name;
        this.Type = Type;
        this.Status = Status;
    }

    /**
     * Returns the Equipment name.
     *
     * @return The Equipment name.
     */
    @Override
    public String getName() {
        return this.Name;
    }

    /**
     * Returns the Equipment type.
     *
     *
     * @return The Equipment type.
     */
    @Override
    public EquipmentType getType() {
        return this.Type;
    }

    /**
     * Returns the Equipment status.
     *
     *
     * @return The Equipment status.
     */
    @Override
    public EquipmentStatus getStatus() {
        return this.Status;
    }

    /**
     * Sets the equipment status.
     *
     *
     * @param es - The Equipment status.
     */
    @Override
    public void setStatus(EquipmentStatus es) {
        this.Status = es;
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
        final Equipment_ other = (Equipment_) obj;
        if (!this.Name.equals(other.Name)) {
            return false;
        }
        if (this.Type != other.Type) {
            return false;
        }
        if (this.Status != other.Status) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\n--Equipment--" + "\nName:" + Name + "\nType:" + Type + "\nStatus:" + Status;
    }

}
