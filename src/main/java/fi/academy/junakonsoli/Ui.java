package fi.academy.junakonsoli;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private String lahtoasema;
    private String maaraAsema;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.lahtoasema="";
        this.maaraAsema="";
    }

    public void kaynnista() {
        // Käynnistetään käyttöliittymä Mainista
        System.out.println("Junainfo");

        System.out.println("1: Tarkasta juna-aikataulut kahden aseman välillä");

        String komento = scanner.nextLine();

        switch (komento) {
            case "1":
                System.out.println("Syötä lähtöasema:");
                lahtoasema = scanner.nextLine();
                System.out.println("Syötä määräasema:");
                maaraAsema = scanner.nextLine();
               tulostaAikataulut(lahtoasema, maaraAsema);
               break;

            case "2":
                //toinen keissi tähän, seuraava lähtevä juna asemalta?
                System.out.println("Odotellaan toiminnallisuutta");
                break;
            case "3":
                //seuraava saapuva juna
                break;
            case "4":
                //asemalta lähteneet
                break;
        }
    }

    public void tulostaAikataulut(String lahtoasema, String maaraAsema) {



    }


}
