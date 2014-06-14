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
import com.mycompany.babyboardsite.Data.*;
import com.mycompany.babyboardsite.MyVaadinUI;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableUser extends Panel implements View {

    public static final String NAME = "tableUser";
    
    private Table table;
    private SQLContainer parent;
    private User user;
    public TableUser() {

        
        user = VaadinSession.getCurrent().getAttribute(User.class);
        Oracle oracle = new Oracle();
        parent = oracle.queryTable("users");
        table = new Table("Parents", parent);
        table = new Table("Inscription Parent", parent);
        table.setPageLength(20); // the number of rows per page
        table.setSizeFull(); // the table will fill the window
        table.setImmediate(true); // the server is notify each time I select a row or modify values
        table.setSelectable(true); // the user is allowed to select rows
        table.setMultiSelect(false); // the user is not allowed to select more than one row
        table.setEditable(true); // the user is allowed to modify values in the selected row
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        HeaderView header = new HeaderView();
        layout.addComponent(header);
        layout.addComponent(user.printUserInfo());
        //contenu de la page
        Button saveButton = new Button("Sauvegarder");
        saveButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                try {
                    parent.commit();
                } catch (UnsupportedOperationException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //afficher le tableau sur la page
        layout.addComponent(table);
        //afficher le bouton sauvegarde
        layout.addComponent(saveButton);

        //On crée un bouton d'ajout d'item
        Button addContact = new Button("Ajouter un contact");

        addContact.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                
               // On récupère la dernière ligne de la table parent
                Object lastentry = parent.lastItemId();
                // On cherche la valeur de la colonne idUser de cette derniere entrée
                Object lastidvalue = table.getContainerProperty(lastentry, "idUser").getValue();
                
                //on transforme cette valeur en int
                int idsup = Integer.parseInt(lastidvalue.toString())+1;
                //on retransforme cet int en objet pour l'afficher
                Object idnewparent = (int)idsup;

                parent.removeAllContainerFilters();
                Object contactId = table.addItem();

                table.getContainerProperty(contactId, "name").setValue(
                        "name");
                table.getContainerProperty(contactId, "firstName").setValue(
                        "firstName");
                //On incrémente automatiquement l'idUser
                table.getContainerProperty(contactId, "idUser").setValue(
                        idnewparent);


                table.select(contactId);

            }
        });
        layout.addComponent(addContact);

        Button deleteBt = new Button("Supprimer le contact");
        deleteBt.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                RowId itemID = (RowId) table.getValue(); // retrieves the id of the record
                if (itemID != null) {
                    parent.removeItem(itemID); // remove the record from the container
                }
                try {
                    parent.commit();
                } catch (UnsupportedOperationException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        layout.addComponent(deleteBt);
        
        setContent(layout);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
