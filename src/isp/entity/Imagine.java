package isp.entity;

import java.io.Serializable;

public class Imagine implements Serializable {
    private static int CURRENT_ID = 1;
    private int id;
    private String sursa;
    private Utilizator utilizator;
    private TipDefectiune tipDefectiune;
    private String descriere;
    private SituatieDefect stareCurenta;

    public Imagine(String sursa, Utilizator utilizator, TipDefectiune tipDefectiune, String descriere) {
        this.id = CURRENT_ID;
        this.sursa = sursa;
        this.utilizator = utilizator;
        this.tipDefectiune = tipDefectiune;
        this.descriere = descriere;
        this.stareCurenta = SituatieDefect.deschis;
        Imagine.CURRENT_ID++;
    }

    public int getId() {
        return id;
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
        switch (stareCurenta) {
            case deschis:
                if (stareNoua.equals(SituatieDefect.amanat) || stareNoua.equals(SituatieDefect.inCursDeRezolvare))
                    stareCurenta = stareNoua;
                break;
            case amanat:
                if (stareNoua.equals(SituatieDefect.deschis))
                    stareCurenta = stareNoua;
                break;
            case inCursDeRezolvare:
                if (stareNoua.equals(SituatieDefect.rezolvat))
                    stareCurenta = stareNoua;
                break;
            case rezolvat:
                System.out.println("Acest caz a fost rezolvat.");
                break;
        }
    }

    public String toString() {
        return "\n Imagine #" + id + "\n" + sursa + " :: " + stareCurenta
                + "\n" + "Adaugat de: " + utilizator.getNume()
                + "\n" + "Tip defectiune: " + tipDefectiune
                + "\n" + "Descriere: " + descriere + "\n";
    }
}
