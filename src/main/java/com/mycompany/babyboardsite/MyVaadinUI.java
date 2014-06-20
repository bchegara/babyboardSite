package com.mycompany.babyboardsite;

import com.mycompany.babyboardsite.Data.*;
import com.mycompany.babyboardsite.View.*;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import javax.servlet.annotation.WebServlet;

@Title("Babyboard")
//@Theme("ourtheme")
@Theme("ourtheme")
@SuppressWarnings("serial")
@PreserveOnRefresh
public class MyVaadinUI extends UI {

    public static Navigator navigator;
    
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "com.mycompany.babyboardsite.AppWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        //Si il n'y pas d'utilisateur dans les variables de session, on crée un nouveau
        if (!isUserInVaadinSession()) {
            //on met dans la session de l'utilisateur un nouvelle objet de classe User
            VaadinSession.getCurrent().setAttribute(User.class, new User());
        }

        // Create Navigator, use the UI content layout to display the views
        navigator = new Navigator(this, this);

        // Add some Views
        //Deux types de méthodes pour utiliser les vues avec les navigator:
        //soit créer un objet ex: MainView mainVue = new MainView();
        // et la passer au navigator: navigator.addView(MainView.NAME, mainVue);
        //la page ne sera dans ce cas pas rafraichit à chaque appel et contiendra les données préalablement
        //saisie
        //Ou
        //passer au navigator la classe contenant la vue ex: navigator.addView(MainView.NAME, MainView.class);
        //Dans ce cas la page sera actualisé à chaque fois que le navigator l'appel
        //utilise une nouvelle vue à chaque fois
        //Vue principale lorsque l'utilisateur accède au site
//        navigator.addView(MainView.NAME, MainView.class);
        //utilise la même vue à chaque appel
        //Vue pour la connexion des utilisateurs
        navigator.addView(Connection.NAME, Connection.class); // no fragment

        //Vue affichant le cotnenue de la table utilisateur dans un tableau permettant de modifier la bdd
        navigator.addView(TableUser.NAME, TableUser.class);

        //Vue 
        navigator.addView(BabyView.NAME, BabyView.class);

        BabyboardView bordView = new BabyboardView();
        navigator.addView(BabyboardView.NAME, BabyboardView.class);

        navigator.addView(Subscribe.NAME, Subscribe.class);

    }

    //Fonction permettant de voir si il y a la présence d'un utilisateur dans la session vaadin
    public Boolean isUserInVaadinSession() {
        Boolean isUser;
        try {
            VaadinSession.getCurrent().getAttribute(User.class).getName();
            isUser = true;
        } catch (Exception e) {
            System.out.println("e");
            isUser = false;
        }
        return isUser;
    }

}
