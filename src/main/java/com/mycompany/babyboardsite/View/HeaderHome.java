package com.mycompany.babyboardsite.View;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import java.io.File;

/**
 *
 * @author geoffroyrouaix
 */
public class HeaderHome extends Panel implements View {

    private CssLayout layout;
    public HeaderHome() {

        layout = new CssLayout();
        layout.addStyleName("header");
        //logo
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
        FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logo.png"));
        Image image = new Image(null, resource);
        image.addStyleName("logo");
        layout.addComponent(image);
        CssLayout menu = new CssLayout();
        menu.addStyleName("menu");
        Link lnk = new Link("Accueil", new ExternalResource("#!"));
        Link lnk2 = new Link("Inscription", new ExternalResource("#!" + Subscribe.NAME));
        lnk.addStyleName("menu-link");
        lnk2.addStyleName("menu-link");
        menu.addComponent(lnk);
        menu.addComponent(lnk2);
        layout.addComponent(menu);

        
//        setContent(layout);
//        return layout;

    }
    public CssLayout getLayout(){
        return layout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
