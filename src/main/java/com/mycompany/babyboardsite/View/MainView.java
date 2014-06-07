package com.mycompany.babyboardsite.View;

/**
 *
 * @author baptman
 */
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.mycompany.babyboardsite.Data.*;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@Theme("mytheme")
public class MainView extends Panel implements View {

    public static final String NAME = "";
    private User user;

    //Vue d'accueil du site (avant le log)
    public MainView() {

        user = VaadinSession.getCurrent().getAttribute(User.class);
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        //Si l'utilisateur n'est pas connecté
        if (!user.isConnected()) {
            Link linkConnection = new Link("Page de connexion", new ExternalResource("#!"
                    + Connection.NAME));
            layout.addComponent(linkConnection);
            Link linkSubcribe = new Link("Page d'inscription", new ExternalResource("#!"
                    + Subscribe.NAME));

            layout.addComponent(linkSubcribe);
        } else {//Si il est connecté
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
                    VaadinSession.getCurrent().close();
                    navigator.navigateTo(MainView.NAME);

                }
            });
            layout.addComponent(deconnectionButton);

        }
        setContent(layout);

    }

    public void enter(ViewChangeEvent event) {

    }

}
