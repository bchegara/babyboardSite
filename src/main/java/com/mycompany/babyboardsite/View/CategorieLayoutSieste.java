/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.Sieste;
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
public class CategorieLayoutSieste extends CategorieLayout {

    ComboBox hour;
    ComboBox minute;
    TextField duree;
    ComboBox note;

    public CategorieLayoutSieste(Baby baby) {
        super(baby);
    }

    @Override
    public VerticalLayout contenu(final Baby baby) {
        final VerticalLayout siesteComponent = new VerticalLayout();
//        siesteComponent.setSizeFull();
//        siesteComponent.addStyleName("box-element");
        try {

            for (Sieste sieste : baby.siesteCategorie.returnListCategorie()) {

                IdElem = sieste.getId();
                Button deleteElem = new Button("Delete");
                deleteElem.addClickListener(new Button.ClickListener() {
                    public void buttonClick(Button.ClickEvent event) {
                        baby.siesteCategorie.removeItem(IdElem);
                        navigator.navigateTo(BabyboardView.NAME);
                    }
                });
                Label title = new Label("Début: " + sieste.getHour() + "h" + sieste.getMinute());
                Label description = new Label("Durée: " + sieste.getDuree() + " Note: " + sieste.getNote());
                siesteComponent.addComponent(title);
                siesteComponent.addComponent(description);
                siesteComponent.addComponent(deleteElem);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erreur siesteLayout");
        }

        return siesteComponent;
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
        baby.siesteCategorie.addMainFact(Integer.parseInt(hour.getValue().toString()), Integer.parseInt(minute.getValue().toString()),
                Integer.parseInt(duree.getValue().toString()), Integer.parseInt(note.getValue().toString()));
    }

}
