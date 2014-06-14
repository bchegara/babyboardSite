/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.Data;

import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.SQLContainer;

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
    public String returnCurrentDate(){
        return date;
    }

}
