/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author baptman
 */
public abstract class MainFactLayout extends UI {

    private VerticalLayout mainFactComponent;

    MainFactLayout(Baby baby) {
        final VerticalLayout factComponent = new VerticalLayout();
        mainFactComponent = new VerticalLayout();

        try {

            for (MainFact fact : baby.FactList) {
                Label title = new Label(fact.getTitle() + " à " + fact.getHour());
                Label description = new Label(fact.getDescription());
                factComponent.addComponent(title);
                factComponent.addComponent(description);
            }
        } catch (Exception e) {
            System.out.println("e");
        }
        final Button open = new Button("Open Sub-Window");
        open.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                popup();
            }
        });
        mainFactComponent.addComponent(open);

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
