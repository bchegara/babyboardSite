/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author baptman
 */
public abstract class CategorieLayout {

    private Window subWindow;
    public VerticalLayout layout;
    public String title;
    public Baby baby;
    public String validation;
    

    //Constructeur
    public CategorieLayout(Baby baby) {
        this.baby = baby;
        setTitle();
        final VerticalLayout factComponent = new VerticalLayout();
        layout = new VerticalLayout();
        layout.setMargin(true);
        final HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.addComponent(new Label(title));
        final Button open = new Button("+");
        open.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                popup();
            }
        });
        titleLayout.addComponent(open);
        layout.addComponent(titleLayout);
        layout.addComponent(contenu(baby));
    }
    public VerticalLayout getLayout(){
        return layout;
    }
    
    public abstract VerticalLayout contenu(Baby baby);
    
    public abstract void setTitle();
    
    public void popup() {
//        PopUp sub = new PopUp();
        subWindow = new Window();
        VerticalLayout content = new VerticalLayout();
        content.addComponent(popUpContent());
        
        content.setMargin(true);
        subWindow.setContent(content);
        subWindow.center();
        subWindow.setCaption("Ajouter "+title);

        // Disable the close button
        subWindow.setClosable(true);

        // Trivial logic for closing the sub-window
        Button ok = new Button("OK");
        ok.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                addElement();
                Notification.show(validation,
                                Notification.Type.TRAY_NOTIFICATION);
                subWindow.close();
                navigator.navigateTo(BabyboardView.NAME);
            }
        });
        content.addComponent(ok);
        // Add it to the root component
        UI.getCurrent().addWindow(subWindow);

    }
    public abstract VerticalLayout popUpContent();
    public abstract void addElement();

}
