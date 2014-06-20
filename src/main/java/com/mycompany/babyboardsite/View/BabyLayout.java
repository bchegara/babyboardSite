package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.Baby;
import com.mycompany.babyboardsite.Data.User;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

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
    public Window subWindow;
    private VerticalLayout V1;
    private Table tableNurse;
    private Baby baby;

    BabyLayout(int i, Baby baby, Boolean canModify) {
        this.baby = baby;
        id = i;
        verticalLayout = new VerticalLayout();
        Panel panel = new Panel();
        panel.setHeight(25, Sizeable.Unit.EM);
        panel.setWidth(30, Sizeable.Unit.EM);
        panel.addStyleName("pointerLink");

        Label babyNameL = new Label(baby.getName() + " " + baby.getFirstname());
        babyNameL.addStyleName("baby-title");
        Label contentBabyLayout = new Label("Date de naissance: " + baby.getOld());
        contentBabyLayout.addStyleName("pointerLink");
        VerticalLayout layoutContenu = new VerticalLayout();
        layoutContenu.addComponent(babyNameL);
        layoutContenu.addComponent(contentBabyLayout);
        if (canModify) {
            Button addNurse = new Button("Ajoutez une nourrice");
            addNurse.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent event) {
                    popup();
                }
            });
            Label desc = new Label("Utilisateur(s) associé(s) au bébé:");
            desc.addStyleName("pointerLink");
            layoutContenu.addComponent(desc);
            for (String userName : baby.getUserAssociated()) {
                Label userNameL = new Label(userName);
                userNameL.addStyleName("pointerLink");
                layoutContenu.addComponent(userNameL);
            }

            layoutContenu.addComponent(addNurse);
        }
        verticalLayout.addListener(new LayoutEvents.LayoutClickListener() {
            @Override
            public void layoutClick(LayoutEvents.LayoutClickEvent event) {

                VaadinSession.getCurrent().setAttribute("babyNumber", id);
                navigator.navigateTo(BabyboardView.NAME);
            }
        });
        panel.setContent(layoutContenu);
        verticalLayout.addComponent(panel);
    }

//    public void popup(VerticalLayout Vlayout) {
    public void popup() {
//        PopUp sub = new PopUp();
        subWindow = new Window();
        VerticalLayout content = new VerticalLayout();
        //contenu de la pop up

//        content.addComponent(Vlayout);
        content.setMargin(true);
        subWindow.setContent(content);
        subWindow.center();
        subWindow.setCaption("Ajout Nourrice");

        // Disable the close button
        subWindow.setClosable(true);
        User user;
        user = VaadinSession.getCurrent().getAttribute(User.class);

        tableNurse = new Table("Associer une nourrice à " + baby.getName() + " " + baby.getFirstname(), user.getSQLContainerNurse());
        tableNurse.setPageLength(20); // the number of rows per page
        tableNurse.setImmediate(true); // the server is notify each time I select a row or modify values
        tableNurse.setSelectable(true); // the user is allowed to select rows
        tableNurse.setMultiSelect(false); // the user is not allowed to select more than one row
        tableNurse.setEditable(false); // the user is allowed to modify values in the selected row
        // Trivial logic for closing the sub-window

        tableNurse.setColumnCollapsingAllowed(true);
        tableNurse.setColumnCollapsed("rightLevel", true);
        tableNurse.setColumnCollapsed("idUser", true);
        tableNurse.setColumnCollapsed("password", true);
        content.addComponent(tableNurse);
        Button ok = new Button("Ajouter");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                //association bébé à nourice
                try {
                    System.out.println(tableNurse.getValue().toString());

                    baby.addNurse(Integer.parseInt(tableNurse.getValue().toString()), baby.getId());
                    subWindow.close();
                    navigator.navigateTo(BabyView.NAME);
                } catch (Exception e) {
                    System.out.println("erreur ajout nourrice");
                }
            }
        });
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        content.addComponent(ok);
        // Add it to the root component
        UI.getCurrent().addWindow(subWindow);

    }

    public VerticalLayout getLayout() {
        return verticalLayout;
    }

}
