/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.Accident;
import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Event;
import estgconstroi.enums.EventPriority;
import org.json.simple.JSONObject;

/**
 *
 * @author PC
 */
public class Accident_ extends Event implements Accident {

    private Employee Employee;
    private final ConstructionSite ConstructionSite;
    private final String Details;
    private final String NotificationMessage;
    //Notified to Insurance
    private boolean Insurance_Notified;

    public Accident_(Employee Employee, ConstructionSite ConstructionSite, String Details, String NotificationMessage, EventPriority priority, String title, Employee reporter) {
        super(priority, title, reporter);
        this.Employee = Employee;
        this.ConstructionSite = ConstructionSite;
        this.Details = Details;
        this.NotificationMessage = NotificationMessage;
        this.Insurance_Notified = false;
    }

    public boolean isInsurance_Notified() {
        return Insurance_Notified;
    }

    public void setInsurance_Notified(boolean Insurance_Notified) {
        this.Insurance_Notified = Insurance_Notified;
    }

    @Override
    public Employee getEmployee() {
        return this.Employee;
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
        event_details.put("employeename", this.getEmployee().getName());

        group_details.put("event", event_details);

        return group_details.toJSONString();
    }
}
