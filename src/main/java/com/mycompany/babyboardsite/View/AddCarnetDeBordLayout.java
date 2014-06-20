/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author baptman
 */
public class AddCarnetDeBordLayout {

    public VerticalLayout layout;
    public Window subWindow;
    HorizontalLayout horizontalLayout;
    public String title;
    public String validation;
    
    public TextField name;
    public TextField firstName;
    public ComboBox sex;
    public PopupDateField date;
    public int idUser;
    
    

    public AddCarnetDeBordLayout(int idUser){
        this.idUser = idUser;
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
        setTitle();
        return horizontalLayout;
    }

    public void setTitle() {
        title = "Ajoutez un carnet de bord";
        validation = "Carnet de bord ajouté";
    }

    public void popup() {

//        PopUp sub = new PopUp();
        subWindow = new Window();
        VerticalLayout content = new VerticalLayout();
        content.addComponent(popUpContent());

        content.setMargin(true);
        subWindow.setContent(content);
        subWindow.center();
        subWindow.setCaption("Ajouter " + title);

        // Disable the close button
        subWindow.setClosable(true);

        // Trivial logic for closing the sub-window
        Button ok = new Button("Ajouter");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
//                addElement();
                Baby baby = new Baby(name.getValue(),date.getValue().toString(), Integer.parseInt(sex.getValue().toString()),
                        firstName.getValue(), idUser, "Postit");
                baby.addBaby();
                baby.addNurse(idUser, baby.getId());
                System.out.println("bébé crée");
                Notification.show(validation,
                        Notification.Type.TRAY_NOTIFICATION);
                subWindow.close();
                navigator.navigateTo(BabyView.NAME);
            }
        });
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        content.addComponent(ok);
        // Add it to the root component
        UI.getCurrent().addWindow(subWindow);

    }
    public FormLayout popUpContent(){
        FormLayout formulaire = new FormLayout();
        name = new TextField("Nom: ");
        firstName = new TextField("prénom: ");
        sex = new ComboBox("sexe: ");  
        sex.select("1");
        sex.addItem("1");
        sex.addItem("2");
        
        formulaire.addComponent(name);
        formulaire.addComponent(firstName);
        formulaire.addComponent(sex);
                date = new PopupDateField();
        date.setValue(new Date());
        date.setImmediate(true);
//        date.setTimeZone(TimeZone.getTimeZone("UTC"));
        date.setLocale(Locale.FRENCH);
//        date.setResolution(Resolution.MINUTE);
        formulaire.addComponent(date);
        return formulaire;
    }
}
