/* Created by Lennu Metsäranta. */

package fi.academy.junakonsoli;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import java.net.URI;
import java.net.URL;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
Vaatii Jackson kirjaston:
File | Project Structure
Libraries >> Add >> Maven
Etsi "jackson-databind", valitse esimerkiksi versio 2.0.5
Asentuu Jacksonin databind, sekä core ja annotations
 */

public class Toiminnallisuus {
    /* Hakee ja tulostaa junat kahden aseman väliltä. Hyödyntää Juna-luokan metodia "tulostaJuna", joka luo oikean tulostusasun
    lähtö- ja pääteaseman perusteella. Metodi saa parametrina lähtö- ja pääteaseman tunnukset. */
    public static void haeJunatAsemienPerusteella(String lahto, String paate) {
        if (lahto.equals(paate)) {
            System.out.println("Virheellinen syöte");
        } else {
            String baseurl = "https://rata.digitraffic.fi/api/v1";
            try {
                URL url = new URL(URI.create(String.format("%s/live-trains/station/" + lahto + "/" + paate, baseurl)).toASCIIString());
                ObjectMapper mapper = new ObjectMapper();
                CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
                List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
                junat.stream()
                        .forEach(j -> System.out.println(j.tulostaJuna(lahto, paate)));
            } catch (Exception ex) {
                System.out.println("Valitettavasti valitsemaltasi väliltä ei löytynyt yhtään junaa.");
            }
        }
    }

    /* Hakee ja tulostaa yhden junan tiedot, sisältäen kaikki pysähdyspaikat. Hyödyntää Juna-luokan toString-metodia. */
    public static void haeJunaNumeronPerusteella(int numero) {
        int junannumero = numero;
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            URL url = new URL(URI.create(String.format("%s/trains/latest/" + junannumero, baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
            if (junat.isEmpty()) {
                System.err.println("Junaa ei ole olemassa");
            }
            junat.stream()
                    .forEach(j -> System.out.println(j));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    // Tulostaa kaikki junat, jotka ovat hakuhetkellä liikkessä. Hyödyntää Juna-luokan toString-metodia.
    public static void haeLiikkeessaOlevatJunat() {
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            URL url = new URL(URI.create(String.format("%s/trains/" + LocalDate.now(), baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
            junat.stream()
                    .filter(j -> j.runningCurrently == true).forEach(j -> System.out.println(j));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void haeLiikkeessaOlevatJunatAsemienPerusteella(String lahto, String paate) {
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            URL url = new URL(URI.create(String.format("%s/trains/" + LocalDate.now(), baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
            List<Juna> liikkuvat = junat.stream()
                    .filter(j -> j.runningCurrently)
                    .collect(Collectors.toList());
            for (Juna juna : liikkuvat) {
                List<Juna> filtteroidut = new ArrayList<>();
                for (int i = 0; i < juna.timeTableRows.size(); i++) {
                    if (juna.timeTableRows.get(i).stationShortCode.equals(lahto) && juna.timeTableRows.get(i).scheduledTime.compareTo(new Date()) < 0) {
                        filtteroidut.add(juna);
                    }
                }
                for (Juna train : filtteroidut) {
                    for (int i = 0; i < juna.timeTableRows.size(); i++) {
                        if (juna.timeTableRows.get(i).stationShortCode.equals(paate) && juna.timeTableRows.get(i).scheduledTime.compareTo(new Date()) > 0) {
                            System.out.println(juna.toString());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

