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
public class SanteCategorie extends CategorieBabyboard {
    
        public SanteCategorie(String date, Baby baby) {

        tableName = "sante";
        this.date = date;
        this.baby = baby;
    }
}
