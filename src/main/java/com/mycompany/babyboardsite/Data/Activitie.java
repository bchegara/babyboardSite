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
public class Activitie {

    private int idActivitie;
    private int idBaby;
    private String date;
    private String title;
    private int duree;
    private int note;
    private int minute;
    private int heure;

    Activitie(int id, int idBaby, String date, String title, int duree, int note, int h, int min) {
        idActivitie = id;
        this.idBaby = idBaby;
        this.date = date;
        this.title = title;
        this.duree = duree;
        this.note = note;
        minute = min;
        heure = h;
    }

    public String getTime() {
        return "" + heure + "h" + minute;
    }

    public int getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }
    
    public int getDuree(){
        return duree;
    }
    public int getId(){
        return idActivitie;
    }
}
