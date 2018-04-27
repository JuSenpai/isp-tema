package isp.collections;

import isp.collections.services.ServiciuSerializare;
import isp.entity.Imagine;
import isp.entity.SituatieDefect;
import isp.entity.TipDefectiune;
import isp.entity.Utilizator;

import java.io.IOException;
import java.util.ArrayList;

public class CatalogImagine {
    private ServiciuSerializare serviciuSerializare;
    private ArrayList<Imagine> catalog = new ArrayList<>();

    public CatalogImagine(ServiciuSerializare serviciuSerializare) {
        this.serviciuSerializare = serviciuSerializare;
        try {
            catalog = serviciuSerializare.incarcareFisier(Imagine.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void adaugaImagine(Imagine imagine) {
        try {
            catalog.add(imagine);
            serviciuSerializare.serializare(catalog.toArray(new Imagine[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stergeImagine(int id) {
        try {
            catalog.removeIf(imagine1 -> id == imagine1.getId());
            serviciuSerializare.serializare(catalog.toArray(new Imagine[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateImagine(int id, SituatieDefect stareNoua) {
        try {
            catalog.stream()
                    .filter(imagine1 -> imagine1.getId() == id)
                    .findFirst()
                    .ifPresent(imagine1 -> imagine1.update(stareNoua));
            serviciuSerializare.serializare(catalog.toArray(new Imagine[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Imagine> getImagini() {
        return catalog;
    }

    public ArrayList<Imagine> cautareDupaUtilizator(Utilizator utilizator) {
        ArrayList<Imagine> imagini = new ArrayList<>();

        int n = catalog.size();
        for (int i = 0;i<n;i++) {
            Imagine imagine = catalog.get(i);
            if (imagine.getUtilizator().getNume().equals(utilizator.getNume())) {
                imagini.add(imagine);
            }
        }

        return imagini;
    }

    public ArrayList<Imagine> cautareDupaTipDefectiune(TipDefectiune tipDefectiune) {
        ArrayList<Imagine> imagini = new ArrayList<>();

        int n = catalog.size();
        for (int i = 0;i<n;i++) {
            Imagine imagine = catalog.get(i);
            if (imagine.getTipDefectiune().equals(tipDefectiune)) {
                imagini.add(imagine);
            }
        }

        return imagini;
    }

    public ArrayList<Imagine> cautareDupaStareCurenta(SituatieDefect stareCurenta) {
        ArrayList<Imagine> imagini = new ArrayList<>();

        int n = catalog.size();
        for (int i = 0;i<n;i++) {
            Imagine imagine = catalog.get(i);
            if (imagine.getStareCurenta().equals(stareCurenta)) {
                imagini.add(imagine);
            }
        }

        return imagini;
    }
}
