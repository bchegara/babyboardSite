/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Activitie;
import com.mycompany.babyboardsite.Data.Baby;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
//Classe pour la partie graphique du composant activité du babyboard
public class CategorieLayoutActivitie extends CategorieLayout {

    ComboBox hour;
    ComboBox minute;
    TextField duree;
    ComboBox note;
    TextField kind;

    public CategorieLayoutActivitie(Baby baby) {
        super(baby);

    }

    @Override
    public VerticalLayout contenu(final Baby baby) {

        final VerticalLayout activitieComponent = new VerticalLayout();
//        activitieComponent.setSizeFull();
//        activitieComponent.addStyleName("box-element");
        try {

            for (Activitie activitie : baby.activitieCategorie.returnListCategorie()) {
                IdElem = activitie.getId();
                Button deleteElem = new Button("Delete");
                deleteElem.addClickListener(new Button.ClickListener() {
                    public void buttonClick(Button.ClickEvent event) {
                        baby.activitieCategorie.removeItem(IdElem);
                        navigator.navigateTo(BabyboardView.NAME);
                    }
                });
                Label title = new Label("début: " + activitie.getTime() + " note: " + activitie.getNote());
                Label description = new Label(activitie.getTitle() + " durée: " + activitie.getDuree() + "h");
                activitieComponent.addComponent(title);
                activitieComponent.addComponent(description);
                activitieComponent.addComponent(deleteElem);
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
        kind = new TextField("Type d'activité: ");
        formulaire.addComponent(kind);

        HorizontalLayout heureInline = new HorizontalLayout();

        hour = new ComboBox("heure");
        for (int i = 0; i < 24; i++) {
            hour.addItem(i);
        }
        minute = new ComboBox("minute");

        for (int i = 0; i < 60; i++) {
            minute.addItem(i);
        }
        heureInline.addComponent(hour);
        heureInline.addComponent(minute);
        formulaire.addComponent(heureInline);

        duree = new TextField("durée: ");
        formulaire.addComponent(duree);

        note = new ComboBox("Note: ");
        for (int i = 0; i <= 5; i++) {
            note.addItem(i);
        }
        formulaire.addComponent(note);
        popUpContent.addComponent(formulaire);
        return popUpContent;
    }

    @Override
    public void addElement() {
        baby.activitieCategorie.addActivitie(kind.getValue().toString(), Integer.parseInt(duree.getValue().toString()), Integer.parseInt(note.getValue().toString()),
                Integer.parseInt(hour.getValue().toString()), Integer.parseInt(minute.getValue().toString()));
    }

}
