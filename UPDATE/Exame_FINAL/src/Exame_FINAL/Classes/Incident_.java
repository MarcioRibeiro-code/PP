/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Event;
import estgconstroi.Incident;
import estgconstroi.enums.EventPriority;
import org.json.simple.JSONObject;

/**
 *
 * @author PC
 */
public class Incident_ extends Event implements Incident {

    private final ConstructionSite ConstructionSite;
    private final String Details;
    private final String NotificationMessage;
    private boolean Insurance_Notified;
    private boolean Reported;

    public Incident_(ConstructionSite ConstructionSite, String Details, String NotificationMessage, EventPriority priority, String title, Employee reporter) {
        super(priority, title, reporter);
        this.ConstructionSite = ConstructionSite;
        this.Details = Details;
        this.NotificationMessage = NotificationMessage;
        this.Insurance_Notified = false;
        this.Insurance_Notified = false;
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

        group_details.put("event", event_details);

        return group_details.toJSONString();
    }

}
