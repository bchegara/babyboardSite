/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.babyboardsite.Data;

import java.util.Date;

/**
 *
 * @author baptman
 */
public class Sante {

    String maladie;
    String medicament;
    String dose;
    Boolean matin;
    Boolean midi;
    Boolean soir;
    Date debut;
    Date fin;
    String commentaire;

    public Sante(String maladie, String medicament, String dose, Boolean matin, Boolean midi, Boolean soir, Date debut,
            Date fin, String commentaire) {
        this.maladie = maladie;
        this.medicament = medicament;
        this.dose = dose;
        this.matin = matin;
        this.midi = midi;
        this.soir = soir;
        this.debut = debut;
        this.fin = fin;
        this.commentaire = commentaire;
    }

}
