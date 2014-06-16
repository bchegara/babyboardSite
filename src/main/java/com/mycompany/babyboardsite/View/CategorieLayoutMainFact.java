/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.MainFact;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
public class CategorieLayoutMainFact extends CategorieLayout {

    private TextField titleTextField;
    private TextArea description;
    private TextField hour;
    private TextField minute;

    public CategorieLayoutMainFact(Baby baby) {
        super(baby);

    }

    @Override
    public VerticalLayout contenu(Baby baby) {

        final VerticalLayout factComponent = new VerticalLayout();
        try {

            for (MainFact fact : baby.mainFactCategorie.returnListCategorie()) {
                Label title = new Label(fact.getTitle() + " à " + fact.getHour());
                Label description = new Label(fact.getDescription());
                factComponent.addComponent(title);
                factComponent.addComponent(description);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erreur mainFactLayout");
        }
        return factComponent;
    }

    public void setTitle() {
        title = "faits marquants";
        validation = "Faits marquants ajouté!";
    }

    @Override
    public VerticalLayout popUpContent() {
        VerticalLayout popUpContent = new VerticalLayout();
        FormLayout formulaire = new FormLayout();
        formulaire.setSizeUndefined();
        titleTextField = new TextField("Titre: ");
        description = new TextArea("Description: ");
        hour = new TextField("heure");

        minute = new TextField("minute");

        formulaire.addComponent(titleTextField);
        formulaire.addComponent(description);
        formulaire.addComponent(hour);
        hour.setRequired(true);
        hour.setRequiredError("Chiffre requis");

        formulaire.addComponent(minute);
        minute.setRequired(true);
        minute.setRequiredError("Chiffre requis");
        popUpContent.addComponent(formulaire);
        return popUpContent;
    }

    @Override
    public void addElement() {
        baby.mainFactCategorie.addMainFact(titleTextField.getValue().toString(), description.getValue().toString(),
                Integer.parseInt(hour.getValue().toString()), Integer.parseInt(minute.getValue().toString()));
    }
}
