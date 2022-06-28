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
import estgconstroi.Event;
import estgconstroi.EventManager;
import estgconstroi.InsuranceReporter;
import estgconstroi.Notifier;
import estgconstroi.Team;
import estgconstroi.enums.EmployeeType;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.EventManagerException;
import java.io.IOException;
import java.time.LocalDate;

/**
 *
 * @author PC
 */
public class EventManager_ implements EventManager {
    
    private Notifier[] Notifier;
    private Event[] Event;

    /**
     *
     */
    public EventManager_() {
        this.Notifier = new Notifier[3];
        this.Event = new Event[4];
    }

    /**
     * Adds a notifier to be added.
     *
     * @param ntfr - The notifier to be added
     * @throws EventManagerException - if the notifier already exists
     */
    @Override
    public void addNotifier(Notifier ntfr) throws EventManagerException {
        if (ntfr == null) {
            return;
        }
        
        for (Notifier ntfr_ : this.getNotifier()) {
            if (ntfr_.equals(ntfr)) {
                throw new EventManagerException("Event already added");
            }
        }

        //Aumentar Tamanho Array
        if (this.getNotifier().length + 1 >= this.Notifier.length) {
            Notifier[] temp = new Notifier[this.Notifier.length * 2];

            //length - Numero de Componentes copiados
            System.arraycopy(this.Notifier, 0, temp, 0, this.getNotifier().length);
        }
        
        this.Notifier[this.getNotifier().length] = ntfr;
    }

    /**
     * Removes a notifier to the event manager.
     *
     * @param ntfr - The notifier to be removed.
     * @throws EventManagerException - if the notifier does not exists
     */
    @Override
    public void removeNotifier(Notifier ntfr) throws EventManagerException {
        
        if (ntfr == null) {
            return;
        }
        
        int pos = -1;
        
        for (int i = 0; i < this.getNotifier().length; i++) {
            if (this.Notifier[i].equals(ntfr)) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            Notifier[] temp = new Notifier[this.Notifier.length];
            for (int i = 0, k = 0; i < this.getNotifier().length; i++) {
                
                if (i != pos) {
                    temp[k++] = this.Notifier[i];
                }
                
            }
            this.Notifier = temp;
        }
        throw new EventManagerException("Notifier does not Exists");
    }

    /**
     * Gets all the Notifiers of the event manager.
     *
     * @return All the Notifiers of the event manager.
     */
    public Notifier[] getNotifier() {
        int contador = 0;
        
        for (Notifier ntfr : this.Notifier) {
            if (ntfr != null) {
                contador++;
            }
        }
        
        Notifier[] temp = new Notifier[contador];
        
        System.arraycopy(this.Notifier, 0, temp, 0, contador);
        return temp;
    }

    /**
     * Adds an event to the event manager. The event must be reported using all
     * the notifiers to the Manager and the Team Leader of the construction
     * site.
     *
     *
     * @param event - The event to be added and notified.
     * @throws EventManagerException - if the event already reported
     */
    @Override
    public void reportEvent(Event event) throws EventManagerException {
        
        if (event == null) {
            return;
        }
        
        if (this.getNotifier() == null) {
            System.out.println("NO NOTIFIER");
            return;
        }
        
        for (Event evn : this.getEvent()) {
            if (evn.equals(event)) {
                throw new EventManagerException("Event already added");
            }
        }
        
        switch (event.getPriority()) {
            case IMMEDIATE:
                //USES ALL NOTIFIER

                for (Notifier ntf : this.getNotifier()) {
                    //REPORTAR MANAGER
                    ntf.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());
                    //REPORTAR TEAM_LEADER
                    for (Team tm1 : event.getConstructionSite().getTeams()) {
                        for (Employee empl : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                            ntf.notify(event.getNotificationMessage(), event.getPriority(), empl);
                        }
                    }
                }
                
                break;
            case HIGH:
                for (Notifier ntf : this.getNotifier()) {
                    if (!ntf.getClass().getTypeName().equals("Text")) {
                        //REPORTAR MANAGER
                        ntf.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());
                        //REPORTAR TEAM_LEADER
                        for (Team tm1 : event.getConstructionSite().getTeams()) {
                            for (Employee empl : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                                ntf.notify(event.getNotificationMessage(), event.getPriority(), empl);
                            }
                        }
                    }
                }
                
