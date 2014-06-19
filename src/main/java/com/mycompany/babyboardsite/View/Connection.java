package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import static com.mycompany.babyboardsite.MyVaadinUI.*;
import com.vaadin.event.ShortcutAction.KeyCode;
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
public class Connection extends Panel implements View {

    public static final String NAME = "";

    private TextField textFieldEmail = new TextField("Email:");
    private PasswordField textFieldPassword = new PasswordField("Password:");
    public FormLayout formLayout;
    private User user;

    public Connection() {
        user = VaadinSession.getCurrent().getAttribute(User.class);

        if (!user.isConnected()) {
            Link linkSubcribe = new Link("Page d'inscription", new ExternalResource("#!"
                    + Subscribe.NAME));

            //layout contenant le formulaire de connexion
            formLayout = new FormLayout();
            formLayout.setSizeUndefined();
            formLayout.addComponent(textFieldEmail);
            // Mark field as required
            textFieldEmail.setRequired(true);
            textFieldEmail.setRequiredError("requis!");

            formLayout.addComponent(textFieldPassword);
            textFieldPassword.setRequired(true);
            textFieldPassword.setRequiredError("requis!");

            //bouton de connexion
            Button connectionButton = new Button("Connexion");
            // press enter to validate form
            connectionButton.setClickShortcut(KeyCode.ENTER);
            //Lien pour le retour à la page d'accueil

            connectionButton.addClickListener(new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    //On vérifie que  le couple email et mot de passe de passe de l'utilisateur
                    //corresponde à un utilsateur dans la table user
                    if (user.checkEmailPassword(textFieldEmail.getValue(), textFieldPassword.getValue())) {
                        //Si le mot de passe et le mail sont bon, on instancie l'utilisateur avec le
                        //constructeur utilisant l'email et le mot de passe
                        user = new User(textFieldEmail.getValue(), textFieldPassword.getValue());
                        //TEST: affiche certaine valeur de l'utilisateur
                        formLayout.addComponent(user.printUserInfo());
                        //On stocke l'utilisateur récupérer dans les variables de session Vaadin
                        VaadinSession.getCurrent().setAttribute(User.class, user);
                        //On rédirige ensuite l'utilisateur dans le menu
                        navigator.navigateTo(BabyView.NAME);
                    }
                }
            });

            formLayout.addComponent(connectionButton);
            formLayout.addComponent(linkSubcribe);
            setContent(formLayout);
        } else {//Si il est connecté
            VerticalLayout layout = new VerticalLayout();
            layout.setMargin(true);
            layout.addComponent(user.printUserInfo());
            Link linkTableUser = new Link("TableUser", new ExternalResource("#!"
                    + TableUser.NAME));
            layout.addComponent(linkTableUser);

            Link linkBabyView = new Link("BabyView", new ExternalResource("#!"
                    + BabyView.NAME));
            layout.addComponent(linkBabyView);

            Button deconnectionButton = new Button("Déconnexion");
            deconnectionButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent event) {

                    logout();
                }
            });
            layout.addComponent(deconnectionButton);
            setContent(layout);
        }
    }

    private void logout() {
        // Close the VaadinServiceSession
        getUI().getSession().close();

        // Invalidate underlying session instead if login info is stored there
        // VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        // Redirect to avoid keeping the removed UI open in the browser
        getUI().getPage().setLocation(Connection.NAME);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
}
