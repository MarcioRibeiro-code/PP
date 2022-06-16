/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exame_FINAL.Classes;

import estgconstroi.ConstructionSite;
import estgconstroi.Incident;

/**
 *
 * @author PC
 */
public class Incident_ implements Incident {

    private final ConstructionSite ConstructionSite;
    private final String Details;
    private final String NotificationMessage;

    public Incident_(ConstructionSite ConstructionSite, String Details, String NotificationMessage) {
        this.ConstructionSite = ConstructionSite;
        this.Details = Details;
        this.NotificationMessage = NotificationMessage;
    }

    
    
    @Override
    public ConstructionSite getConstructionSite() {
        return this.ConstructionSite;
    }

    @Override
    public String getDetails() {
        return this.Details;
    }

    // ?? DUVIDAS NISTO. Qual 'e o conteudo que deve ter.
    @Override
    public String getNotificationMessage() {
        return this.NotificationMessage;
    }

}
