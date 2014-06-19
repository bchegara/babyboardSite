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
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CategorieLayoutRepas extends CategorieLayout {
    TextField kind;
    TextField hour;
    TextField minute;
    TextArea contenu;
    TextField note;
    

    public CategorieLayoutRepas(Baby baby) {
        super(baby);
    }

    @Override
    public VerticalLayout contenu(Baby baby) {
        final VerticalLayout repasComponent = new VerticalLayout();
        try {

            for (Repas repas : baby.repasCategorie.returnListCategorie()) {
                Label title = new Label("" + repas.getType() + " à " + repas.getTime());
                Label description = new Label(repas.getContenu() + " note: " + repas.getNote());
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
        kind = new TextField("Type de repas: ");
        formulaire.addComponent(kind);

        HorizontalLayout heureInline = new HorizontalLayout();

        hour = new TextField("heure: ");
        minute = new TextField("");
        heureInline.addComponent(hour);
        heureInline.addComponent(new Label("h:"));
        heureInline.addComponent(minute);
        formulaire.addComponent(heureInline);

        contenu = new TextArea("Contenu: ");
        formulaire.addComponent(contenu);

        note = new TextField("Note: ");
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
