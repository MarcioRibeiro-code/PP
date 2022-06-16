/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.Event;
import estgconstroi.EventManager;
import estgconstroi.Notifier;
import estgconstroi.enums.EventPriority;
import estgconstroi.exceptions.EventManagerException;
import java.time.LocalDate;

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

    //REPORT EVENT == ADD EVENT
    @Override
    public void reportEvent(Event event) throws EventManagerException {
        
        if (event == null) {
            return;
        }
        
        for (Event ev1 : this.getEvent()) {
            Event_ temp = (Event_) ev1;
            if (temp.equals(ev1)) {
                throw new EventManagerException("Event already Reported");
            }
        }
        
        if (this.getEvent().length + 1 >= this.Event.length) {
            Event[] temp = new Event[this.Event.length * 2];
            
            System.arraycopy(this.Event, 0, temp, 0, this.getEvent().length);
            this.Event = temp;
        }
        
        this.Event[this.getEvent().length] = event;
    }
    
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
            Event_ temp = (Event_) this.Event[i];
            if (temp.equals(event)) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Event[] getEvent(LocalDate ld, LocalDate ld1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
