package isp.tests;

import isp.collections.CatalogUtilizatori;
import isp.collections.services.ServiciuUtilizatori;
import isp.entity.Utilizator;
import isp.exception.RegistrationException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiciuUtilizatoriTest {

    private ServiciuUtilizatori systemUnderTest;
    private CatalogUtilizatori catalogUtilizatoriMock;

    public ServiciuUtilizatoriTest() {
        catalogUtilizatoriMock = mock(CatalogUtilizatori.class);
        systemUnderTest = new ServiciuUtilizatori(catalogUtilizatoriMock);
    }

    @Test
    public void testInregistrareUtilizatoriSucces() throws RegistrationException {
        String nume = "username";
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        Utilizator utilizator = new Utilizator(nume, parola, email, universitate, cnp);

        when(catalogUtilizatoriMock.cautaUtilizator(nume)).thenReturn(null);
        Utilizator rezultat = systemUnderTest.inregistrareUtilizator(nume, parola, email, universitate, cnp);

        assertEquals(utilizator.afisare(), rezultat.afisare());
    }

    private ArrayList<String[]> inregistrareEsuataDataProvider() {
        ArrayList<String[]> provider = new ArrayList<>();
        provider.add(new String[] {"Ion", "asda", "asd", "poli", "cnp"});           // numele exista
        provider.add(new String[] {"Gigel", "bl", "asd", "poli", "cnp"});           // parola prea usoara
        provider.add(new String[] {"Florin", "asda", "asd@asd.com", "poli", "cnp"});           // cnp invalid
        provider.add(new String[] {"Bucur", "asdf", "asd", "poli", "cnp"});         // email invalid

        return provider;
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareContExistent() throws RegistrationException {
        testInregistrareEsuata(0);
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareParolaPreaUsoara() throws RegistrationException {
        testInregistrareEsuata(1);
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareCNPInvalid() throws RegistrationException {
        testInregistrareEsuata(2);
    }

    @Test(expected = RegistrationException.class)
    public void testInregistrareEmailInvalid() throws RegistrationException {
        testInregistrareEsuata(3);
    }

    private void testInregistrareEsuata(int i) throws RegistrationException {
        String[] data = inregistrareEsuataDataProvider().get(i);
        String nume = data[0], parola = data[1], email = data[2], universitate = data[3], cnp = data[4];

        systemUnderTest.inregistrareUtilizator(nume, parola, email, universitate, cnp);
    }

    @Test
    public void testAutentificareReusita() throws RegistrationException {
        String nume = "username";
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        when(catalogUtilizatoriMock.cautaUtilizator(nume))
                .thenReturn(new Utilizator(nume, parola, email, universitate, cnp));

        assertTrue(systemUnderTest.autentificareUtilizator(nume, parola));
    }

    @Test
    public void testAutentificareEsuataNume() throws RegistrationException {
        testAutentificare("asdf", "asdf");
    }

    @Test
    public void testAutentificareEsuataParola() throws RegistrationException {
        testAutentificare(null, "asd");
    }

    private void testAutentificare(String username, String password) throws RegistrationException {
        String nume = "username";
        String parola = "asdf";
        String email = "cosmin.stoica97@gmail.com";
        String universitate = "poli";
        String cnp = "1970505471333";

        when(catalogUtilizatoriMock.cautaUtilizator(nume))
                .thenReturn(new Utilizator(nume, parola, email, universitate, cnp));

        assertFalse(systemUnderTest.autentificareUtilizator(username == null ? nume : username, password));
    }
}
