package com.mycompany.babyboardsite.View;

import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.event.LayoutEvents;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.mycompany.babyboardsite.Data.Baby;

/**
 *
 * @author baptman
 */
//Classe pour la vue BabyView, permettant de créer le textarea contenant un bébé et lors du clique
//ajoute un paramètre "babyNumber" pour identifier le bébé sélectionné dans VaadinSession.
//babyNumber représente la posittion du bébé dans list<baby> de l"utilisateur
public class BabyLayout {

    private int id;
    private VerticalLayout verticalLayout;

    BabyLayout(int i, Baby baby) {
        id = i;
        verticalLayout = new VerticalLayout();
        TextArea area1 = new TextArea(baby.getFisrtname() + " " + baby.getName());
        area1.setWordwrap(true); // The default
        area1.setValue(baby.getOld());
        verticalLayout.addComponent(area1);

        verticalLayout.addListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {

                VaadinSession.getCurrent().setAttribute("babyNumber", id);
                navigator.navigateTo(BabyboardView.NAME);
            }
        });
    }

    public Layout getLayout() {
        return verticalLayout;
    }

}
