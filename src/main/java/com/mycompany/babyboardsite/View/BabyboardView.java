package com.mycompany.babyboardsite.View;

import com.mycompany.babyboardsite.Data.*;
import static com.mycompany.babyboardsite.MyVaadinUI.navigator;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Date;
import java.util.Locale;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author baptman
 */
public class BabyboardView extends Panel implements View {

    public static final String NAME = "Board";

    private User user;
    //permet d'identifier l'enfant dans la list d'enfant associé à l'utilisateur
    private int babyNumber = 0;
    private Baby baby;
    public Window subWindow;

    private Date date;
    private InlineDateField calendar;

    //Vue répresentant une page du carnet de bord d'un enfant
    public BabyboardView() {

        user = VaadinSession.getCurrent().getAttribute(User.class);
        final VerticalLayout layout = new VerticalLayout();

        layout.addComponent(new HeaderView());

        layout.addComponent(user.getHeader());

        //Si l'utilisateur a une liste d'enfant vide
        if (user.babyList.isEmpty()) {
            Label noChildLabel = new Label("Vous n'aver pas d'enfant associé à votre compte!");
            layout.addComponent(noChildLabel);
        } else {

            try {
                if (!isDateVaadinSession()) {
                    //On récupère le numéro de l'enfant mis dans la VaadinSession dans la vue BabyView
                    babyNumber = Integer.parseInt(VaadinSession.getCurrent().getAttribute("babyNumber").toString());
                    //on récupère l'enfant dans la liste 
                    baby = user.babyList.get(babyNumber);
                    //Si il n'y a pas de date stocké dans la VaadinSession on en redifinit une nouvelle
                    //ca sera la date du jour actuel

                    date = new java.util.Date();
                    baby.getBabyCategorie(formatDateToString(date));
                    baby.mainFactCategorie.setDate(formatDateToString(date));
                } else {
                    //Sinon on récupère la date
                    babyNumber = Integer.parseInt(VaadinSession.getCurrent().getAttribute("babyNumber").toString());
                    date = (Date) VaadinSession.getCurrent().getAttribute("date");
                    baby = user.babyList.get(babyNumber);

                    //Permet de changer la date pour les catégorie de l'enfant 
                    baby.changeDateBabyCategorie(formatDateToString(date));
                }
                layout.addComponent(baby.getPresentation());
                popup(baby.getPostIt());
                //Création du calendrier
                calendar = new InlineDateField();
                calendar.setValue(date);
                calendar.setLocale(Locale.FRENCH);
                calendar.addValueChangeListener(new ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        //Lors du changement de valeur dans le calendrier
                        //on récupère la nouvelle date
                        date = calendar.getValue();
                        //on stocke cette nouvelle date dans la VaadinSession
                        VaadinSession.getCurrent().setAttribute("date", date);
                        //on redirige l'utilisateur vers la vue du babyBoard, pour le réactualiser
                        //avec la nouvelle date
                        navigator.navigateTo(BabyboardView.NAME);

                        final String valueString = String.valueOf(event.getProperty()
                                .getValue());
                        Notification.show("Date sélectionnée: ", valueString,
                                Type.TRAY_NOTIFICATION);
                    }

                });
                try {
                    //on instancie l'objet stokant le layout des fait marquants
                    HorizontalLayout layoutL = new HorizontalLayout();
                    VerticalLayout categorieLayout = new VerticalLayout();
                    VerticalLayout categorieLayout2 = new VerticalLayout();
                    VerticalLayout calendarAndNumeroUtile = new VerticalLayout();

                    CategorieLayoutMainFact mainFactComponent = new CategorieLayoutMainFact(baby);
                    //on ajoute le layout fait marquant au layout de la page
                    categorieLayout.addComponent(mainFactComponent.getLayout());
                    //On ajoute le calendrier
                    CategorieLayoutActivitie activitieComponent = new CategorieLayoutActivitie(baby);
                    categorieLayout2.addComponent(activitieComponent.getLayout());

                    CategorieLayoutSieste siesteComponent = new CategorieLayoutSieste(baby);
                    categorieLayout.addComponent(siesteComponent.getLayout());
                    categorieLayout.setSpacing(true);

                    CategorieLayoutRepas repasComponent = new CategorieLayoutRepas(baby);
                    categorieLayout2.addComponent(repasComponent.getLayout());

//                    VerticalLayout calendarAndNumeroUtile = new VerticalLayout();
                    calendarAndNumeroUtile.addComponent(calendar);
                    CategorieLayoutNumeroUtile numeroUtileComponent = new CategorieLayoutNumeroUtile(baby);
                    calendarAndNumeroUtile.addComponent(numeroUtileComponent.getLayoutHorizontal());

                    layoutL.addComponent(categorieLayout);
                    layoutL.addComponent(categorieLayout2);
                    layoutL.addComponent(calendarAndNumeroUtile);
                    layout.addComponent(layoutL);

                } catch (Exception e) {
                    System.out.println("pas de d'infos");
                }

            } catch (Exception e) {
                //Si il n'y pas  la présence de la variable permettant d'identifier l'enfant dans la 
                //liste, on affiche un message d'erreur et affiche un lien pointant vers la page 
                // de selection d'enfant
                layout.addComponent(new Label("Pas d'enfant selectionné!"));
                Link linkBabyView = new Link("Selectionner un enfant", new ExternalResource("#!"
                        + BabyView.NAME));
                layout.addComponent(linkBabyView);
                System.out.println("e");
            }
        }
        setContent(layout);
    }

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    //Pour formater la date en String pour la récupération dans la bdd
    private String formatDateToString(Date d) {
        String dateInString;
        dateInString = d.toString().substring(0, 11) + d.toString().substring(19);
        return dateInString;
    }

    //fonction pour vérifier si une date est déjà présente dans la VaadinSession
    private Boolean isDateVaadinSession() {
        Boolean isDate;
        try {
            VaadinSession.getCurrent().getAttribute("date").toString();
            isDate = true;
        } catch (Exception e) {
            System.out.println("e");
            isDate = false;
        }
        return isDate;
    }

    public void popup(String postit) {
        subWindow = new Window();
        VerticalLayout content = new VerticalLayout();
        TextArea textArea = new TextArea();
        textArea.setValue(postit);

        content.addComponent(textArea);

        content.setMargin(true);
        subWindow.setContent(content);
        subWindow.center();
        //Set position windows
        subWindow.setPositionX(1200);
        subWindow.setPositionY(400);
        subWindow.setCaption("POST-IT");

        // Disable the close button
        subWindow.setClosable(false);

        // Trivial logic for closing the sub-window
        Button ok = new Button("Sauvegarder");
        ok.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                //modifier post it dans bdd
                //UI.getCurrent().removeWindow(subWindow);
            }
        });
        content.addComponent(ok);
        // Add it to the root component
        UI.getCurrent().addWindow(subWindow);

    }
}
