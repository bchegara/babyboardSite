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
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.DragAndDropWrapper.DragStartMode;
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
    public VerticalLayout layout;

    public Connection() {
        
        final CssLayout box = new CssLayout();
        box.addStyleName("connexion");
        user = VaadinSession.getCurrent().getAttribute(User.class);
        layout = new VerticalLayout();
        layout.addComponent(new HeaderHome());
        if (!user.isConnected()) {
//            layout = new VerticalLayout();
            Link linkSubcribe = new Link("Page d'inscription", new ExternalResource("#!"
                    + Subscribe.NAME));

            //layout contenant le formulaire de connexion
            formLayout = new FormLayout();
//            formLayout.addComponent(header);

            formLayout.setSizeFull();
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
                    if (textFieldEmail.getValue() != null & textFieldPassword.getValue() != null) {
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
                        } else {
                            System.out.println("test");
                            navigator.navigateTo(BabyView.NAME);
                        }
                    }
                }
            });

            formLayout.addComponent(connectionButton);
            formLayout.addComponent(linkSubcribe);
            layout.addComponent(formLayout);
            box.addComponent(layout);
            setContent(box);
        } else {//Si il est connecté
//            layout = new VerticalLayout();
//            layout.addComponent(header);
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
            box.addComponent(layout);
            setContent(box);
        }
    }

    private void logout() {
        // Close the VaadinServiceSession
//        getUI().getSession().close();
        VaadinSession.getCurrent().close();
        // Invalidate underlying session instead if login info is stored there
        // VaadinService.getCurrentRequest().getWrappedSession().invalidate();
        // Redirect to avoid keeping the removed UI open in the browser
        getUI().getPage().setLocation(Connection.NAME);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }
}
