package com.mycompany.babyboardsite.View;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import java.io.File;

/**
 *
 * @author geoffroyrouaix
 */
public class HeaderView extends Panel implements View {
private CssLayout layout;
    public HeaderView() {
        

        layout = new CssLayout();
        layout.addStyleName("header");

        //logo
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
        Image image = new Image(null, resource);
        image.addStyleName("logo");
        layout.addComponent(image);

        //menu
        CssLayout menu = new CssLayout();
        menu.addStyleName("menu");
        Link lnk = new Link("Accueil", new ExternalResource("#!"));
        Link lnk2 = new Link("Mes enfants", new ExternalResource("#!" + BabyView.NAME));
        lnk.addStyleName("menu-link");
        lnk2.addStyleName("menu-link");
        Button deconnectionButton = new Button("Déconnexion");
        deconnectionButton.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                
                VaadinSession.getCurrent().close();
                try{
                    getUI().getPage().setLocation(Connection.NAME);
                }catch(Exception e){
                    System.out.println("erreur redirection");
                }
                
            }
        });
        menu.addComponent(lnk);
        menu.addComponent(lnk2);
        menu.addComponent(deconnectionButton);
        layout.addComponent(menu);
//        setContent(layout);
//        return layout;
    }

    public CssLayout getLayout() {
        return layout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
