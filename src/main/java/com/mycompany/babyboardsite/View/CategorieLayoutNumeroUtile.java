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
import com.mycompany.babyboardsite.MyVaadinUI;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieLayoutNumeroUtile extends CategorieLayout {

    HorizontalLayout horizontalLayout;
    SQLContainer SQLContainerNumeroUtile;

    public CategorieLayoutNumeroUtile(Baby baby) {
        super(baby);
    }

    public HorizontalLayout getLayoutHorizontal() {
        horizontalLayout = new HorizontalLayout();

        Label numeroUtile = new Label("Numéros utiles");
        horizontalLayout.addComponent(numeroUtile);
        horizontalLayout.addListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                popup();
            }
        });
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
        title = "Numéros utiles";
        validation = "Numéro utile ajoutée";
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

        VerticalLayout role = new VerticalLayout();
        VerticalLayout nom = new VerticalLayout();
        VerticalLayout numero = new VerticalLayout();
        VerticalLayout adresse = new VerticalLayout();

        // Trivial logic for closing the sub-window
//        int test = baby.numeroUtileCategorie.getListAdresse().size();
        SQLContainerNumeroUtile = baby.numeroUtileCategorie.getSQLContainer();
        Table table = new Table("Parents", SQLContainerNumeroUtile);
        table.setPageLength(20); // the number of rows per page
        table.setSizeFull(); // the table will fill the window
        table.setImmediate(true); // the server is notify each time I select a row or modify values
        table.setSelectable(true); // the user is allowed to select rows
        table.setMultiSelect(false); // the user is not allowed to select more than one row
        table.setEditable(true); // the user is allowed to modify values in the selected row
        content.addComponent(table);
        
        Button saveButton = new Button("Sauvegarder");
        saveButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    SQLContainerNumeroUtile.commit();
                } catch (UnsupportedOperationException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        content.addComponent(saveButton);
        
        Button ok = new Button("OK+ " + SQLContainerNumeroUtile.size());
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                addElement();
                Notification.show(validation,
                        Notification.Type.TRAY_NOTIFICATION);
                subWindow.close();
                navigator.navigateTo(BabyboardView.NAME);
            }
        });
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        content.addComponent(ok);
        // Add it to the root component
        UI.getCurrent().addWindow(subWindow);

    }

}
