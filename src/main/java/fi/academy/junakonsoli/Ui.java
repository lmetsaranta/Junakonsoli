package fi.academy.junakonsoli;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private JSON_pohja_junat junat = new JSON_pohja_junat();
    private String lahtoasema;
    private String maaraAsema;
    private JSON_pohja_junat toiminta;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.lahtoasema="";
        this.maaraAsema="";
        //this.toiminta = new JSON_pohja_junat();
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
        JSON_pohja_junat.lueJunanJSONData(lahtoasema, maaraAsema);

    }

}
