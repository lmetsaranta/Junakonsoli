package fi.academy.junakonsoli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private String lahtoasema;
    private String maaraAsema;
    private Asema asema;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.lahtoasema = "";
        this.maaraAsema = "";
        this.asema = new Asema();
    }

    public void kaynnista() throws IOException { // heittää mainiin, pitäis varmaan käsitellä siellä?
        System.out.println("Junainfo");

        System.out.println("1: Tarkasta juna-aikataulut kahden aseman välillä");

        do {
            String komento = scanner.nextLine();

            switch (komento) {
                case "1":
                    System.out.println("Syötä lähtöasema:");
                    String asemanNimi = muokkaa(scanner.nextLine());
                    lahtoasema = asema.haeAsemanKoodi(asemanNimi);
                    System.out.println("Syötä määräasema:");
                    asemanNimi = muokkaa(scanner.nextLine());
                    maaraAsema = asema.haeAsemanKoodi(asemanNimi);

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
        } while ();
    }

    // Muokataan syötettä -> trimmaus ja jos kaksi osaa niin lisätään se mukaan ja palautetaan.
    private String muokkaa(String syote) {
        String [] asemanNimenosat = syote.split(" ");
        String asemanNimi = asemanNimenosat[0].trim().toLowerCase();
        asemanNimi = asemanNimi.substring(0, 1).toUpperCase() + asemanNimi.substring(1);
        if (asemanNimenosat.length==2) {
            return asemanNimi + " " + asemanNimenosat[1];
        }
        return asemanNimi;
    }

    public void tulostaAikataulut(String lahtoasema, String maaraAsema) {

        JSON_pohja_junat.lueJunanJSONData(lahtoasema, maaraAsema);
    }

}
