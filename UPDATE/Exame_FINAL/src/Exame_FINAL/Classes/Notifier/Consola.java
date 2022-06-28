/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes.Notifier;

import estgconstroi.Employee;
import estgconstroi.Notifier;
import estgconstroi.enums.EventPriority;

/**
 *
 * @author PC
 */
public class Consola implements Notifier {
    
    
    @Override
    public void notify(String string, EventPriority ep, Employee empl) {
        System.out.println("\n--PHONE TEXT--" + "\nNotification Message:" + string + "\nEvent Priority:" + ep.toString() + "\nEmploye:" + empl.toString());
    }
}
