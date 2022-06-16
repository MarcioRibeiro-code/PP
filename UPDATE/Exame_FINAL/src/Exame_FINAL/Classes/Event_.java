/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.ConstructionSite;
import estgconstroi.Employee;
import estgconstroi.Event;
import estgconstroi.enums.EventPriority;

/**
 *
 * @author PC
 */
public class Event_ extends Event {

    private final ConstructionSite Constructionsite;
    private final String Details;
    //Mensagem a ser Displayed no Notifier, quando ocorre um problema
    private final String NotificationMessage;

    public Event_(EventPriority priority, String title, Employee reporter,
            ConstructionSite Constructionsite, String Details,
            String NotificationMessage) {
        super(priority, title, reporter);
        this.Constructionsite = Constructionsite;
        this.Details = Details;
        this.NotificationMessage = NotificationMessage;
    }

    /*
    
    super.getDate()
    super.getPriority()
    super.getUuid()
    super.getTitle()
    super.getReporter()
     */
    @Override
    public ConstructionSite getConstructionSite() {
        return this.Constructionsite;
    }

    @Override
    public String getDetails() {
        return this.Details;
    }

    @Override
    public String getNotificationMessage() {
        return this.NotificationMessage;
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
        final Event_ other = (Event_) obj;
        if (!this.Details.equals(other.Details)) {
            return false;
        }
        if (!this.NotificationMessage.equals(other.NotificationMessage)) {
            return false;
        }
        if (!this.Constructionsite.equals(other.Constructionsite)) {
            return false;
        }
        return true;
    }

    
}
