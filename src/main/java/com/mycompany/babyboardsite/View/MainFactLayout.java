/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
/**
 *
 * @author baptman
 */
public class MainFactLayout {
    
    private VerticalLayout mainFactComponent;
    MainFactLayout(Baby baby){
        VerticalLayout factComponent = new VerticalLayout();
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
        mainFactComponent.addComponent(factComponent);
    }
    public VerticalLayout getMainFactLayout(){
        return mainFactComponent;
    }
}
