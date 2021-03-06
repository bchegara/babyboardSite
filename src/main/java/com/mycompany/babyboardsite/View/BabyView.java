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
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

//Vue présenter les enfants associés à un compte utilisateur
public class BabyView extends Panel implements View {

    public static final String NAME = "Baby";
    private User user;
    //permet d'identifier le bébé choisit par l'utilisateur, variable ensuite stockée dans la 
    //VaadinSession grace à la classe BabyLayout. La variable représente la position du bébé dans la liste
    private int babyNumber = 0;
    private HorizontalLayout ligneBaby;

    
    public BabyView() {
        user = VaadinSession.getCurrent().getAttribute(User.class);
        user.refreshUser();
        final VerticalLayout layout = new VerticalLayout();

        layout.addComponent(user.getHeader());
        //affiche le lien vers le tableau des utilisateur que si l'utilsateur à le rightLevel admin
        if (user.isAdmin()) {
            Link linkTableUser = new Link("TableUser", new ExternalResource("#!"
                    + TableUser.NAME));
            layout.addComponent(linkTableUser);
            layout.addComponent(linkTableUser);
        }
        if (user.isUser() || user.isAdmin()) {
            AddCarnetDeBordLayout addCarnetBord = new AddCarnetDeBordLayout(user.getId());
            layout.addComponent(addCarnetBord.getLayoutHorizontal());

        }
        //Si l'utilisateur n'a pas de bébé associé à son compte
        if (user.babyList.isEmpty()) {
            Label noChildMsg = new Label("Vous n'avez pas d'enfant associés à votre compte!");
            layout.addComponent(noChildMsg);
        } else {

            Label enfantTitle = new Label("Vos enfants: ");
            layout.addComponent(enfantTitle);
            try {
                VaadinSession.getCurrent().setAttribute("date", null);
            } catch (Exception e) {
                System.out.println("erreur reset date");
            }
            try {
                babyNumber = 0;
                //On parcourt la list des bébé associer à l'utilisateur
                ligneBaby = new HorizontalLayout();
                for (Baby baby : user.babyList) {
                    if (babyNumber % 3 == 0) {
                        layout.addComponent(ligneBaby);
                        ligneBaby = new HorizontalLayout();
                    }
                    BabyLayout babyLayout = new BabyLayout(babyNumber, baby, (user.getId() == baby.getIdParent()));
                    VerticalLayout babyVL = babyLayout.getLayout();
                    babyVL.setMargin(true);
                    ligneBaby.addComponent(babyVL);
                    babyNumber++;
                }
            } catch (Exception e) {
                System.out.println("erreur lors du parcourt de la liste des enfants");
            }
            layout.addComponent(ligneBaby);

        }

        setContent(layout);
    }

    public void enter(ViewChangeEvent event) {

    }
}
