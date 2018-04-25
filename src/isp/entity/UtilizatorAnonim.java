package isp.entity;

public class UtilizatorAnonim extends Utilizator {
    public UtilizatorAnonim(String nume) {
        super(nume, "", "", "", "");
    }

    @Override
    public String afisare() {
        return "anon@" + getNume();
    }
}
