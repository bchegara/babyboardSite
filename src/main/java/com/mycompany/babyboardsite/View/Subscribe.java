/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author geoffroyrouaix
 */
public class Subscribe extends Panel implements View {

    public static final String NAME = "Subscribe";
    private User user;

    public Subscribe() {

        user = VaadinSession.getCurrent().getAttribute(User.class);

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        
        FormLayout form = new FormLayout();
        
        layout.addComponent(form);

        
        layout.addComponent(new Button("OK", new ClickListener() {
            public void buttonClick(ClickEvent event) {
                try {
                    System.out.println("");
                } catch (Exception e) {
                    
                }
            }

        }));

        setContent(layout);

    }

    public void enter(ViewChangeEvent event) {

    }

}