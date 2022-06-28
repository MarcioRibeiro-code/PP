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

    /**
     *
     * @param cs
     * @param string
     * @param string1
     * @param ep
     * @param empl
     * @param string2
     */
    public Incident_(ConstructionSite ConstructionSite, String Details, String NotificationMessage, EventPriority priority, String title, Employee reporter) {
        super(priority, title, reporter);
        this.ConstructionSite = ConstructionSite;
        this.Details = Details;
        this.NotificationMessage = NotificationMessage;
        this.Insurance_Notified = false;
        this.Insurance_Notified = false;
    }

    /**
     * Returns if the Insurance is Notified or not.
     *
     * @return boolean - if the Insurance is Notified or not.
     */
    public boolean isInsurance_Notified() {
        return Insurance_Notified;
    }

    /**
     * Sets Insurance_Notified as true, this meaning that the Insurance as
     * Reported.
     */
    public void setInsurance_Notified() {
        this.Insurance_Notified = true;
    }

    /**
     * Returns the Construction Site where the Accident occurred.
     *
     * @return ConstructioSite - where the Accident occurred.
     */
    @Override
    public ConstructionSite getConstructionSite() {
        return this.ConstructionSite;
    }

    /**
     * Returns Details about the Accident.
     *
     * @return String - Details about the Accident.
     */
    @Override
    public String getDetails() {
        return this.Details;
    }

    /**
     * Returns the NotificationMessage.
     *
     * @return String - Notification Message.
     */
    @Override
    public String getNotificationMessage() {
        return this.NotificationMessage;
    }

     /**
     * Returns the JSON.STRING representation of Incident.
     *
     * @return String - JSON.STRING representation of Incident.
     */
    public String Event_toJSON() {

        JSONObject group_details = new JSONObject();
        group_details.put("groupname", "Grupo03");
        group_details.put("groupkey", "GywNgSAn4bCxayZ");

        JSONObject event_details = new JSONObject();

        event_details.put("uuid", super.getUuid());
        event_details.put("data", super.getDate().toString());
        event_details.put("priority", super.getPriority().toString());
        event_details.put("eventtype", "Incident");
        event_details.put("title", super.getTitle());
        event_details.put("constructionsitename", this.getConstructionSite().getName());
        event_details.put("details", this.getDetails());

        group_details.put("event", event_details);

        return group_details.toJSONString();
    }

    @Override
    public String toString() {
        return "\n--Incident--" + "\nConstructionSite=" + ConstructionSite.getName() + "\nDetails=" + Details + "\nNotificationMessage=" + NotificationMessage + "\nInsurance_Notified=" + Insurance_Notified;
    }

}
