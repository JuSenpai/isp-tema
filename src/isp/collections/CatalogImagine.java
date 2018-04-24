package isp.collections;

import isp.entity.Imagine;
import isp.entity.SituatieDefect;
import isp.entity.TipDefectiune;
import isp.entity.Utilizator;

import java.util.ArrayList;
import java.util.List;

public class CatalogImagine {
    private static CatalogImagine instance = null;
    private ArrayList<Imagine> catalog = new ArrayList<>();

    private CatalogImagine() {}
    public static CatalogImagine getInstance() {
        if (instance == null) {
            instance = new CatalogImagine();
        }

        return instance;
    }

    public void adaugaImagine(Imagine imagine) {

    }

    public void stergeImagine(Imagine imagine) {

    }

    public void modificaImagine(Imagine imagine) {

    }

    public List cautareDupaUtilizator(Utilizator utilizator) {
        return null;
    }

    public List cautareDupaTipDefectiune(TipDefectiune tipDefectiune) {
        return null;
    }

    public List cautareDupaStareCurenta(SituatieDefect stareCurenta) {
        return null;
    }
}
