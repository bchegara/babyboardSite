package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author baptman
 */
//Classe pour la vue BabyView, permettant de créer le textarea contenant un bébé et lors du clique
//ajoute un paramètre "babyNumber" pour identifier le bébé sélectionné dans VaadinSession.
//babyNumber représente la posittion du bébé dans list<baby> de l"utilisateur
public class BabyLayout {

    private int id;
    private VerticalLayout verticalLayout;
    public Window subWindow;
    private VerticalLayout V1;

    BabyLayout(int i, Baby baby, Boolean canModify) {
        id = i;
        verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setHeight(25, Sizeable.Unit.EM);
        panel.setWidth(30, Sizeable.Unit.EM);
        panel.addStyleName("baby-title");

        Label babyNameL = new Label(baby.getName() + " " + baby.getFirstname());
        Label contentBabyLayout = new Label(baby.getOld());
        VerticalLayout layoutContenu = new VerticalLayout();
        layoutContenu.addComponent(babyNameL);
        layoutContenu.addComponent(contentBabyLayout);


//        TextArea area1 = new TextArea(baby.getName() + " " + baby.getFirstname());
//        area1.addStyleName("baby-title");
//        area1.setWordwrap(true); // The default
//        area1.setValue(baby.getOld());

        if (canModify) {
            Button addNurse = new Button("Ajoutez une nourrice");
            addNurse.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent event) {

                    //fonction affichage des nurse disponibles
                    openNurses();
                }
            });
            layoutContenu.addComponent(addNurse);
        }
        verticalLayout.addListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {

                VaadinSession.getCurrent().setAttribute("babyNumber", id);
                navigator.navigateTo(BabyboardView.NAME);
            }
        });
                panel.setContent(layoutContenu);
                verticalLayout.addComponent(panel);
    }

    public void openNurses() {
        //ouvrir une pop
        int i = 1;
        //appel bdd pour check nurses dispo
        //gérer le contenu de la pop up
        if (i == 1) {
            //afficher la liste des nurses dispo
            //display nurses dispo
            V1 = new VerticalLayout();
        } else if (i == 2) {
            //pas de nurses dispo

        } else {
            //error       
        }
        popup(V1);

    }
    //le vertical layout va servir a contenir les différentes possiblités

    public void popup(VerticalLayout Vlayout) {
//        PopUp sub = new PopUp();
        subWindow = new Window();
        VerticalLayout content = new VerticalLayout();
        //contenu de la pop up

        content.addComponent(Vlayout);

        content.setMargin(true);
        subWindow.setContent(content);
        subWindow.center();
        subWindow.setCaption("Ajout Nourrice");

        // Disable the close button
        subWindow.setClosable(true);

        // Trivial logic for closing the sub-window
        Button ok = new Button("+ 1");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                //association bébé à nourice

                subWindow.close();
                navigator.navigateTo(BabyView.NAME);
            }
        });
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        content.addComponent(ok);
        // Add it to the root component
        UI.getCurrent().addWindow(subWindow);

    }

    public VerticalLayout getLayout() {
        return verticalLayout;
    }

    void addStyleName(String babytitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
