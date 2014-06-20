package com.mycompany.babyboardsite.Data;

import static com.mycompany.babyboardsite.Data.User.RightLevel.ADMIN;
import static com.mycompany.babyboardsite.Data.User.RightLevel.NURSE;
import static com.mycompany.babyboardsite.Data.User.RightLevel.USER;
import com.mycompany.babyboardsite.View.HeaderHome;
import com.mycompany.babyboardsite.View.HeaderView;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author baptman
 */
public class User {

    //Variable de l'utilsateur
    private int idUser;
    private String name;
    private String email;
    private String password;
    private String adress;
    private int zip;
    private String city;
    private int tel;
    private String firstname;
    public List<Baby> babyList;
    private SQLContainer userTable;
    private HeaderView header;

    final Oracle oracle = new Oracle();
//contruction des niveaux de droits de l'utilsateur

    public enum RightLevel {

        ADMIN,
        USER,
        NURSE,
        PARENT,
        NOUNOU,
        VISITOR;
    }
    //Variable contenant le droit de l'utilsateur
    private RightLevel rightLvl;

    public Component printUserInfo() {
        Label userInfo;
        //Si l'utilisateur est connecté on affiche ses données
        if (isConnected()) {
            userInfo = new Label("nom: " + name + " prénom: " + firstname + " id: " + idUser + " email: "
                    + email + " password: " + password + " adresse: " + adress + " zip: " + zip + " city: " + city
                    + " tel: " + tel + " rightLvl: " + rightLvl.toString() + " baby: " + babyList.size());
            return userInfo;
        } else {
            //Sinon message d'erreur
            userInfo = new Label("pas enregistré");
            return userInfo;
        }

    }

    public Component printErrorSubscribe(int num) {
        Label errorSubscribe;
        if (num == 1) {
            errorSubscribe = new Label("verifiez email");
        } else if (num == 2) {
            errorSubscribe = new Label("verifiez mot de passe");

        } else if (num == 3) {
            errorSubscribe = new Label("utilisateur déjà existant");
        } else if (num == 4) {
            errorSubscribe = new Label("contactez l'administrateur");
        } else if (num == 0) {
            errorSubscribe = new Label("remplissez tous les champs");
        } else {
            errorSubscribe = new Label("fatal error");

        }

        return errorSubscribe;

    }

    public Component printErrorLogin(int num) {
        Label errorLogin;
        if (num == 1) {
            errorLogin = new Label("Champs vides");
        } else if (num == 2) {
            errorLogin = new Label("Mot de passe et/ou nom d'utilisateur incorrects");

        } else {
            errorLogin = new Label("fatal error");

        }

        return errorLogin;

    }

    //TEST: Fonction test
    public void setUserInfos() {
        userTable = oracle.queryTable("users");

        userTable.addContainerFilter(
                new Equal("name", "Cheg"));// WHERE name="cheg"
        //On récupère l'idUser
        idUser = Integer.parseInt(userTable.firstItemId().toString());
        Item infoUser = userTable.getItem(new RowId(new Object[]{idUser}));

        name = infoUser.getItemProperty("name").getValue().toString();
        firstname = infoUser.getItemProperty("firstName").getValue().toString();
        email = infoUser.getItemProperty("email").getValue().toString();
        password = infoUser.getItemProperty("password").getValue().toString();
        adress = infoUser.getItemProperty("adress").getValue().toString();
        zip = Integer.parseInt(infoUser.getItemProperty("zip").getValue().toString());
        city = infoUser.getItemProperty("city").getValue().toString();
        tel = Integer.parseInt(infoUser.getItemProperty("tel").getValue().toString());
        rightLvl = RightLevel.ADMIN;

    }

    //Constructeur User avec le couple (mail, password) d'un user de la bdd
    public User(String emailToTest, String passwordToTest) {
        userTable = oracle.queryTable("users");
        //Ajout du container pour filtrer les résultats
        try {
            userTable.addContainerFilter(
                    new And(new Equal("email", emailToTest),
                            new Equal("password", passwordToTest)));// WHERE name=emailToTest AND password=passwordToTest
            //On récupère l'idUser
            idUser = Integer.parseInt(userTable.firstItemId().toString());
            Item infoUser = userTable.getItem(new RowId(new Object[]{idUser}));

            name = infoUser.getItemProperty("name").getValue().toString();
            firstname = infoUser.getItemProperty("firstName").getValue().toString();
            email = infoUser.getItemProperty("email").getValue().toString();
            password = infoUser.getItemProperty("password").getValue().toString();
            adress = infoUser.getItemProperty("adress").getValue().toString();
            zip = Integer.parseInt(infoUser.getItemProperty("zip").getValue().toString());
            city = infoUser.getItemProperty("city").getValue().toString();
            tel = Integer.parseInt(infoUser.getItemProperty("tel").getValue().toString());
            rightLvl = setRightLevel(infoUser.getItemProperty("rightLevel").getValue().toString());

            babyList = new ArrayList<Baby>();
            babyList = getBabyByUser();
            header = new HeaderView();

        } catch (Exception e) {
            System.out.println("e");
        }
    }

