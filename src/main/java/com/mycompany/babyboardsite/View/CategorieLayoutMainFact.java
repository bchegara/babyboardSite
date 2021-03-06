/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.MainFact;
import com.mycompany.babyboardsite.Data.User;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
//Classe pour la partie graphique du composant faitmarquant du babyboard
public class CategorieLayoutMainFact extends CategorieLayout {

    private TextField titleTextField;
    private TextArea description;
    private ComboBox hour;

    private ComboBox minute;

    public CategorieLayoutMainFact(Baby baby) {
        super(baby);

    }

    @Override
    public VerticalLayout contenu(final Baby baby) {

        final VerticalLayout factComponent = new VerticalLayout();
//        factComponent.addStyleName("box-element");
//        factComponent.setSizeFull();
        try {

            for (MainFact fact : baby.mainFactCategorie.returnListCategorie()) {
                IdElem = fact.getId();
                Button deleteElem = new Button("Delete");
                deleteElem.addClickListener(new Button.ClickListener() {
                    public void buttonClick(Button.ClickEvent event) {
                        baby.mainFactCategorie.removeItem(IdElem);
                        navigator.navigateTo(BabyboardView.NAME);
                    }
                });

                Label title = new Label(fact.getTitle() + " à " + fact.getHour());
                Label description = new Label(fact.getDescription());
                factComponent.addComponent(title);
                factComponent.addComponent(description);
                factComponent.addComponent(deleteElem);
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

        HorizontalLayout heureInline = new HorizontalLayout();
        hour = new ComboBox("heure");
        for (int i = 0; i < 24; i++) {
            hour.addItem(i);
        }
        minute = new ComboBox("minute");

        for (int i = 0; i < 60; i++) {
            minute.addItem(i);
        }
        hour.setRequired(true);
        minute.setRequired(true);
        heureInline.addComponent(hour);
        heureInline.addComponent(minute);

        formulaire.addComponent(titleTextField);
        formulaire.addComponent(description);
        formulaire.addComponent(heureInline);

        popUpContent.addComponent(formulaire);
        return popUpContent;
    }

    @Override
    public void addElement() {
        baby.mainFactCategorie.addMainFact(titleTextField.getValue().toString(), description.getValue().toString(),
                Integer.parseInt(hour.getValue().toString()), Integer.parseInt(minute.getValue().toString()));
    }
}
