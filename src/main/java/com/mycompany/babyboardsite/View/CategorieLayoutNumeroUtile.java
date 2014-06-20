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
import com.mycompany.babyboardsite.MyVaadinUI;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
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

//Classe pour la partie graphique du composant sieste du babyboard
//l'ajout de numéro ne fonctionne pas
public class CategorieLayoutNumeroUtile extends CategorieLayout {

    HorizontalLayout horizontalLayout;
    SQLContainer SQLContainerNumeroUtile;
    Table table;

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
        table = new Table("Numéros Utiles", SQLContainerNumeroUtile);
        table.setPageLength(20); // the number of rows per page
        table.setSizeFull(); // the table will fill the window
        table.setImmediate(true); // the server is notify each time I select a row or modify values
        table.setSelectable(true); // the user is allowed to select rows
        table.setMultiSelect(false); // the user is not allowed to select more than one row
        table.setEditable(true); // the user is allowed to modify values in the selected row
//a mettre dans le css de la table pour ne pas afficher l'élément permettant d'afficher les colonne caché
//         .v-table-column-selector {
//    height: 0px;
//    width: 0px;
//  } 
        table.setColumnCollapsingAllowed(true);
        table.setColumnCollapsed("idNumeroUtile", true);
        table.setColumnCollapsed("idBaby", true);
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

        Button addContact = new Button("Ajouter un contact");
        addContact.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {

//               // On récupère la dernière ligne de la table parent
//                Object lastentry = parent.lastItemId();
//                // On cherche la valeur de la colonne idUser de cette derniere entrée
//                Object lastidvalue = table.getContainerProperty(lastentry, "idUser").getValue();
//                
//                //on transforme cette valeur en int
//                int idsup = Integer.parseInt(lastidvalue.toString())+1;
//                //on retransforme cet int en objet pour l'afficher
//                Object idnewparent = (int)idsup;
//
//                parent.removeAllContainerFilters();
                Object contactId = table.addItem();
                table.getContainerProperty(contactId, "idNumeroUtile").setValue(
                        2);
                table.getContainerProperty(contactId, "idBaby").setValue(
                        2);
                table.getContainerProperty(contactId, "idBaby").setValue(
                        1);
                table.getContainerProperty(contactId, "role").setValue(
                        "role");
                table.getContainerProperty(contactId, "nom").setValue(
                        "nom");
                //On incrémente automatiquement l'idUser
                table.getContainerProperty(contactId, "numero").setValue(
                        "+336");
                table.getContainerProperty(contactId, "adresse").setValue(
                        "aresse");

                table.select(contactId);

            }
        });
        content.addComponent(addContact);
        
                Button deleteBt = new Button("Supprimer le contact");
        deleteBt.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                RowId itemID = (RowId) table.getValue(); // retrieves the id of the record
                if (itemID != null) {
                    SQLContainerNumeroUtile.removeItem(itemID); // remove the record from the container
                }
                try {
                    SQLContainerNumeroUtile.commit();
                } catch (UnsupportedOperationException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(MyVaadinUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        content.addComponent(deleteBt);

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
