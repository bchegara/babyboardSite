package com.mycompany.babyboardsite.Data;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.sql.SQLException;
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
    private int idParent;
    private String postIt;
    //objet contenant les item pour les catégories du babyboard
    public MainFactCategorie mainFactCategorie;
    public ActivitieCategorie activitieCategorie;
    public SiesteCategorie siesteCategorie;
    public NumeroUtileCategorie numeroUtileCategorie;
    public RepasCategorie repasCategorie;

    Oracle oracle = new Oracle();
    private SQLContainer babyTable;

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

    public String getFirstname() {
        return firstname;
    }

    public int getIdParent() {
        return idParent;
    }

    public String getPostIt() {
        return postIt;
    }

    //Constructeur pour récupérer un bébé avec son idBaby
    public Baby(int idB) {

        babyTable = oracle.queryTable("babies");
        babyTable.addContainerFilter(new Compare.Equal("idBaby", idB));// WHERE idBaby=idBaby
        idBaby = idB;
        Item infoUser = babyTable.getItem(new RowId(new Object[]{idBaby}));
        name = infoUser.getItemProperty("name").getValue().toString();
        old = infoUser.getItemProperty("age").getValue().toString();
        sex = Integer.parseInt(infoUser.getItemProperty("sex").getValue().toString());
        firstname = infoUser.getItemProperty("firstName").getValue().toString();
        idParent = Integer.parseInt(infoUser.getItemProperty("idParent").getValue().toString());
        postIt = infoUser.getItemProperty("postit").getValue().toString();

    }

    public Baby(String name, String age, int sex, String firstname, int idParent, String postit) {
        this.name = name;
        this.old = age;
        this.sex = sex;
        this.firstname = firstname;
        this.idParent = idParent;
        this.postIt = postit;
    }

    public Baby() {
        this.idBaby = idBaby;
        this.name = name;
        this.old = old;
        this.sex = sex;
        this.firstname = firstname;
        this.idParent = idParent;
        this.postIt = postIt;
    }

//TEST: pour afficher quelques infos du bébé
    public Component printBabyInfo() {
        Label infoB = new Label("name " + name + " firstname: " + firstname);
        return infoB;
    }

    public Component getPresentation() {
        Label presentationL = new Label("Enfant: " + name + " " + firstname);
        return presentationL;
    }

    //pour récupérer les données des catégories du carnet de bord (instancie les objets et récupère les table de données
    //correspondantes
    public void getBabyCategorie(String date) {
        this.mainFactCategorie = new MainFactCategorie(date, this);
        this.mainFactCategorie.setSQLContainer();

        this.activitieCategorie = new ActivitieCategorie(date, this);
        this.activitieCategorie.setSQLContainer();

        this.siesteCategorie = new SiesteCategorie(date, this);
        this.siesteCategorie.setSQLContainer();

        this.numeroUtileCategorie = new NumeroUtileCategorie(this);
        this.numeroUtileCategorie.setSQLContainer();

        this.repasCategorie = new RepasCategorie(date, this);
        this.repasCategorie.setSQLContainer();

    }

    //Pour changer la date dans les objets représentant les catégories
    public void changeDateBabyCategorie(String date) {
        mainFactCategorie.setDate(date);
        activitieCategorie.setDate(date);
        siesteCategorie.setDate(date);
        repasCategorie.setDate(date);
    }

    public void addBaby() {
        oracle = new Oracle();
        babyTable = oracle.queryTable("babies");
        babyTable.removeAllContainerFilters();
        try {
            Item rowItem = babyTable.getItem(babyTable.addItem());

            rowItem.getItemProperty("name").setValue(name);
            rowItem.getItemProperty("age").setValue(old);
            rowItem.getItemProperty("sex").setValue(sex);
            rowItem.getItemProperty("firstName").setValue(name);
            rowItem.getItemProperty("idParent").setValue(idParent);
            rowItem.getItemProperty("postit").setValue("nouveau post it");

            babyTable.commit();
            idBaby = Integer.parseInt(babyTable.lastItemId().toString());
        } catch (UnsupportedOperationException e) {
            System.out.println("erreur ajout enfant 1");
        } catch (Property.ReadOnlyException e) {
            System.out.println("erreur ajout enfant 2");
        } catch (SQLException e) {
            System.out.println("erreur ajout enfant 3");
            System.out.println(e.getMessage());
        }
    }

    public void addNurse(int idNurse, int idBaby) {
        oracle = new Oracle();

        SQLContainer jonctionTable = oracle.queryTable("jonction");

        try {
            Item rowItem = jonctionTable.getItem(jonctionTable.addItem());
            rowItem.getItemProperty("idUser").setValue(idNurse);
            rowItem.getItemProperty("idBaby").setValue(idBaby);
            jonctionTable.commit();
        } catch (UnsupportedOperationException e) {
            System.out.println("erreur ajout enfant 1");
        } catch (Property.ReadOnlyException e) {
            System.out.println("erreur ajout enfant 2");
        } catch (SQLException e) {
            System.out.println("erreur ajout enfant 3");
            System.out.println(e.getMessage());
        }
    }

    public List<String> getUserAssociated() {
        Oracle oracle2 = new Oracle();
        List<String> userList = new ArrayList<String>();
        SQLContainer userTable = oracle2.queryTable("users");
        SQLContainer jonctionTable = oracle.queryTable("jonction");
        Collection BabyIds = new ArrayList<Object>();

        try {
            jonctionTable.addContainerFilter(
                    new Compare.Equal("idBaby", idBaby));// WHERE name=emailToTest AND password=passwordToTest
            BabyIds = jonctionTable.getItemIds();
            for (Object item : BabyIds) {
                int i = Integer.parseInt(item.toString());
                Item infoJonctionTable = jonctionTable.getItem(new RowId(new Object[]{i}));
                int userId = Integer.parseInt(infoJonctionTable.getItemProperty("idUser").getValue().toString());

                userTable.removeAllContainerFilters();
                userTable.addContainerFilter(
                        new Compare.Equal("idUser", userId));// WHERE name=emailToTest AND password=passwordToTest
                userId = Integer.parseInt(userTable.firstItemId().toString());
                Item infoUser = userTable.getItem(new RowId(new Object[]{userId}));
                userList.add("Statut: " + infoUser.getItemProperty("rightLevel").getValue().toString() + " utilisteur: " + infoUser.getItemProperty("name").getValue().toString() + " " + infoUser.getItemProperty("firstName").getValue().toString());
            }

        } catch (Exception e) {
            System.out.println("erreur récupération utilisateur associé");
        }

        return userList;
    }
    
    public void updatePostit(String s){
        babyTable = oracle.queryTable("babies");
        babyTable.addContainerFilter(new Compare.Equal("idBaby", this.idBaby));// WHERE idBaby=idBaby
        Item infoUser = babyTable.getItem(new RowId(new Object[]{this.idBaby}));
        
        name = infoUser.getItemProperty("name").getValue().toString();
        old = infoUser.getItemProperty("age").getValue().toString();
        sex = Integer.parseInt(infoUser.getItemProperty("sex").getValue().toString());
        firstname = infoUser.getItemProperty("firstName").getValue().toString();
        idParent = Integer.parseInt(infoUser.getItemProperty("idParent").getValue().toString());
        postIt = infoUser.getItemProperty("postit").getValue().toString();
        
        
    }
}
