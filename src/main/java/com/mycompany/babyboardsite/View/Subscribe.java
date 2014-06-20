/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author geoffroyrouaix
 */
//Vue d'inscription
public class Subscribe extends Panel implements View {

    public static final String NAME = "Subscribe";
    private TextField textFieldEmail = new TextField("Email:");
    private TextField textFieldEmailCheck = new TextField("Verification Email:");
    private TextField textFieldFirst = new TextField("Prénom:");
    private TextField textFieldLast = new TextField("Nom:");
    private PasswordField textFieldPassword = new PasswordField("Password:");
    private PasswordField textFieldPasswordCheck = new PasswordField("Verification Password:");
    private ComboBox rightLevel = new ComboBox("Type d'utilisateur: ");
    private TextField textFieldtel = new TextField("Téléphone: ");
    private TextField textFieldCity = new TextField("Ville: ");
    private TextField textFieldZip = new TextField("Code postal: ");
    private TextField textFieldAdresse = new TextField("Adresse: ");
    public VerticalLayout layout;
    public FormLayout subscribeLayout;
    private User user;
    public int i = 0;

    public Subscribe() {
        rightLevel.addItem("Parent");
        rightLevel.addItem("Nounou");
        rightLevel.select("Parent");
        user = VaadinSession.getCurrent().getAttribute(User.class);
        //layout contenant le formulaire d'inscription
        layout = new VerticalLayout();
        layout.addComponent(new HeaderHome().getLayout());

        subscribeLayout = new FormLayout();
        subscribeLayout.setSizeUndefined();
        subscribeLayout.addComponent(textFieldFirst);
        subscribeLayout.addComponent(textFieldLast);
        subscribeLayout.addComponent(rightLevel);
        subscribeLayout.addComponent(textFieldtel);
        subscribeLayout.addComponent(textFieldEmail);
        subscribeLayout.addComponent(textFieldEmailCheck);
        subscribeLayout.addComponent(textFieldCity);
        subscribeLayout.addComponent(textFieldZip);
        subscribeLayout.addComponent(textFieldAdresse);

        layout.addComponent(new HeaderHome());

        // Mark field as required
        rightLevel.setRequired(true);
        rightLevel.setRequiredError("requis!");
        textFieldAdresse.setRequired(true);
        textFieldAdresse.setRequiredError("requis!");
        textFieldZip.setRequired(true);
        textFieldZip.setRequiredError("requis!");
        textFieldCity.setRequired(true);
        textFieldCity.setRequiredError("requis!");
        textFieldtel.setRequired(true);
        textFieldtel.setRequiredError("requis!");
        textFieldEmailCheck.setRequired(true);
        textFieldEmailCheck.setRequiredError("requis!");
        textFieldFirst.setRequired(true);
        textFieldFirst.setRequiredError("requis!");
        textFieldLast.setRequired(true);
        textFieldLast.setRequiredError("requis!");
        textFieldEmail.setRequired(true);
        textFieldEmail.setRequiredError("requis!");

        subscribeLayout.addComponent(textFieldPassword);
        subscribeLayout.addComponent(textFieldPasswordCheck);

        textFieldPassword.setRequired(true);
        textFieldPassword.setRequiredError("requis!");
        textFieldPasswordCheck.setRequired(true);
        textFieldPasswordCheck.setRequiredError("requis!");

        //bouton de connexion
        Button subscribeButton = new Button("S'inscrire");
        // press enter to validate form
        subscribeButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        //Lien pour le retour à la page d'accueil
        Link lnk = new Link("Accueil", new ExternalResource("#!" + Connection.NAME));
        subscribeLayout.addComponent(lnk);
        subscribeButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                //check champs non vides
                if (user.checkNotEmpty(textFieldFirst.getValue(), textFieldLast.getValue(),
                        textFieldPassword.getValue(), textFieldEmail.getValue())) {

                    //On vérifie que  le couple email et mot de passe de passe de l'utilisateur
                    //corresponde à un utilsateur dans la table user
                    if (textFieldPassword.getValue().equals(textFieldPasswordCheck.getValue())) {
                        if (textFieldEmail.getValue().equals(textFieldEmailCheck.getValue())) {

                            if (user.checkEmail(textFieldEmail.getValue())) {
                                //insert dans la base de données
                                user.addUser(textFieldFirst.getValue(), textFieldLast.getValue(), textFieldEmail.getValue(), textFieldPassword.getValue(), rightLevel.getValue().toString(),
                                        textFieldtel.getValue(), textFieldAdresse.getValue(), textFieldCity.getValue(), Integer.parseInt(textFieldZip.getValue()));
                                navigator.navigateTo(Connection.NAME);

                            } else {
                                VaadinSession.getCurrent().setAttribute("num", 3);
                                navigator.navigateTo(Subscribe.NAME);
                                //un utilisateur possède deja cette adresse email
                            }
                        } else {
                            VaadinSession.getCurrent().setAttribute("num", 1);
                            navigator.navigateTo(Subscribe.NAME);

                            //return erreur password ou email verification
                        }
                    } else {
                        VaadinSession.getCurrent().setAttribute("num", 2);
                        navigator.navigateTo(Subscribe.NAME);

                        //return erreur password ou email verification
                    }

                    VaadinSession.getCurrent().setAttribute(User.class, user);

                } else {
                    VaadinSession.getCurrent().setAttribute("num", 0);
                    navigator.navigateTo(Subscribe.NAME);
                }

            }

        });
        if (VaadinSession.getCurrent().getAttribute("num") != null) {
            i = Integer.parseInt(VaadinSession.getCurrent().getAttribute("num").toString());
        } else {
            i = 0;
        }
        subscribeLayout.addComponent(user.printErrorSubscribe(i));

        subscribeLayout.addComponent(subscribeButton);
        layout.addComponent(subscribeLayout);
        setContent(layout);

    }

    public void enter(ViewChangeEvent event) {

    }

}
