package isp;

import isp.collections.CatalogImagine;
import isp.collections.CatalogUtilizatori;
import isp.collections.services.ServiciuUtilizatori;
import isp.entity.*;
import isp.exception.RegistrationException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicatie {
    private static Aplicatie instance = null;
    public static final String SEPARATOR = "-------------------------------------------------------";
    private Utilizator utilizatorCurent = null;
    public static final Utilizator GLOBAL_USER = new Utilizator(
        "Fairy Godmother",
        "123",
        "selena@fairysociety.com",
        "The Magic School of White, Dark, Grey and Pink Fairies",
        "1970505471333"
    );

    public enum Command {   // comenzi de executat in consola dupa rulare; pur si simplu scrieti comanda
        login,          // login cu un user inregistrat
        auto,           // login automat pe userul global
        logout,         // logout de pe userul curent
        register,       // inregistreaza un utilizator nou
        profile,        // afiseaza profil pentru utilizatorul curent
        list,           // listeaza imaginile utilizatorului curent
        add,            // adauga o imagine; daca nu sunteti logat, veti posta o imagine drept utilizator anonim
        rm,             // sterge o imagine
        update,         // update de stare pentru o imagine
        exit            // inchide aplicatia definitiv
    }

    public static Aplicatie getInstance() {
        if (instance == null) {
            instance = new Aplicatie();
        }

        return instance;
    }

    private Aplicatie() {
    }

    public Utilizator getUtilizatorCurent() {
        return utilizatorCurent;
    }

    public void setUtilizatorCurent(Utilizator utilizator) {
        this.utilizatorCurent = utilizator;
    }

    public void secventaLogin(Scanner scanner) {
        System.out.println(SEPARATOR);
        System.out.println("Introdu numele de utilizator: ");
        String username = scanner.nextLine();
        System.out.println("Introdu parola: ");
        String password = scanner.nextLine();
        System.out.println("Autentificare... ");
        if (ServiciuUtilizatori.getInstance().autentificareUtilizator(username, password)) {
            System.out.println("Autentificare reusita!");
            utilizatorCurent = CatalogUtilizatori.getInstance().cautaUtilizator(username);
            System.out.println(SEPARATOR);
        } else {
            System.out.println("Autentificare esuata, doresti sa incerci din nou? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.startsWith("Y") || choice.startsWith("y")) {
                secventaLogin(scanner);
            } else System.out.println(SEPARATOR);
        }
    }

    public void secventaInregistrare(Scanner scanner) {
        System.out.println(SEPARATOR);
        System.out.println("Introdu numele de utilizator: ");
        String username = scanner.nextLine();
        System.out.println("Introdu parola: ");
        String parola = scanner.nextLine();
        System.out.println("Introdu emailul: ");
        String email = scanner.nextLine();
        System.out.println("Introdu universitatea: ");
        String universitate = scanner.nextLine();
        System.out.println("Introdu CNP-ul: ");
        String cnp = scanner.nextLine();

        try {
            ServiciuUtilizatori.getInstance().inregistrareUtilizator(username, parola, email, universitate, cnp);
            System.out.println("Inregistrare reusita!");
            System.out.println(SEPARATOR);
        } catch (RegistrationException e) {
            System.out.println(e.getMessage());
            System.out.println("Doresti sa incerci din nou? (Y/N)");
            String choice = scanner.nextLine();
            if (choice.startsWith("Y") || choice.startsWith("y")) {
                secventaInregistrare(scanner);
            } else System.out.println(SEPARATOR);
        }
    }

    public void secventaProfil() {
        System.out.println(SEPARATOR);
        if (utilizatorCurent != null) {
            System.out.println(utilizatorCurent.afisare());
        } else {
            System.out.println("Nu esti autentificat!");
        }
        System.out.println(SEPARATOR);
    }

    public void secventaImaginiProprii() {
        System.out.println(SEPARATOR);
        if (utilizatorCurent != null && !(utilizatorCurent instanceof UtilizatorAnonim)) {
            ArrayList<Imagine> images = CatalogImagine.getInstance().cautareDupaUtilizator(utilizatorCurent);
            images.forEach(image -> System.out.println(image.toString()));
        } else if (utilizatorCurent instanceof UtilizatorAnonim) {
            System.out.println("Utilizatorii anonimi nu pot vedea imaginile adaugate. Te rugam sa te inregistrezi pentru a vedea imaginile!");
        } else {
            System.out.println("Nu esti autentificat!");
        }
        System.out.println(SEPARATOR);
    }

    public Imagine secventaAdaugareImagine(Scanner scanner) {
        System.out.println(SEPARATOR);
        if (utilizatorCurent == null) {
            System.out.println("Nu esti autentificat. Vom adauga poza ca utilizator anonim. Daca doresti sa poti reedita poza ulterior, inregistreaza-te!");
            System.out.println("Alege un nume: ");
            String username = scanner.nextLine();
            try {
                utilizatorCurent = ServiciuUtilizatori.getInstance().inregistrareAnonima(username);
            } catch (RegistrationException e) {
                System.out.println(e.getMessage());
                System.out.println("Doresti sa incerci din nou? (Y/N)");
                String choice = scanner.nextLine();
                if (choice.startsWith("Y") || choice.startsWith("y")) {
                    return secventaAdaugareImagine(scanner);
                } else {
                    System.out.println(SEPARATOR);
                    return null;
                }
            }
        }

        CatalogImagine catalogImagine = CatalogImagine.getInstance();
        System.out.println("Introdu sursa imaginii (URL sau PATH): ");
        String sursa = scanner.nextLine();
        System.out.println("Introdu tipul de defectiune prezent in imagine: ");
        System.out.println("1 - Obiect Spart");
        System.out.println("2 - Aparatura Defecta");
        System.out.println("3 - Absenta Resurselor");
        System.out.println("4 - Element Inestetic");
        System.out.println("5 - Poate fi imbunatatit");
        System.out.println("6 - Altele");
        TipDefectiune tipDefectiune = null;
        String choiceLine = scanner.nextLine();
        int choice = Integer.parseInt(choiceLine);
        if (choice == 1) {
            tipDefectiune = TipDefectiune.obiectSpart;
        } else if (choice == 2) {
            tipDefectiune = TipDefectiune.aparaturaDefecta;
        } else if (choice == 3) {
            tipDefectiune = TipDefectiune.absentaResurse;
        } else if (choice == 4) {
            tipDefectiune = TipDefectiune.elementInestetic;
        } else if (choice == 5) {
            tipDefectiune = TipDefectiune.poateFiImbunatatit;
        } else {
            tipDefectiune = TipDefectiune.altele;
        }

        System.out.println("Introdu o descriere a imaginii si apasa ENTER: ");
        String descriere = scanner.nextLine();
        Imagine imagine = new Imagine(sursa, utilizatorCurent, tipDefectiune, descriere);
        catalogImagine.adaugaImagine(imagine);
        System.out.println("Imaginea a fost adaugata cu succes!");

        System.out.println(SEPARATOR);
        return imagine;
    }

    public void secventaStergere(Scanner scanner) {
        if (utilizatorCurent == null) {
            System.out.println(SEPARATOR);
            System.out.println("Nu esti autentificat!");
            System.out.println(SEPARATOR);
        } else {
            secventaImaginiProprii();
            System.out.println("Introdu ID-ul imaginii pe care doresti sa o stergi: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Imagine imagine = utilizatorCurent.getImagini().stream().filter(imagine1 -> imagine1.getId() == id).findFirst().orElse(null);
            if (imagine != null) {
                CatalogImagine.getInstance().stergeImagine(id);
                System.out.println("Imaginea a fost stearsa cu succes!");
            } else {
                System.out.println("Imaginea nu este a ta sau nu exista! Nu a putut fi stearsa.");
            }
            System.out.println(SEPARATOR);
        }
    }

    public void secventaModificare(Scanner scanner) {
        if (utilizatorCurent == null) {
            System.out.println(SEPARATOR);
            System.out.println("Nu esti autentificat!");
            System.out.println(SEPARATOR);
        } else {
            System.out.println("Introdu ID-ul imaginii pe care doresti sa o editezi: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Imagine imagine = utilizatorCurent.getImagini().stream().filter(imagine1 -> imagine1.getId() == id).findFirst().orElse(null);
            System.out.println(imagine.toString());
            if (imagine != null) {
                SituatieDefect stareNoua;
                System.out.println("Introdu noua stare pe care doresti sa o setezi: ");
                System.out.println("1 - Deschis");
                System.out.println("2 - Amanat");
                System.out.println("3 - In curs de rezolvare");
                System.out.println("4 - Rezolvat");
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1)
                    stareNoua = SituatieDefect.deschis;
                else if (choice == 2)
                    stareNoua = SituatieDefect.amanat;
                else if (choice == 3)
                    stareNoua = SituatieDefect.inCursDeRezolvare;
                else if (choice == 4)
                    stareNoua = SituatieDefect.rezolvat;
                else
                    stareNoua = SituatieDefect.deschis;
                CatalogImagine.getInstance().updateImagine(id, stareNoua);
                System.out.println("Imaginea a fost modificată cu succes!");
            } else {
                System.out.println("Imaginea nu este a ta sau nu exista! Nu a putut fi modificată.");
            }
            System.out.println(SEPARATOR);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Aplicatie app = Aplicatie.getInstance();

        while (true) {
            try {
                Command cmd = Command.valueOf(scanner.nextLine());
                switch (cmd) {
                    case login:
                        app.secventaLogin(scanner);
                        break;
                    case logout:
                        app.setUtilizatorCurent(null);
                        System.out.println("Delogare reusita!");
                        System.out.println(SEPARATOR);
                        break;
                    case auto:
                        app.setUtilizatorCurent(GLOBAL_USER);
                        System.out.println(SEPARATOR);
                        System.out.println("Autentificare rapida pentru test utilizata!");
                        System.out.println(SEPARATOR);
                        break;
                    case register:
                        app.secventaInregistrare(scanner);
                        break;
                    case profile:
                        app.secventaProfil();
                        break;
                    case list:
                        app.secventaImaginiProprii();
                        break;
                    case add:
                        app.secventaAdaugareImagine(scanner);
                        break;
                    case rm:
                        app.secventaStergere(scanner);
                        break;
                    case update:
                        app.secventaModificare(scanner);
                        break;
                    case exit:
                        System.exit(0);
                    default:
                        System.out.println("Illegal action");
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
