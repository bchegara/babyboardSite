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
import com.mycompany.babyboardsite.Data.MainFact;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CategorieLayoutNumeroUtile extends CategorieLayout {

    public CategorieLayoutNumeroUtile(Baby baby) {
        super(baby);
    }

    @Override
    public VerticalLayout contenu(Baby baby) {
        VerticalLayout layoutNumeroUtile = new VerticalLayout();
        Label numeroUtile = new Label("Numéros utiles");
        layoutNumeroUtile.addComponent(numeroUtile);
        return layoutNumeroUtile;
    }
    
    

    @Override
    public void setTitle() {
        title = "faits marquants";
        validation = "Faits marquants ajouté!";
    }

    @Override
    public VerticalLayout popUpContent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
