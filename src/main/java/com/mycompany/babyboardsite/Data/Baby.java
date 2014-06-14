package com.mycompany.babyboardsite.Data;

import com.vaadin.data.Item;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author baptman
 */
public class Baby {

    //Information contenu dans la table sql
    private int idBaby;
    private String name;
    private String old;
    private int sex;
    private String firstname;
    public MainFactCategorie mainFactCategorie;

    Oracle oracle = new Oracle();
    private SQLContainer babyTable;
    private SQLContainer factTable;
    public List<MainFact> FactList;

    //getteur
    public int getId() {
        return idBaby;
    }

    public String getName() {
        return name;
    }

    public String getOld() {
        return old;
    }

    public int getSex() {
        return sex;
    }

    public String getFisrtname() {
        return firstname;
    }

    //Constructeur pour récupérer un bébé avec son idBay
    public Baby(int idB) {

        babyTable = oracle.queryTable("babies");
        babyTable.addContainerFilter(new Compare.Equal("idBaby", idB));// WHERE idBaby=idBaby
        idBaby = idB;
        Item infoUser = babyTable.getItem(new RowId(new Object[]{idBaby}));
        name = infoUser.getItemProperty("name").getValue().toString();
        old = infoUser.getItemProperty("age").getValue().toString();
        sex = Integer.parseInt(infoUser.getItemProperty("sex").getValue().toString());
        firstname = infoUser.getItemProperty("firstName").getValue().toString();
    }

    //TEST: pour afficher quelques infos du bébé
    public Component printBabyInfo() {
        Label infoB = new Label("name " + name + " firstname: " + firstname);
        return infoB;
    }

    //pour récupérer les données des catégories du carnet de bord (instancie les objets et récupère les table de données
    //correspondantes
    public void getBabyCategorie(String date) {
        this.mainFactCategorie = new MainFactCategorie(date, this);
        this.mainFactCategorie.setSQLContainer();

    }

    //Pour changer la date dans les objets représentant les catégories
    public void changeDateBabyCategorie(String date) {
        mainFactCategorie.setDate(date);
    }        
}