                break;
            case NORMAL:
                for (Notifier ntf : this.getNotifier()) {
                    if (ntf.getClass().getTypeName().equals("Email")) {
                        //REPORTAR MANAGER
                        ntf.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());
                        //REPORTAR TEAM_LEADER
                        for (Team tm1 : event.getConstructionSite().getTeams()) {
                            for (Employee empl : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                                ntf.notify(event.getNotificationMessage(), event.getPriority(), empl);
                            }
                        }
                    }
                }
                break;
            case LOW:
                for (Notifier ntf : this.getNotifier()) {
                    if (ntf.getClass().getTypeName().equals("Consola")) {
                        //REPORTAR MANAGER
                        ntf.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());
                        //REPORTAR TEAM_LEADER
                        for (Team tm1 : event.getConstructionSite().getTeams()) {
                            for (Employee empl : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                                ntf.notify(event.getNotificationMessage(), event.getPriority(), empl);
                            }
                        }
                    }
                }
                
                break;
            default:
                System.out.println("NAO SUPORTADO");
        }
        
    }

    /**
     * Removes all events from the event manager.
     *
     */
    @Override
    public void removeAllEvents() {
        
        for (Event ev1 : this.getEvent()) {
            ev1 = null;
        }
        
        System.arraycopy(this.Event, 0, this.Event, 0, 4);
    }

    /**
     * Remove the event from the event manager.
     *
     * @param event - The event to be removed.
     * @throws EventManagerException - if the event does not exist
     */
    @Override
    public void removeEvent(Event event) throws EventManagerException {
        
        if (event == null) {
            return;
        }
        
        int pos = -1;
        
        for (int i = 0; i < this.getEvent().length; i++) {
            if (this.Event[i].equals(event)) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            Event[] temp = new Event[this.Event.length];
            for (int i = 0, k = 0; i < this.getEvent().length; i++) {
                
                if (i != pos) {
                    temp[k++] = this.Event[i];
                }
                
            }
            this.Event = temp;
        } else {
            throw new EventManagerException("Event does not Exists");
        }
        
    }

    /**
     * Gets the events of the event manager with the specified priority.
     *
     * @param ep - filter criteria
     * @return The events of the event manager with the specified priority
     */
    @Override
    public Event[] getEvent(EventPriority ep) {
        
        if (ep == null) {
            return null;
        }
        Event[] temp = new Event[this.getEvent().length];
        int k = 0;
        
        for (int i = 0; i < this.getEvent().length; i++) {
            if (this.Event[i].getPriority().equals(ep)) {
                temp[k++] = this.Event[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
    }

    /**
     *
     * Gets the events of the event manager with the specified class, e.g.,
     * Incident, Accident or Failure.
     *
     * @param type - class used to filter events
     * @return The events of the event manager with the specified priority.
     */
    @Override
    public Event[] getEvent(Class type) {
        
        if (type == null) {
            return null;
        }
        Event[] temp = new Event[this.getEvent().length];
        int k = 0;
        
        for (int i = 0; i < this.getEvent().length; i++) {
            if (this.Event[i].getClass().equals(type)) {
                temp[k++] = this.Event[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;   
    }

    /**
     * Gets the events of the event manager with the specified date.
     *
     * @param ld - to filter events
     *
     * @return The events of the event manager with the specified priority.
     */
    @Override
    public Event[] getEvent(LocalDate ld) {
        if (ld == null) {
            return null;
        }
        Event[] temp = new Event[this.getEvent().length];
        int k = 0;
        
        for (int i = 0; i < this.getEvent().length; i++) {
            if (this.Event[i].getDate().equals(ld)) {
                temp[k++] = this.Event[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
        
    }

    /**
     * Gets the events of the event manager between the specified dates.
     *
     * @param ld - Event Start Date
     * @param ld1 - Event End Date
     * @return The events of the event manager between the specified dates.
     */
    @Override
    public Event[] getEvent(LocalDate ld, LocalDate ld1) {
        if (ld == null) {
            return null;
        }
        
        Event[] temp = new Event[this.getEvent().length];
        int k = 0;
        
        for (int i = 0; i < this.getEvent().length; i++) {
            if ((this.Event[i].getDate().isAfter(ld)) && this.Event[i].getDate().isBefore(ld1)) {
                temp[k++] = this.Event[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;
        
    }

    /**
     * Gets all the Notifiers of the event manager.
     *
     * @return All the Notifiers of the event manager.
     */
    public Event[] getEvent() {
        
        int contador = 0;
        
        for (Event ev1 : this.Event) {
            if (ev1 != null) {
                contador++;
            }
        }
        
        Event[] temp = new Event[contador];
        
        System.arraycopy(this.Event, 0, temp, 0, contador);
        return temp;
        
    }

    /**
     * Reports an event to the Insurance, via an API.
     *
     * @param event - Event to the reported to the Insurance
     * @throws EventManagerException - if the event was already reported to the
     * Insurer
     */
    public void Report_toInsurance(Event event) throws EventManagerException {
        
        if (event == null) {
            return;
        }
        
   
        
        if (event instanceof Accident_) {
            Accident_ temp = (Accident_) event;
            if (temp.isInsurance_Notified() == false) {
                try {
                    InsuranceReporter.addEvent(temp.Event_toJSON());
                    temp.setInsurance_Notified();
                    System.out.println("Reportado a seguradora");
                } catch (IOException | InterruptedException ex) {
                    System.out.println("ERROR - Insurance not reported");
                }
                return;
            }
        } else if (event instanceof Failure_) {
            Failure_ temp = (Failure_) event;
            if (temp.isInsurance_Notified() == false) {
                try {
                    InsuranceReporter.addEvent(temp.Event_toJSON());
                    temp.setInsurance_Notified();
                    System.out.println("Reportado a seguradora");
                } catch (IOException | InterruptedException ex) {
                    System.out.println("ERROR - Insurance not reported");
                }
                return;
            }
        } else if (event instanceof Incident_) {
            Incident_ temp = (Incident_) event;
            if (temp.isInsurance_Notified() == false) {
                try {
                    InsuranceReporter.addEvent(temp.Event_toJSON());
                    temp.setInsurance_Notified();
                    System.out.println("Reportado a seguradora");
                } catch (IOException | InterruptedException ex) {
                    System.out.println("ERROR - Insurance not reported");
                }
            }
            return;
        }
        
        throw new EventManagerException("Insurance already Reported");
    }
    
}
