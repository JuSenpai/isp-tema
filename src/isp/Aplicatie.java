package isp;

import isp.collections.services.ServiciuUtilizatori;

public class Aplicatie {
    public static void main(String[] args) {
        try {
            ServiciuUtilizatori.getInstance().inregistrareUtilizator(
                "Cosmin",
                "123pass",
                "cosmin.stoica@zitec.com",
                "Politehnica",
                "1970505471333"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
