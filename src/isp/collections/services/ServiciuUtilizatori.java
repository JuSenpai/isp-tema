package isp.collections.services;

import isp.collections.CatalogUtilizatori;
import isp.entity.Utilizator;

public class ServiciuUtilizatori {
    private static ServiciuUtilizatori instance = null;
    private CatalogUtilizatori catalog;

    private ServiciuUtilizatori() {}
    public static ServiciuUtilizatori getInstance() {
        if (instance == null) {
            instance = new ServiciuUtilizatori();
        }

        return instance;
    }

    public Utilizator inregistrareUtilizator(String nume, String parola, String email, String universitate, String cnp) {
        return null;
    }

    public boolean autentificareUtilizator(String nume, String parola) {
        return true;
    }
}
