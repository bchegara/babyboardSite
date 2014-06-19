/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.User;
import com.mycompany.babyboardsite.MyVaadinUI;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baptman
 */
public class AddCarnetDeBordLayout extends CategorieLayout {

    HorizontalLayout horizontalLayout;

    public AddCarnetDeBordLayout(Baby baby) {
        super(baby);
    }

    public HorizontalLayout getLayoutHorizontal() {
        horizontalLayout = new HorizontalLayout();

        Button addCarnetDeBord = new Button("Créez un carnet de bord");

        addCarnetDeBord.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                popup();
            }
        });
        horizontalLayout.addComponent(addCarnetDeBord);
//        horizontalLayout.addListener(new LayoutEvents.LayoutClickListener() {
//            @Override
//            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
//                popup();
//            }
//        });
        return horizontalLayout;
    }

    @Override
    public VerticalLayout contenu(Baby baby) {
        layout = new VerticalLayout();
        VerticalLayout layoutNumeroUtile = new VerticalLayout();
        Label numeroUtile = new Label("Numéros utiles");
        layoutNumeroUtile.addComponent(numeroUtile);
        return layoutNumeroUtile;
    }

    @Override
    public void setTitle() {
        title = "Ajoutez un carnet de bord";
        validation = "Carnet de bord ajouté";
    }

    @Override
    public VerticalLayout popUpContent() {
        VerticalLayout contenuPopUp = new VerticalLayout();
        return contenuPopUp;

    }

    @Override
    public void addElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void popup() {
        subWindow = new Window();
        subWindow.center();
        subWindow.setCaption(title);

        VerticalLayout content = new VerticalLayout();
        content.addComponent(popUpContent());

        content.setMargin(true);
        subWindow.setContent(content);
        // Disable the close button
        subWindow.setClosable(true);

        
        UI.getCurrent().addWindow(subWindow);

    }
}
