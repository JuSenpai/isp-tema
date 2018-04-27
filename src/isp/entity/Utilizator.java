package isp.entity;

import isp.collections.CatalogImagine;
import isp.collections.services.ServiciuSerializare;

import java.io.Serializable;
import java.util.ArrayList;

public class Utilizator implements Serializable {
    private String nume;
    private String parola;
    private String email;
    private String universitate;
    private String cnp;

    public Utilizator(String nume, String parola, String email, String universitate, String cnp) {
        this.nume = nume;
        this.parola = parola;
        this.email = email;
        this.universitate = universitate;
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public String getParola() {
        return parola;
    }

    public String getEmail() {
        return email;
    }

    public String getUniversitate() {
        return universitate;
    }

    public String getCnp() {
        return cnp;
    }

    public String afisare() {
        return "Profil Utilizator:\n" +
                "Nume: " + nume + "\n" +
                "Email: " + email + "\n" +
                "CNP: " + cnp + "\n" +
                "Universitate: " + universitate + "\n";
    }

    public ArrayList<Imagine> getImagini() {
        return new CatalogImagine(
                new ServiciuSerializare()
        ).cautareDupaUtilizator(this);
    }
}
