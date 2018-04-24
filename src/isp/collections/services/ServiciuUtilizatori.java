package isp.collections.services;

import isp.collections.CatalogUtilizatori;
import isp.entity.Utilizator;
import isp.exception.RegistrationException;

public class ServiciuUtilizatori {
    private static ServiciuUtilizatori instance = null;
    private CatalogUtilizatori catalog = CatalogUtilizatori.getInstance();

    private ServiciuUtilizatori() {}

    public static ServiciuUtilizatori getInstance() {
        if (instance == null) {
            instance = new ServiciuUtilizatori();
        }

        return instance;
    }

    public Utilizator inregistrareUtilizator(String nume, String parola, String email, String universitate, String cnp)
            throws RegistrationException {
        if (catalog.cautaUtilizator(nume) != null) {
            throw new RegistrationException("Acest utilizator există deja în sistem!");
        }

        if (!parola.matches("\\w{3,}")) {
            throw new RegistrationException("Parola nu este suficient de puternică");
        }

        if (cnp.length() != 13) {
            throw new RegistrationException("CNP-ul nu are 13 caractere");
        }

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);
        catalog.adaugaUtilizator(utilizator);

        return utilizator;
    }

    public boolean autentificareUtilizator(String nume, String parola) {
        Utilizator utilizator = catalog.cautaUtilizator(nume);
        if (utilizator == null) {
            return false;
        } else {
            return utilizator.getParola().equals(parola);
        }
    }
}
