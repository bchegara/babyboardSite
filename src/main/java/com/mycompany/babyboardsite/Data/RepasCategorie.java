/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.Data;

/**
 *
 * @author baptman
 */
import com.vaadin.data.Item;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;


public class RepasCategorie extends CategorieBabyboard {

    List<Repas> listRepas;
    public RepasCategorie(String date, Baby baby) {

        tableName = "repas";
        this.date = date;
        this.baby = baby;
    }

    public List<Repas> returnListCategorie() {
        Collection factsIds;
        listRepas = new ArrayList<Repas>();

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
                Repas repas = new Repas(Integer.parseInt(infoJonctionTable.getItemProperty("idRepas").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()),
                        infoJonctionTable.getItemProperty("type").getValue().toString(),
                        Integer.parseInt(infoJonctionTable.getItemProperty("heure").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("minute").getValue().toString()),
                        infoJonctionTable.getItemProperty("contenu").getValue().toString(),
                        Integer.parseInt(infoJonctionTable.getItemProperty("note").getValue().toString()));
                        
                listRepas.add(repas);
            }
        } catch (Exception e) {
            System.out.println("erreur retour liste repas");
        }
        return listRepas;
    }
    
     public void addRepas(String type, int hour, int minute, String contenu, int note) {
        categorieTable = oracle.queryTable("repas");
        Collection factsIds = new ArrayList<Object>();
        List<Repas> listRepas = new ArrayList<Repas>();

        try {
            Item rowItem = categorieTable.getItem(categorieTable.addItem());
            rowItem.getItemProperty("idBaby").setValue(baby.getId());
            rowItem.getItemProperty("type").setValue(type);
            rowItem.getItemProperty("heure").setValue(hour);
            rowItem.getItemProperty("minute").setValue(minute);
            rowItem.getItemProperty("contenu").setValue(contenu);
            rowItem.getItemProperty("note").setValue(note);// On récupère la dernière ligne de la table parent
            rowItem.getItemProperty("date").setValue(date);
            categorieTable.commit();
        } catch (Exception e) {
            System.out.println("erreur ajout de repas");
        }
    }

}
