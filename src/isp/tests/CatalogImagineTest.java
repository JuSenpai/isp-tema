package isp.tests;

import isp.Aplicatie;
import isp.collections.CatalogImagine;
import isp.entity.Imagine;
import isp.entity.SituatieDefect;
import isp.entity.TipDefectiune;
import isp.entity.Utilizator;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatalogImagineTest {

    @Test
    public void testCautareDupaUtilizator() {
        Utilizator utilizator = Aplicatie.GLOBAL_USER;
        Imagine imagine = new Imagine(
                "asd",
                Aplicatie.GLOBAL_USER,
                TipDefectiune.altele,
                "test test"
        );

        CatalogImagine.getInstance().adaugaImagine(imagine);
        assertNotNull(CatalogImagine.getInstance().cautareDupaUtilizator(utilizator)
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

        CatalogImagine.getInstance().adaugaImagine(imagine);
        assertNotNull(CatalogImagine.getInstance().cautareDupaTipDefectiune(tipDefectiune)
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

        CatalogImagine.getInstance().adaugaImagine(imagine);
        assertNotNull(CatalogImagine.getInstance().cautareDupaStareCurenta(stareCurenta)
                .stream()
                .filter(imagine::equals)
                .findFirst()
        );
    }
}
