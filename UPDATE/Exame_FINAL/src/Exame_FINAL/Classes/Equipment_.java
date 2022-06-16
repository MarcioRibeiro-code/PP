/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public int hashCode() {
        int hash = 7;
        return hash;
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

}
