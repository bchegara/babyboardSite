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
public class SiesteCategorie extends CategorieBabyboard{
    List<Sieste> listActivitie;
    public SiesteCategorie(String date, Baby baby) {

        tableName = "siestes";
        this.date = date;
        this.baby = baby;
    }
    
    public List<Sieste> returnListCategorie() {
        Collection factsIds;
        listActivitie = new ArrayList<Sieste>();

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
                Sieste sieste = new Sieste(Integer.parseInt(infoJonctionTable.getItemProperty("idSieste").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("idBaby").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("heure").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("minute").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("duree").getValue().toString()),
                        Integer.parseInt(infoJonctionTable.getItemProperty("note").getValue().toString()),
                        infoJonctionTable.getItemProperty("date").getValue().toString());

                listActivitie.add(sieste);
            }
        } catch (Exception e) {
            System.out.println("erreur retour liste activities");
        }
        return listActivitie;
    }
    
     public void addMainFact(int heure, int minute, int duree, int note) {
        categorieTable = oracle.queryTable("siestes");
        Collection factsIds = new ArrayList<Object>();
        List<Sieste> listSieste = new ArrayList<Sieste>();

        try {
            Item rowItem = categorieTable.getItem(categorieTable.addItem());
            rowItem.getItemProperty("idBaby").setValue(baby.getId());
            rowItem.getItemProperty("heure").setValue(heure);
            rowItem.getItemProperty("minute").setValue(minute);
            rowItem.getItemProperty("duree").setValue(duree);
            rowItem.getItemProperty("note").setValue(note);
            rowItem.getItemProperty("date").setValue(date);
            categorieTable.commit();
        } catch (Exception e) {
            System.out.println("e");
        }
    }
}
