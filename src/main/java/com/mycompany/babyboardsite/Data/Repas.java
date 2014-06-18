
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
public class Repas {
    int idRepas;
    int idBbaby;
    String typeRepas;
    int heure;
    int minute;
    String contenu;

    int note;

    public Repas(int idRepas, int idBaby, String typeRepas, int heure, int minute, String contenu, int note) {
        this.idRepas = idRepas;
        this.idBbaby = idBaby;
        this.typeRepas = typeRepas;
        this.heure = heure;
        this.minute = minute;
        this.contenu = contenu;
        this.note = note;

    }
    
    public String getType(){
        return typeRepas;
    }
    public int getNote(){
        return note;
    }
    public String getTime(){
        return ""+heure+"h"+minute;
    }
    public String getContenu(){
        return contenu;
    }

}
