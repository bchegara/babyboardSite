/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
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
public class Subscribe extends Panel implements View {

    public static final String NAME = "Subscribe";
    private TextField textFieldEmail = new TextField("Email:");
    private TextField textFieldEmailCheck = new TextField("Verification Email:");
    private TextField textFieldFirst = new TextField("Prénom:");
    private TextField textFieldLast = new TextField("Nom:");
    private PasswordField textFieldPassword = new PasswordField("Password:");
    private PasswordField textFieldPasswordCheck = new PasswordField("Verification Password:");

    public FormLayout subscribeLayout;
    private User user;
    private int errorSubscribing;

    public Subscribe() {

        user = VaadinSession.getCurrent().getAttribute(User.class);
        errorSubscribing = 1;
        //layout contenant le formulaire d'inscription
        subscribeLayout = new FormLayout();
        subscribeLayout.setSizeUndefined();
        subscribeLayout.addComponent(textFieldFirst);
        subscribeLayout.addComponent(textFieldLast);
        subscribeLayout.addComponent(textFieldEmail);
        subscribeLayout.addComponent(textFieldEmailCheck);

        // Mark field as required
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
        Link lnk = new Link("Accueil", new ExternalResource("#!"));
        subscribeLayout.addComponent(lnk);
        subscribeButton.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                //On vérifie que  le couple email et mot de passe de passe de l'utilisateur
                //corresponde à un utilsateur dans la table user
                if (textFieldPassword.getValue().equals(textFieldPasswordCheck.getValue())
                        && textFieldEmail.getValue().equals(textFieldEmailCheck.getValue())
                        && textFieldEmail != null
                        && textFieldPassword != null
                        && textFieldFirst != null) {

                    if (user.checkEmail(textFieldEmail.getValue())) {
                        //insert dans la base de données
                        user.addUser(textFieldFirst.getValue(), textFieldLast.getValue(), textFieldPassword.getValue(), textFieldEmail.getValue());
                        navigator.navigateTo(Connection.NAME);
                        errorSubscribing = 0;

                    } else {
                        navigator.navigateTo(Subscribe.NAME);
                        //un utilisateur possède deja cette adresse email
                    }
                } else {
                    navigator.navigateTo(Subscribe.NAME);

                    //return erreur password ou email verification
                }
                VaadinSession.getCurrent().setAttribute(User.class, user);

            }
        });
        if (errorSubscribing == 1) {
            subscribeLayout.addComponent(user.printErrorSubscribe());
        }
        subscribeLayout.addComponent(subscribeButton);
        setContent(subscribeLayout);

    }

    public void enter(ViewChangeEvent event) {

    }

}
