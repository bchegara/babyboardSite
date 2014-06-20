package com.mycompany.babyboardsite.Data;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import java.sql.SQLException;

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
        idParent = Integer.parseInt(infoUser.getItemProperty("idParent").getValue().toString());
    }

    public Baby() {
    this.idBaby= idBaby;
    this.name = name;
    this.old = old;
    this.sex = sex;
    this.firstname = firstname;
    this.idParent = idParent;
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
    
         public void addNurse(int idNurse, int idBaby) {
        oracle = new Oracle();

         SQLContainer jonctionTable = oracle.queryTable("jonction");

        try {
            Item rowItem = jonctionTable.getItem(jonctionTable.addItem());
            rowItem.getItemProperty("idUser").setValue(idNurse);
            rowItem.getItemProperty("idBaby").setValue(idBaby);
            jonctionTable.commit();
        } catch (UnsupportedOperationException e) {
            System.out.println("erreur ajout enfantede");
        } catch (Property.ReadOnlyException e) {
            System.out.println("erreur ajout enfant000");
        } catch (SQLException e) {
            System.out.println("erreur ajout enfantLLL");
            System.out.println(e.getMessage());
        }
    }
}
