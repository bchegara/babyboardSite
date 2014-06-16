/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Activitie;
import com.mycompany.babyboardsite.Data.Baby;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.mycompany.babyboardsite.Data.Sieste;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 *
 * @author baptman
 */
public class CategorieLayoutSieste extends CategorieLayout{

    TextField hour;
    TextField minute;
    TextField duree;
    TextField note;
    public CategorieLayoutSieste(Baby baby) {
        super(baby);
    }

    @Override
    public VerticalLayout contenu(Baby baby) {
        final VerticalLayout activitieComponent = new VerticalLayout();
        try {

            for (Sieste sieste : baby.siesteCategorie.returnListCategorie()) {
                Label title = new Label("début: " + sieste.getHour()+ "h" + sieste.getMinute());
                Label description = new Label("Durée: " + sieste.getDuree()+" note: "+sieste.getNote());
                activitieComponent.addComponent(title);
                activitieComponent.addComponent(description);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erreur siesteLayout");
        }

        return activitieComponent;
    }

    @Override
    public void setTitle() {
        title = "Siestes";
        validation = "Sieste ajoutée";
    }

    @Override
    public VerticalLayout popUpContent() {
        VerticalLayout popUpContent = new VerticalLayout();
        FormLayout formulaire = new FormLayout();
        formulaire.setSizeUndefined();
        HorizontalLayout heureInline = new HorizontalLayout();
        
        hour = new TextField("Début: ");
        minute = new TextField("");
        heureInline.addComponent(hour);
        heureInline.addComponent(new Label("h:"));
        heureInline.addComponent(minute);
        formulaire.addComponent(heureInline);
        
        duree = new TextField("durée: ");
        formulaire.addComponent(duree);
        
        note = new TextField("Note: ");
        formulaire.addComponent(note);
        popUpContent.addComponent(formulaire);
        return popUpContent;
    }

    @Override
    public void addElement() {
        baby.siesteCategorie.addMainFact(Integer.parseInt(hour.getValue().toString()), Integer.parseInt(minute.getValue().toString()),
                Integer.parseInt(duree.getValue().toString()), Integer.parseInt(note.getValue().toString()));
    }
    
    
}