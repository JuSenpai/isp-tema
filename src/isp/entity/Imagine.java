package isp.entity;

import java.io.Serializable;

public class Imagine implements Serializable {
    private String sursa;
    private Utilizator utilizator;
    private TipDefectiune tipDefectiune;
    private String descriere;
    private SituatieDefect stareCurenta;

    public Imagine(String sursa, Utilizator utilizator, TipDefectiune tipDefectiune, String descriere) {
        this.sursa = sursa;
        this.utilizator = utilizator;
        this.tipDefectiune = tipDefectiune;
        this.descriere = descriere;
        this.stareCurenta = SituatieDefect.deschis;
    }

    public String getSursa() {
        return sursa;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public TipDefectiune getTipDefectiune() {
        return tipDefectiune;
    }

    public String getDescriere() {
        return descriere;
    }

    public SituatieDefect getStareCurenta() {
        return stareCurenta;
    }

    public void update(SituatieDefect stareNoua) {
        
    }
}
