//Heikki, mostly..
package fi.academy.junakonsoli;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private String asemanNimi;
    private String maaraAsema;
    private int junanNumero;
    private Toiminnallisuus toiminta;
    private Asema asema;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
        this.asemanNimi = "";
        this.maaraAsema = "";
        this.junanNumero = 0;
        this.asema = new Asema();
        this.toiminta = new Toiminnallisuus();
    }

    public void kaynnista() {
        String komento = "";

        do {
            System.out.print(
                    "\nToiminnot: \n" +
                            "1: Tarkasta juna-aikataulut kahden aseman välillä \n" +
                            "2: Hae yksittäisen junan aikataulua numeron perusteella \n" +
                            "3: Hae liikkeellä olevat junat kahden aseman välillä \n" +
                            "4: Listaa kaikki liikkeellä olevat junat \n" +
                            "0: Poistu junakonsolista \n\n" +
                            "Valitse toiminto: ");

            String lahtoAsema = "Syötä lähtöasema: ";
            String maaraAsema = "Syötä määräasema: ";
            komento = scanner.nextLine();
            switch (komento) {
                case "1":
                    // metodin parametreina metodit, jotka kysyvät aseman nimen ja palauttavat sitä vastaavan koodin, joilla haetaan aikataulut
                    tulostaAikataulut(syotaAsema(lahtoAsema), syotaAsema(maaraAsema));
                    System.out.println("");
                    komento="";
                    break;

                case "2":
                    // jos käyttäjä syöttää ei-olemassa olevan junan numeron, Toiminnallisuus luokasta palautetaan tästä tieto
                    System.out.println("Syötä junan numero");

                    try {
                        junanNumero = Integer.valueOf(scanner.nextLine());
                        tulostaJunanTiedot(junanNumero);
                        System.out.println("");
                    } catch (NumberFormatException e) {
                        System.err.print("Virheellinen syöte. Syötithän numeron? \n");
                    }

                    break;
                case "3":
                    //tulostaa liikkeessä olevat junat asemien perusteella
                    Toiminnallisuus.haeLiikkeessaOlevatJunatAsemienPerusteella(syotaAsema(lahtoAsema), syotaAsema(maaraAsema));
                    break;

                case "4":
                    //listaa liikkeellä olevat junat
                    Toiminnallisuus.haeLiikkeessaOlevatJunat();
                    break;
            }
        } while (!komento.equals("0"));
        System.out.println("Kiitos ja näkemiin!");
    }
    //kysyy aseman ja muuttaa sen aseman lyhenteeksi. kysyy syötettä kunnes järkevä nimi. Message tulee asema-luokasta.
    private String syotaAsema(String viestiKayttajalle) {
        System.out.println(viestiKayttajalle);
        String syotettyNimi = muokkaaAsemanNimi(scanner.nextLine());
        asemanNimi = asema.haeAsemanKoodi(syotettyNimi);
        while (asemanNimi == null) {
            syotettyNimi = muokkaaAsemanNimi(scanner.nextLine());
            asemanNimi = asema.haeAsemanKoodi(syotettyNimi);
        }
        return asemanNimi;
    }
    // Aseman nimisyötteen trimmaus ja jos aseman virallisessa nimessä kaksi osaa niin lisätään jälkimmäinen mukaan ja palautetaan.
    public String muokkaaAsemanNimi(String syote) {
        String[] asemanNimenosat = syote.trim().split(" ");
        String asemanNimi = asemanNimenosat[0].trim().toLowerCase();
        asemanNimi = asemanNimi.substring(0, 1).toUpperCase() + asemanNimi.substring(1);
        if (asemanNimenosat.length == 2) {
            return asemanNimi + " " + asemanNimenosat[1].toLowerCase();
        }
        return asemanNimi;
    }

    public void tulostaAikataulut(String lahtoasema, String maaraAsema) {
        Toiminnallisuus.haeJunatAsemienPerusteella(lahtoasema, maaraAsema);
    }

    public void tulostaJunanTiedot(int numero) {
        Toiminnallisuus.haeJunaNumeronPerusteella(numero);
    }
}
