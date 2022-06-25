/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class EventManager_ implements EventManager {

    private Notifier Notifier;
    private Event[] Event;

    public EventManager_() {
        this.Event = new Event[4];
    }

    @Override
    public void addNotifier(Notifier ntfr) throws EventManagerException {

        if (this.Notifier != null) {
            throw new EventManagerException("Notifier Already Exists");
        }

        this.Notifier = ntfr;
    }

    @Override
    public void removeNotifier(Notifier ntfr) throws EventManagerException {
        if (!this.Notifier.equals(ntfr)) {
            throw new EventManagerException("Notifier doesnt exist");
        }

        this.Notifier = null;
    }

    public void addEvent(Event event) throws EventManagerException {
        if (event == null) {
            return;
        }

        for (Event ev1 : this.getEvent()) {

            if (event.equals(ev1)) {
                throw new EventManagerException("Event already added");
            }
        }

        if (this.getEvent().length + 1 >= this.Event.length) {
            Event[] temp = new Event[this.Event.length * 2];

            System.arraycopy(this.Event, 0, temp, 0, this.getEvent().length);
            this.Event = temp;
        }
        this.Event[this.getEvent().length] = event;
    }

    //REPORT EVENT 
    @Override
    public void reportEvent(Event event) throws EventManagerException {

        if (event == null) {
            return;
        }

        if (event instanceof Accident_) {
            Accident_ temp_event = (Accident_) event;
            if (temp_event.isReported() == false) {
//NOTIFICAR O GESTOR
                this.Notifier.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());

                //NOTIFICAR OS TEAM LEADERS
                Team[] temp = new Team[event.getConstructionSite().getTeams().length];

                for (Team tm1 : temp) {
                    for (Employee em1 : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                        this.Notifier.notify(event.getNotificationMessage(), event.getPriority(), em1);
                    }

                }

                try {
                    // Event_toJSON
                    InsuranceReporter.addEvent(temp_event.Event_toJSON());
                    //this.Event[this.getEvent().length - 1]
                    temp_event.setInsurance_Notified();

                    //System.out.println(InsuranceReporter.addEvent(););
                } catch (IOException | InterruptedException ex) {

                }
            }
        } else if (event instanceof Failure_) {
            Failure_ temp_event = (Failure_) event;
            if (temp_event.isReported() == false) {
                this.Notifier.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());

                //NOTIFICAR OS TEAM LEADERS
                Team[] temp = new Team[event.getConstructionSite().getTeams().length];

                for (Team tm1 : temp) {
                    for (Employee em1 : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                        this.Notifier.notify(event.getNotificationMessage(), event.getPriority(), em1);
                    }
                }
                try {
                    InsuranceReporter.addEvent(temp_event.Event_toJSON());
                    temp_event.setInsurance_Notified();
                } catch (IOException | InterruptedException ex) {
                }
            }
        } else if (event instanceof Incident_) {
            Incident_ temp_event = (Incident_) event;
            if (temp_event.isReported() == false) {
                this.Notifier.notify(event.getNotificationMessage(), event.getPriority(), event.getConstructionSite().getResponsible());
                //NOTIFICAR OS TEAM LEADERS
                Team[] temp = new Team[event.getConstructionSite().getTeams().length];

                for (Team tm1 : temp) {
                    for (Employee em1 : tm1.getEmployees(EmployeeType.TEAM_LEADER)) {
                        this.Notifier.notify(event.getNotificationMessage(), event.getPriority(), em1);
                    }
                }
                try {
                    InsuranceReporter.addEvent(temp_event.Event_toJSON());
                    temp_event.setInsurance_Notified();
                } catch (IOException | InterruptedException ex) {
                }

            }

        }
        throw new EventManagerException("Event already Reported");
    }

    /*
    private static String Event_toJSON(Event event) {

        if (event instanceof Accident_) {
            Accident_ temp = (Accident_) event;
            return temp.Event_toJSON();
        } else if (event instanceof Failure_) {
            Failure_ temp = (Failure_) event;
            return temp.Event_toJSON();
        } else if (event instanceof Incident_) {
            Incident_ temp = (Incident_) event;
            return temp.Event_toJSON();
        }

        return null;
    }*/
    @Override
    public void removeAllEvents() {

        for (Event ev1 : this.getEvent()) {
            ev1 = null;
        }

        System.arraycopy(this.Event, 0, this.Event, 0, 4);
    }

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

    @Override
    public Event[] getEvent(Class type) {

        if (type == null) {
            return null;
        }

        Event[] temp = new Event[this.getEvent().length];
        int k = 0;

        for (int i = 0; i < this.getEvent().length; i++) {
            if (this.Event[i].getClass() == type) {
                temp[k++] = this.Event[i];
            }
        }
        System.arraycopy(temp, 0, temp, 0, k);
        return temp;

    }

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

    @Override
    public Event[] getEvent(LocalDate ld, LocalDate ld1
    ) {

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

    public Event[] getEvent() {

        int contador = 0;

        for (Event ev1 : this.Event) {
            if (Event != null) {
                contador++;
            }
        }

        Event[] temp = new Event[contador];

        System.arraycopy(this.Event, 0, temp, 0, contador);

        return temp;

    }

    public void Report_toInsurance(Event event) throws EventManagerException {

        if (event == null) {
            return;
        }

        if (event instanceof Accident_) {
            Accident_ temp = (Accident_) event;
            if (temp.isInsurance_Notified() == false) {
                try {
                    InsuranceReporter.addEvent(temp.Event_toJSON());
                } catch (IOException | InterruptedException ex) {
                    System.out.println("ERROR - Insurance not reported");
                }
            }
        } else if (event instanceof Failure_) {
            Failure_ temp = (Failure_) event;
            if (temp.isInsurance_Notified() == false) {
                try {
                    InsuranceReporter.addEvent(temp.Event_toJSON());
                } catch (IOException | InterruptedException ex) {
                    System.out.println("ERROR - Insurance not reported");
                }
            }
        } else if (event instanceof Incident_) {
            Incident_ temp = (Incident_) event;
            if (temp.isInsurance_Notified() == false) {
                try {
                    InsuranceReporter.addEvent(temp.Event_toJSON());
                } catch (IOException | InterruptedException ex) {
                    System.out.println("ERROR - Insurance not reported");
                }
            }
        }

        throw new EventManagerException("Insurance already Reported");
    }

}
