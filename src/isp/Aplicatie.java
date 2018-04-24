package isp;

import isp.collections.services.ServiciuSerializare;
import isp.entity.Utilizator;

import java.util.ArrayList;

public class Aplicatie {
    public static void main(String[] args) {
        try {
            ArrayList<Utilizator> users = ServiciuSerializare.getInstance().incarcareFisier(Utilizator.class);
            users.forEach(user -> System.out.println(user.afisare()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
