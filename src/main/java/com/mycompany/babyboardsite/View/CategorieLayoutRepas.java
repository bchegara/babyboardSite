/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

/**
 *
 * @author baptman
 */
import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.Repas;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CategorieLayoutRepas extends CategorieLayout {

    ComboBox kind;
    ComboBox hour;
    ComboBox minute;
    TextArea contenu;
    ComboBox note;

    public CategorieLayoutRepas(Baby baby) {
        super(baby);
    }

    @Override
    public VerticalLayout contenu(Baby baby) {
        final VerticalLayout repasComponent = new VerticalLayout();
        repasComponent.setSizeFull();
        repasComponent.addStyleName("box-element");
        try {

            for (Repas repas : baby.repasCategorie.returnListCategorie()) {
                Label title = new Label("" + repas.getType() + " à " + repas.getTime());
                Label description = new Label(repas.getContenu() + " Note: " + repas.getNote());
                repasComponent.addComponent(title);
                repasComponent.addComponent(description);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erreur repasLayout");
        }

        return repasComponent;
    }

    @Override
    public void setTitle() {
        title = "Repas";
        validation = "Repas ajoutée";
    }

    @Override
    public VerticalLayout popUpContent() {
        VerticalLayout popUpContent = new VerticalLayout();
        FormLayout formulaire = new FormLayout();
        formulaire.setSizeUndefined();
        kind = new ComboBox("Type de repas:");
        kind.addItem("Petit-déjeunner");
        kind.addItem("Déjeunner");
        kind.addItem("Gouter");
        kind.addItem("Diner");
        kind.select("Petit-déjeunner");
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
//        heureInline.addComponent(new Label("h:"));
        heureInline.addComponent(minute);
        formulaire.addComponent(heureInline);

        contenu = new TextArea("Contenu: ");
        formulaire.addComponent(contenu);

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
        baby.repasCategorie.addRepas(kind.getValue().toString(), Integer.parseInt(hour.getValue().toString()), Integer.parseInt(minute.getValue().toString()),
                contenu.getValue().toString(), Integer.parseInt(note.getValue().toString()));
    }

}
