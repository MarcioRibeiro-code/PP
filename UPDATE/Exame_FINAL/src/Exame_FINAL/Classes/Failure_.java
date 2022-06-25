/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Equipment;
import estgconstroi.Event;
import estgconstroi.Failure;
import estgconstroi.enums.EquipmentStatus;
import estgconstroi.enums.EventPriority;
import org.json.simple.JSONObject;

/**
 *
 * @author PC
 */
public class Failure_ extends Event implements Failure {

    /**
     * The Failure interface provides the methods for an failure on an
     * equipment. Upon Creation of a Failure the {link equipment must be changed
     * to INOPERATIVE.
     */
    private final Equipment Equipment;
    private final ConstructionSite ConstructionSite;
    private final String Details;
    private final String NotificationMessage;
    private boolean Insurance_Notified;
    private boolean Reported;

    public Failure_(Equipment Equipment, ConstructionSite ConstructionSite, String Details, String NotificationMessage, EventPriority priority, String title, Employee reporter) {
        super(priority, title, reporter);
        this.Equipment = Equipment;
        this.Equipment.setStatus(EquipmentStatus.INOPERATIVE);
        this.ConstructionSite = ConstructionSite;
        this.Details = Details;
        this.NotificationMessage = NotificationMessage;
        this.Insurance_Notified = false;
        this.Reported = false;
    }

    public boolean isReported() {
        return Reported;
    }

    public void setReported() {
        this.Reported = true;
    }

    public boolean isInsurance_Notified() {
        return Insurance_Notified;
    }

    public void setInsurance_Notified() {
        this.Insurance_Notified = true;
    }

    @Override
    public Equipment getEquipment() {
        return this.Equipment;
    }

    @Override
    public ConstructionSite getConstructionSite() {
        return this.ConstructionSite;
    }

    @Override
    public String getDetails() {
        return this.Details;
    }

    @Override
    public String getNotificationMessage() {
        return this.NotificationMessage;
    }

    public String Event_toJSON() {

        JSONObject group_details = new JSONObject();
        group_details.put("groupname", "GrupoXX");
        group_details.put("groupkey", "GywNgSAn4bCxayZ");

        JSONObject event_details = new JSONObject();

        event_details.put("uuid", super.getUuid());
        event_details.put("data", super.getDate());
        event_details.put("priority", super.getPriority());
        event_details.put("eventtype", super.getPriority());
        event_details.put("title", super.getTitle());
        event_details.put("constructionsitename", this.getConstructionSite().getName());
        event_details.put("details", this.getDetails());
        event_details.put("equipment", this.Equipment.getName());

        group_details.put("event", event_details);

        return group_details.toJSONString();
    }

}
