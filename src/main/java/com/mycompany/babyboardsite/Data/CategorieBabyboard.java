/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.Data;

import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author baptman
 */
public abstract class CategorieBabyboard {

    SQLContainer categorieTable;
    final Oracle oracle = new Oracle();
    Baby baby;
    public String date;
    public String tableName;

    public void setSQLContainer() {
        categorieTable = oracle.queryTable(tableName);
        categorieTable.addContainerFilter((new Compare.Equal("idBaby", baby.getId())));
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String returnCurrentDate() {
        return date;
    }

    public void removeItem(int i) {
        categorieTable.removeAllContainerFilters();
        categorieTable.removeItem(new RowId(new Object[]{i}));
        try {
            categorieTable.commit();
            System.out.println("GOOOD");
        } catch (UnsupportedOperationException ex) {
            Logger.getLogger(MainFactCategorie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainFactCategorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
