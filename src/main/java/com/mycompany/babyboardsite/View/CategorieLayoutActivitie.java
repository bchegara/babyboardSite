/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Activitie;
import com.mycompany.babyboardsite.Data.Baby;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
public class CategorieLayoutActivitie extends CategorieLayout {

    public CategorieLayoutActivitie(Baby baby) {
        super(baby);

    }

    @Override
    public VerticalLayout contenu(Baby baby) {

        final VerticalLayout activitieComponent = new VerticalLayout();
        try {

            for (Activitie activitie : baby.activitieCategorie.returnListCategorie()) {
                Label title = new Label("début" + activitie.getTime() + " note: " + activitie.getNote());
                Label description = new Label(activitie.getTitle() + " durée: " + activitie.getDuree());
                activitieComponent.addComponent(title);
                activitieComponent.addComponent(description);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erreur mainFactLayout");
        }

        return activitieComponent;
    }

    public void setTitle() {
        title = "Activités";
        validation = "Activité ajoutée";
    }

    @Override
    public VerticalLayout popUpContent() {
        VerticalLayout popUpContent = new VerticalLayout();
        FormLayout formulaire = new FormLayout();
        formulaire.setSizeUndefined();
        TextField title = new TextField("Titre: ");
        TextField description = new TextField("Description: ");
        
        
        formulaire.addComponent(title);
        formulaire.addComponent(description);
        
        
        popUpContent.addComponent(formulaire);
        return popUpContent;
    }

    @Override
    public void addElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
