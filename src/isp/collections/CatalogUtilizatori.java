package isp.collections;

import isp.collections.services.ServiciuSerializare;
import isp.entity.Utilizator;
import java.util.ArrayList;

public class CatalogUtilizatori {
    private static CatalogUtilizatori instance = null;
    private ServiciuSerializare serviciuSerializare = ServiciuSerializare.getInstance();
    private ArrayList<Utilizator> catalog = new ArrayList<>();

    private CatalogUtilizatori() {}

    public static CatalogUtilizatori getInstance() {
        if (instance == null) {
            instance = new CatalogUtilizatori();
        }

        return instance;
    }

    public void adaugaUtilizator(Utilizator utilizator) {

    }

    public Utilizator cautaUtilizator(String cnp) {
        return null;
    }
}
