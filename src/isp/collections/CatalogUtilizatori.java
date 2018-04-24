package isp.collections;

import isp.collections.services.ServiciuSerializare;
import isp.entity.Utilizator;

import java.io.IOException;
import java.util.ArrayList;

public class CatalogUtilizatori {
    private static CatalogUtilizatori instance = null;
    private ServiciuSerializare serviciuSerializare = ServiciuSerializare.getInstance();
    private ArrayList<Utilizator> catalog = new ArrayList<>();

    private CatalogUtilizatori() {
        try {
            catalog = serviciuSerializare.incarcareFisier(Utilizator.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CatalogUtilizatori getInstance() {
        if (instance == null) {
            instance = new CatalogUtilizatori();
        }

        return instance;
    }

    public void adaugaUtilizator(Utilizator utilizator) {
        try {
            catalog.add(utilizator);
            serviciuSerializare.serializare(catalog.toArray(new Utilizator[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Utilizator cautaUtilizator(String nume) {
        return catalog.stream()
                .filter(utilizator -> utilizator.getNume().equals(nume))
                .findFirst()
                .orElse(null);
    }
}
