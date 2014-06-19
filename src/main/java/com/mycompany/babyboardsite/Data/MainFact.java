package com.mycompany.babyboardsite.Data;

import com.vaadin.data.util.sqlcontainer.SQLContainer;

/**
 *
 * @author baptman
 */
public class MainFact {

    private String date;
    private int idFact;
    private int idBaby;
    private String title;
    private String description;
    private String hour;
    private SQLContainer factTable;

    //Classe représentant un fait marquant
    MainFact(int idF, int idB, String titleF, String descriptionF, String dateF, String hourF) {
        idFact = idF;
        idBaby = idB;
        title = titleF;
        description = descriptionF;
        hour = hourF;
        date = dateF;
    }
    
    //getteur
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHour() {
        return hour;
    }
}