    public Boolean checkEmailPassword(String emailToTest, String passwordToTest) {
        try {
            userTable = oracle.queryTable("users");
            //Ajout du container pour filtrer les résultats
            userTable.addContainerFilter(
                    new And(new Equal("email", emailToTest),
                            new Equal("password", passwordToTest)));// WHERE name=emailToTest AND password=passwordToTest

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean checkEmail(String emailToTest) {
        try {
            userTable = oracle.queryTable("users");
            //Ajout du container pour filtrer les résultats
            userTable.addContainerFilter(
                    new And(new Equal("email", emailToTest)));// WHERE name=emailToTest AND password=passwordToTest

            return true;
        } catch (Exception e) {
            System.out.println("test");
            return false;
        }
    }

    public Boolean checkNotEmpty(String first, String last, String email, String password) {
        if (!"".equals(first) && !"".equals(last) && !"".equals(email) && password != "") {
            return true;
        } else {
            return false;
        }
    }

    public void addUser(String first, String last, String email, String password, String rightLevel, String tel, String adresse, String city, int zip) {
        SQLContainer userContainer;

        userContainer = oracle.queryTable("users");

        try {
            Item rowItem = userContainer.getItem(userContainer.addItem());
            rowItem.getItemProperty("name").setValue(last);
            rowItem.getItemProperty("email").setValue(email);
            rowItem.getItemProperty("password").setValue(password);
            rowItem.getItemProperty("adress").setValue(adresse);
            rowItem.getItemProperty("zip").setValue(zip);
            rowItem.getItemProperty("city").setValue(city);
            rowItem.getItemProperty("tel").setValue(tel);
            rowItem.getItemProperty("firstName").setValue(first);
            if (rightLevel == "Parent") {
                rowItem.getItemProperty("rightLevel").setValue("USER");
            } else if (rightLevel == "Nounou") {
                rowItem.getItemProperty("rightLevel").setValue("NURSE");

            } else {
                // par defaut parent mais ce cas ne devrait jamais se produire
                rowItem.getItemProperty("rightLevel").setValue("USER");

            }
            userContainer.commit();
            System.err.println("commit réussi");
        } catch (UnsupportedOperationException e) {
            System.out.println("erreur ajout nouvel user");
        } catch (Property.ReadOnlyException e) {
            System.out.println("erreur ajout nouvel user");
        } catch (SQLException e) {
            System.out.println("erreur ajout nouvel user");
        }
    }

    //Constructeur par défaut
    public User() {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.adress = adress;
        this.zip = zip;
        this.city = city;
        this.tel = tel;
        this.firstname = firstname;
        this.rightLvl = RightLevel.VISITOR;
        babyList = new ArrayList<Baby>();
    }

    //Fonction permettant de définir le "profil" de l'utilsateur
    private RightLevel setRightLevel(String s) {
        if (s.equals("ADMIN")) {
            return RightLevel.ADMIN;
        } else if (s.equals("USER")) {
            return RightLevel.USER;
        } else if (s.equals("NURSE")) {
            return RightLevel.NURSE;
        } else {
            return RightLevel.VISITOR;
        }

    }

    public RightLevel getRightLevel() {
        return rightLvl;
    }

    //Fonction pour savoir si un utilisateur est connecté
    public Boolean isConnected() {
        if (rightLvl == RightLevel.ADMIN || rightLvl == RightLevel.USER || rightLvl == RightLevel.NURSE) {
            return true;
        }
        return false;
    }

    //fonction qui renvoi la liste des enfant d'un utilisateurs
    public List<Baby> getBabyByUser() {

        SQLContainer jonctionTable = oracle.queryTable("jonction");
        Collection BabyIds = new ArrayList<Object>();
        List<Baby> listBaby = new ArrayList<Baby>();

        try {
            jonctionTable.addContainerFilter(
                    new Equal("idUser", idUser));// WHERE name=emailToTest AND password=passwordToTest
            BabyIds = jonctionTable.getItemIds();
            for (Object item : BabyIds) {

                int i = Integer.parseInt(item.toString());
                Item infoJonctionTable = jonctionTable.getItem(new RowId(new Object[]{i}));
                Baby babyCurrent = new Baby(Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()));
                listBaby.add(babyCurrent);
            }
        } catch (Exception e) {
            System.out.println("e");
        }
        return listBaby;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return idUser;
    }

    public Boolean isUser() {
        return rightLvl == USER;
    }

    public Boolean isNurse() {
        return rightLvl == NURSE;
    }

    public Boolean isAdmin() {
        return rightLvl == ADMIN;
    }

    public SQLContainer getSQLContainerNurse() {
        userTable = oracle.queryTable("users");
        //Ajout du container pour filtrer les résultats
        try {
            userTable.addContainerFilter(
                    new Equal("rightLevel", "NURSE"));// WHERE name=emailToTest AND password=passwordToTest

        } catch (Exception e) {
            System.out.println("e");
        }
        return userTable;
    }

    public VerticalLayout welcomLayout() {
        VerticalLayout welcomeL = new VerticalLayout();
        welcomeL.addComponent(new Label("Bienvenue " + firstname + " " + name + "!"));
        welcomeL.addComponent(new Label("Compte de type: " + this.getRightLevel()));
        welcomeL.addComponent(new Label("Vous avez " + babyList.size() + " enfants asscoié(s) à votre compte"));
        return welcomeL;
    }

    public CssLayout getHeader() {
        if (isConnected()) {
            return header.getLayout();
        }
        HeaderHome header2 = new HeaderHome();

        return header2.getLayout();
    }

    public void refreshUser() {
        User user = new User(this.email, this.password);
        this.babyList = user.babyList;
    }

}
