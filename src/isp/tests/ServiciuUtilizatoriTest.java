package isp.tests;

import isp.collections.services.ServiciuUtilizatori;
import isp.entity.Utilizator;
import isp.exception.RegistrationException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ServiciuUtilizatoriTest {

    @Test
    public void testInregistrareUtilizatoriSucces() throws RegistrationException {
        String nume = String.valueOf(Math.random() * 100000);
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);
        Utilizator rezultat = ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);

        assertEquals(utilizator.afisare(), rezultat.afisare());
    }

    private ArrayList<String[]> inregistrareEsuataDataProvider() {
        ArrayList<String[]> provider = new ArrayList<>();
        provider.add(new String[] {"Ion", "asda", "asd", "poli", "cnp"});           // numele exista
        provider.add(new String[] {"Gigel", "bl", "asd", "poli", "cnp"});           // parola prea usoara
        provider.add(new String[] {"Florin", "asda", "asd", "poli", "cnp"});           // cnp invalid

        return provider;
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareContExistent() throws RegistrationException {
        String[] data = inregistrareEsuataDataProvider().get(0);
        String nume = data[0], parola = data[1], email = data[2], universitate = data[3], cnp = data[4];

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);
        Utilizator rezultat = ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareParolaPreaUsoara() throws RegistrationException {
        String[] data = inregistrareEsuataDataProvider().get(1);
        String nume = data[0], parola = data[1], email = data[2], universitate = data[3], cnp = data[4];

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);
        Utilizator rezultat = ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareCNPInvalid() throws RegistrationException {
        String[] data = inregistrareEsuataDataProvider().get(2);
        String nume = data[0], parola = data[1], email = data[2], universitate = data[3], cnp = data[4];

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);
        Utilizator rezultat = ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);
    }

    @Test
    public void testAutentificareReusita() throws RegistrationException {
        String nume = String.valueOf(Math.random() * 100000);
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);
        assertTrue(ServiciuUtilizatori.getInstance().autentificareUtilizator(nume, parola));
    }

    @Test
    public void testAutentificareEsuataNume() throws RegistrationException {
        String nume = String.valueOf(Math.random() * 100000);
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);
        assertFalse(ServiciuUtilizatori.getInstance().autentificareUtilizator("ASD", parola));
    }

    @Test
    public void testAutentificareEsuataParola() throws RegistrationException {
        String nume = String.valueOf(Math.random() * 100000);
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        ServiciuUtilizatori.getInstance().inregistrareUtilizator(nume, parola, email, universitate, cnp);
        assertFalse(ServiciuUtilizatori.getInstance().autentificareUtilizator(nume, "asd"));
    }
}
