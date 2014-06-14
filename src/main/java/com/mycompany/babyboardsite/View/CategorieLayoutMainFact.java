/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.MainFact;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author baptman
 */
public class CategorieLayoutMainFact extends CategorieLayout {

    

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
public void setTitle(){
    title = "faits marquants";
}
}
