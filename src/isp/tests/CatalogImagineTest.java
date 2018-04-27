package isp.tests;

import isp.Aplicatie;
import isp.collections.CatalogImagine;
import isp.collections.services.ServiciuSerializare;
import isp.entity.Imagine;
import isp.entity.SituatieDefect;
import isp.entity.TipDefectiune;
import isp.entity.Utilizator;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CatalogImagineTest {

    private ServiciuSerializare serviciuSerializareMock;
    private CatalogImagine systemUnderTest;

    public CatalogImagineTest() {
        serviciuSerializareMock = mock(ServiciuSerializare.class);
        systemUnderTest = new CatalogImagine(serviciuSerializareMock);
    }

    @Test
    public void testCautareDupaUtilizator() {
        Utilizator utilizator = Aplicatie.GLOBAL_USER;
        Imagine imagine = new Imagine(
                "asd",
                Aplicatie.GLOBAL_USER,
                TipDefectiune.altele,
                "test test"
        );

        systemUnderTest.adaugaImagine(imagine);
        assertNotNull(systemUnderTest.cautareDupaUtilizator(utilizator)
                .stream()
                .filter(imagine::equals)
                .findFirst()
        );
    }

    @Test
    public void testCautareDupaTipDefectiune() {
        TipDefectiune tipDefectiune = TipDefectiune.altele;
        Imagine imagine = new Imagine(
                "asd",
                Aplicatie.GLOBAL_USER,
                TipDefectiune.altele,
                "test test"
        );

        systemUnderTest.adaugaImagine(imagine);
        assertNotNull(systemUnderTest.cautareDupaTipDefectiune(tipDefectiune)
                .stream()
                .filter(imagine::equals)
                .findFirst()
        );
    }

    @Test
    public void testCautareDupaSituatie() {
        SituatieDefect stareCurenta = SituatieDefect.deschis;
        Imagine imagine = new Imagine(
                "asd",
                Aplicatie.GLOBAL_USER,
                TipDefectiune.altele,
                "test test"
        );

        systemUnderTest.adaugaImagine(imagine);
        assertNotNull(systemUnderTest.cautareDupaStareCurenta(stareCurenta)
                .stream()
                .filter(imagine::equals)
                .findFirst()
        );
    }
}
