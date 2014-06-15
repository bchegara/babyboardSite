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
public class MainFactCategorie extends CategorieBabyboard {

    List<MainFact> listMainFact;
    

    public MainFactCategorie(String date, Baby baby) {
        
        tableName = "mainfacts";
        this.date = date;
        this.baby = baby;
    }

    public List<MainFact> returnListCategorie() {
        Collection factsIds;
        listMainFact = new ArrayList<MainFact>();

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
                MainFact mainFact = new MainFact(Integer.parseInt(infoJonctionTable.getItemProperty("idFact").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()),
                        infoJonctionTable.getItemProperty("title").getValue().toString(),
                        infoJonctionTable.getItemProperty("description").getValue().toString(),
                        infoJonctionTable.getItemProperty("date").getValue().toString(),
                        infoJonctionTable.getItemProperty("hours").getValue().toString());
                listMainFact.add(mainFact);
            }
        } catch (Exception e) {
            System.out.println("erreur retour liste");
        }
        return listMainFact;
    }

    public void addMainFact(String titleMF, String descriptionMF, int hour, int minute) {
        categorieTable = oracle.queryTable("mainfacts");
        Collection factsIds = new ArrayList<Object>();
        List<MainFact> listMFact = new ArrayList<MainFact>();

        try {
            Item rowItem = categorieTable.getItem(categorieTable.addItem());
            rowItem.getItemProperty("idBaby").setValue(baby.getId());
            rowItem.getItemProperty("title").setValue(titleMF);
            rowItem.getItemProperty("description").setValue(descriptionMF);
            rowItem.getItemProperty("date").setValue(date);
            rowItem.getItemProperty("hours").setValue(hour+":"+minute+":00");// On récupère la dernière ligne de la table parent
//            rowItem.getItemProperty("hours").setValue("00:00:00");
            categorieTable.commit();
        } catch (Exception e) {
            System.out.println("e");
        }
    }
    
}
