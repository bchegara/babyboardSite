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
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class BabyView extends Panel implements View {

    public static final String NAME = "Baby";
    private User user;
    //permet d'identifier le bébé choisit par l'utilisateur, variable ensuite stockée dans la 
    //VaadinSession grace à la classe BabyLayout. La variable représente la position du bébé dans la liste
    private int babyNumber = 0;

    public BabyView() {

        user = VaadinSession.getCurrent().getAttribute(User.class);

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        //Si l'utilisateur n'a pas de bébé associé à son compte
        if (user.babyList.isEmpty()) {
            Label noChildMsg = new Label("Vous n'aver pas d'enfant associé à votre compte!");
            layout.addComponent(noChildMsg);
        } else {
            try {
                babyNumber = 0;
                //On parcourt la list des bébé associer à l'utilisateur
                for (Baby baby : user.babyList) {
                    BabyLayout babyLayout = new BabyLayout(babyNumber, baby);
                    layout.addComponent(babyLayout.getLayout());
                    babyNumber++;
                }
            } catch (Exception e) {
                System.out.println("e");
            }
        }
        setContent(layout);
    }

    public void enter(ViewChangeEvent event) {

    }
}
