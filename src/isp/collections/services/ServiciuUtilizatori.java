package isp.collections.services;

import isp.collections.CatalogUtilizatori;
import isp.entity.Utilizator;
import isp.entity.UtilizatorAnonim;
import isp.exception.RegistrationException;

public class ServiciuUtilizatori {
    private CatalogUtilizatori catalog;

    public ServiciuUtilizatori(CatalogUtilizatori catalogUtilizatori) {
        catalog = catalogUtilizatori;
    }

    public Utilizator inregistrareUtilizator(String nume, String parola, String email, String universitate, String cnp)
            throws RegistrationException {
        if (catalog.cautaUtilizator(nume) != null) {
            throw new RegistrationException("Acest utilizator există deja în sistem!");
        }

        if (!parola.matches("\\w{3,}")) {
            throw new RegistrationException("Parola nu este suficient de puternică");
        }

        if (!email.matches(".+@.+\\..+")) {
            throw new RegistrationException("Emailul nu este valid");
        }

        if (cnp.length() != 13) {
            throw new RegistrationException("CNP-ul nu are 13 caractere");
        }

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);
        catalog.adaugaUtilizator(utilizator);

        return utilizator;
    }

    public UtilizatorAnonim inregistrareAnonima(String nume) throws RegistrationException {
        if (catalog.cautaUtilizator(nume) != null) {
            throw new RegistrationException("Nu poti alege numele unui utilizator inregistrat!");
        }

        return new UtilizatorAnonim(nume);
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
