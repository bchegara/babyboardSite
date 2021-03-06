/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.Data;

import com.vaadin.data.Item;
import com.vaadin.data.util.filter.And;
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
public class ActivitieCategorie extends CategorieBabyboard {

    List<Activitie> listActivitie;

    public ActivitieCategorie(String date, Baby baby) {

        tableName = "activities";
        this.date = date;
        this.baby = baby;
    }

    public List<Activitie> returnListCategorie() {
        Collection factsIds;
        listActivitie = new ArrayList<Activitie>();

        try {
            categorieTable.removeAllContainerFilters();
            SQLContainer catgorieTableSortedByDate = categorieTable;
            catgorieTableSortedByDate.addContainerFilter(
                    new And(new Compare.Equal("date", date),
                            new Compare.Equal("idBaby", baby.getId())));

            factsIds = catgorieTableSortedByDate.getItemIds();
            for (Object item : factsIds) {

                int i = Integer.parseInt(item.toString());
                Item infoJonctionTable = catgorieTableSortedByDate.getItem(new RowId(new Object[]{i}));
                Activitie activitie = new Activitie(Integer.parseInt(infoJonctionTable.getItemProperty("idActivitie").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()),
                        infoJonctionTable.getItemProperty("date").getValue().toString(),
                        infoJonctionTable.getItemProperty("kind").getValue().toString(),
                        Integer.parseInt(infoJonctionTable.getItemProperty("duree").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("note").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("hour").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("minute").getValue().toString()));

                listActivitie.add(activitie);
            }
        } catch (Exception e) {
            System.out.println("erreur retour liste activities");
        }
        return listActivitie;
    }

    public void addActivitie(String kind, int duree, int note, int hour, int minute) {
        categorieTable = oracle.queryTable("activities");
        Collection factsIds = new ArrayList<Object>();
        List<Activitie> listActivitie = new ArrayList<Activitie>();

        try {
            Item rowItem = categorieTable.getItem(categorieTable.addItem());
            rowItem.getItemProperty("idBaby").setValue(baby.getId());
            rowItem.getItemProperty("date").setValue(date);
            rowItem.getItemProperty("kind").setValue(kind);
            rowItem.getItemProperty("duree").setValue(duree);
            rowItem.getItemProperty("note").setValue(note);
            rowItem.getItemProperty("hour").setValue(hour);
            rowItem.getItemProperty("minute").setValue(minute);// On récupère la dernière ligne de la table parent
            categorieTable.commit();
        } catch (Exception e) {
            System.out.println("erreur ajout fait marquants");
        }
    }

   
}

 
