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
public class Sieste {
int idSieste;
int idBaby;
int heure;
int minute;
int duree;
int note;
String date;

Sieste(int idSieste, int idBaby, int heure, int minute, int duree, int note, String date){
    this.idSieste =idSieste;
    this.idBaby = idBaby;
    this.heure = heure;
    this.minute = minute;
    this.duree = duree;
    this.note = note;
    this.date = date;
}

public int getHour(){
    return heure;
}

public int getMinute(){
    return minute;
}

public int getNote(){
    return note;
}

public int getDuree(){
    return duree;
}
public int getId(){
    return idSieste;
}

}
