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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            URL url = new URL(URI.create(String.format("%s/live-trains/station/" + lahto + "/" + paate, baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
            junat.stream()
                    .forEach(j -> System.out.println(j.tulostaJuna(paate)));
        } catch (Exception ex) {
            System.out.println(ex);
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
            URL url = new URL(URI.create(String.format("%s/trains/" + LocalDate.now() , baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
            junat.stream()
                    .filter(j -> j.runningCurrently == true).forEach(j -> System.out.println(j));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}

