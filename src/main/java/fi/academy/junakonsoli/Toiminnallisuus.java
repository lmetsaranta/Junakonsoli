/* Created by Lennu Metsäranta. */

package fi.academy.junakonsoli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Toiminnallisuus {

    /* Hakee ja tulostaa junat kahden aseman väliltä. Hyödyntää Juna-luokan metodia "tulostaJuna", joka luo oikean tulostusasun
    lähtö- ja pääteaseman perusteella. Metodi saa parametrina lähtö- ja pääteaseman tunnukset. */
    public static void haeJunatAsemienPerusteella(String lahto, String paate) {
        if (lahto.equals(paate)) {
            System.err.println("Virheellinen syöte");
        } else {
            String baseurl = "https://rata.digitraffic.fi/api/v1";
            try {
                URL url = new URL(URI.create(String.format("%s/live-trains/station/" + lahto + "/" + paate, baseurl)).toASCIIString());
                ObjectMapper mapper = new ObjectMapper();
                CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
                List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);
                junat.stream()
                        .forEach(j -> System.out.println(j.tulostaJuna(lahto, paate)));
            } catch (Exception ex) {
                System.err.println("Valitettavasti valitsemaltasi väliltä ei löytynyt yhtään junaa.");
            }
        }
    }

    /* Hakee ja tulostaa yhden junan tiedot, sisältäen kaikki pysähdyspaikat. Hyödyntää Juna-luokan toString-metodia. */
    public static void haeJunaNumeronPerusteella(int numero) {
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            URL url = new URL(URI.create(String.format("%s/trains/latest/" + numero, baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);
            if (junat.isEmpty()) {
                System.err.println("Antamallasi numerolla ei löytynyt yhtään junaa.");
            }
            junat.stream()
                    .forEach(j -> System.out.println(j));
        } catch (Exception ex) {
            System.err.println("Antamallasi numerolla ei löytynyt yhtään junaa.");
        }
    }

    // Tulostaa kaikki junat, jotka ovat hakuhetkellä liikkessä. Hyödyntää Juna-luokan toString-metodia.
    public static void haeLiikkeessaOlevatJunat() {
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            List<Juna> junat = getJunaJSON(baseurl);
            junat.stream()
                    .filter(j -> j.runningCurrently).forEach(j -> System.out.println(j + "\n"));
        } catch (Exception ex) {
            System.err.println("Nyt ei enää kone jaksa hakea enempää...");
        }
    }

    // Tulostaa kaikki junat, jotka ovat hakuhetkellä liikkessä kahden aseman välillä. Hyödyntää Juna-luokan toString-metodia.
    public static void haeLiikkeessaOlevatJunatAsemienPerusteella(String lahto, String paate) {
        int eijunia = 1;
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            List<Juna> junat = getJunaJSON(baseurl);
            List<Juna> liikkuvat = junat.stream()
                    .filter(j -> j.runningCurrently)
                    .collect(Collectors.toList());
            for (Juna juna : liikkuvat) {
                for (int i = 0; i < juna.timeTableRows.size(); i++) {
                    if (juna.timeTableRows.get(i).stationShortCode.equals(lahto) && juna.timeTableRows.get(i).scheduledTime.compareTo(new Date()) < 0 && juna.timeTableRows.get(i).type.equals("DEPARTURE")) {
                        for (int y = 0; y < juna.timeTableRows.size(); y++) {
                            if (juna.timeTableRows.get(y).stationShortCode.equals(paate) && juna.timeTableRows.get(y).scheduledTime.compareTo(new Date()) > 0 && juna.timeTableRows.get(y).type.equals("ARRIVAL")) {
                                System.out.println(juna.tulostaJuna(lahto, paate));
                                eijunia = 2;
                            }
                        }
                    }
                }
            }
            if (eijunia == 1) {
                System.err.println("Antamillasi hakuehdoilla ei löytynyt yhtään junaa.");
            }
        } catch (Exception ex) {
            System.err.println("Haku ei onnistunut. Yritä myöhemmin uudelleen tai muuta antamiasi hakuehtoja.");
        }
    }
    // Metodi hakee junien JSON-datan REST-API:sta.
    private static List<Juna> getJunaJSON(String baseurl) throws IOException {
        URL url = new URL(URI.create(String.format("%s/trains/" + LocalDate.now(), baseurl)).toASCIIString());
        ObjectMapper mapper = new ObjectMapper();
        CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
        return mapper.readValue(url, tarkempiListanTyyppi);
    }
}