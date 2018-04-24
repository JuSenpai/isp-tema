package isp.collections.services;

import isp.entity.Imagine;
import isp.entity.Utilizator;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class ServiciuSerializare {
    private static ServiciuSerializare instance = null;

    public final String FISIER_UTILIZATORI = "utilizatori.dat";
    public final String FISIER_IMAGINI = "imagini.dat";

    private HashMap<String, String> fisiere = new HashMap<String, String>() {
    };

    private ServiciuSerializare() {
        fisiere.put(String.valueOf(Utilizator[].class), FISIER_UTILIZATORI);
        fisiere.put(String.valueOf(Imagine[].class), FISIER_IMAGINI);
    }

    public static ServiciuSerializare getInstance() {
        if (instance == null) {
            instance = new ServiciuSerializare();
        }

        return instance;
    }

    public void serializareObiect(Object object) throws IOException {
        String fisier = fisiere.get(object.getClass().toString());
        File file = new File(fisier);
        if (!file.exists()) {
            file.createNewFile();
        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fisier));
        out.writeObject(object);
        out.close();
    }

    public ArrayList incarcareFisier(Class clasa) throws IOException, ClassNotFoundException {
        Class toArray = Array.newInstance(clasa, 0).getClass();
        String fisier = fisiere.get(toArray.toString());

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fisier));
        Object[] array = (Object[]) in.readObject();
        in.close();

        return new ArrayList<>(Arrays.asList(array));
    }
}