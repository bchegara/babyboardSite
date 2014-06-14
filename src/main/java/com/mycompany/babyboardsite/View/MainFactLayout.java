/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
public class MainFactLayout {

    private VerticalLayout mainFactComponent;

    MainFactLayout(Baby baby) {
        final VerticalLayout factComponent = new VerticalLayout();
        mainFactComponent = new VerticalLayout();
        final HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.addComponent(new Label("Faits marquants"));
        final Button open = new Button("+");
        open.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                popup();
            }
        });
        titleLayout.addComponent(open);
        mainFactComponent.addComponent(titleLayout);

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

        mainFactComponent.addComponent(factComponent);
    }

    public VerticalLayout getMainFactLayout() {
        return mainFactComponent;
    }

    public void popup() {
        PopUp sub = new PopUp();

        // Add it to the root component
        UI.getCurrent().addWindow(sub);

    }
}
