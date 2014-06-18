/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.Data;

import com.vaadin.data.Item;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author baptman
 */
public class NumeroUtileCategorie extends CategorieBabyboard {

    List<String> idNumeroUtile;
    List<String> idBaby;
    List<String> role;
    List<String> nom;
    List<String> numero;
    List<String> adresse;

    public NumeroUtileCategorie(Baby baby) {

        tableName = "numeroutile";
        this.baby = baby;
    }

    public void createListCategorie() {
        Collection factsIds;
        role = new ArrayList<String>();
        nom = new ArrayList<String>();
        numero = new ArrayList<String>();
        adresse = new ArrayList<String>();
        idNumeroUtile = new ArrayList<String>();
        idBaby = new ArrayList<String>();

        try {
            categorieTable.removeAllContainerFilters();
            SQLContainer catgorieTableSortedByDate = categorieTable;
            catgorieTableSortedByDate.addContainerFilter(new Compare.Equal("idBaby", baby.getId()));

            factsIds = catgorieTableSortedByDate.getItemIds();
            for (Object item : factsIds) {

                int i = Integer.parseInt(item.toString());
                Item infoJonctionTable = catgorieTableSortedByDate.getItem(new RowId(new Object[]{i}));
                idNumeroUtile.add(infoJonctionTable.getItemProperty("idNumeroUtile").getValue().toString());
                idBaby.add(infoJonctionTable.getItemProperty("idBaby").getValue().toString());
                role.add(infoJonctionTable.getItemProperty("role").getValue().toString());
                nom.add(infoJonctionTable.getItemProperty("nom").getValue().toString());
                numero.add(infoJonctionTable.getItemProperty("numero").getValue().toString());
                adresse.add(infoJonctionTable.getItemProperty("adresse").getValue().toString());
            }
        } catch (Exception e) {
            System.out.println("erreur création liste pour numéro utile");
        }
    }
    
    @Override
        public void setSQLContainer() {
        categorieTable = oracle.queryTable(tableName);
        categorieTable.addContainerFilter((new Compare.Equal("idBaby", baby.getId())));
    }
        public SQLContainer getSQLContainer(){
            return categorieTable;
        }

    public List<String> getListRole() {
        return role;
    }

    public List<String> getListNom() {
        return nom;
    }

    public List<String> getListNuméro() {
        return numero;
    }

    public List<String> getListAdresse() {
        return adresse;
    }

    public int getSize() {
        return role.size();
    }

}
