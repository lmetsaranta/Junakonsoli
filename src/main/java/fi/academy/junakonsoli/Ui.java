package fi.academy.junakonsoli;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public void kaynnista() {
        System.out.println("Junainfo");

        System.out.println("1: Tarkasta juna-aikataulut kahden aseman välillä");

        String komento = scanner.nextLine();

        switch (komento) {
            case "1":
               lisaaAsemat();
               tulostaAikataulut();
               break;

            case "2":
                //toinen keissi tähän
                System.out.println("Odotellaan toiminnallisuutta");
        }
    }

    public void lisaaAsemat() {
        System.out.println("Lisää lähtöaseman lyhenne");
        String lahtoAsemanLyhenne = scanner.nextLine();
        System.out.println("Syötä määräaseman lyhenne");
        String maaraAsemanLyhenne = scanner.nextLine();

        //luo näillä parametreilla uusi asema
    }

    public void tulostaAikataulut() {
        // tulosta junien aikataulut asemien välillä
    }


}
