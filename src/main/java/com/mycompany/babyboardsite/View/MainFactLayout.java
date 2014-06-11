/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Button;
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
    private Window subWindow = new Window("Sub-window");
    private VerticalLayout subContent = new VerticalLayout();

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

        Button connectionButton = new Button("Ouvrir Pop Up");

        connectionButton.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                popup();
            }

        });

        factComponent.addComponent(connectionButton);

        mainFactComponent.addComponent(factComponent);
    }

    public VerticalLayout getMainFactLayout() {
        return mainFactComponent;
    }

    public void popup() {
        // Create a sub-window and set the content

        subContent.setMargin(true);
        subWindow.setContent(subContent);

        // Put some components in it
        subContent.addComponent(new Label("Meatball sub"));
        subContent.addComponent(new Button("Awlright"));

        // Center it in the browser window
        subWindow.center();

        // Open it in the UI
        addWindow(subWindow);
    }
}
