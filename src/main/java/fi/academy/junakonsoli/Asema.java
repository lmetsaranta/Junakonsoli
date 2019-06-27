package fi.academy.junakonsoli;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;


public class Asema {
    //luo asemien hakuun tarvittavan HashMapin
    private HashMap<String, String> asemat = new HashMap<>();

    //hakee aseman koodin aseman nimen perusteella
    public String haeAsemanKoodi(String nimi) {
        lueAsemienMetaData();
        if (Stream.of("Hanko", "Helsinki", "Ilmala", "Imatra", "Joensuu", "Järvenpää", "Kauklahti", "Kotka", "Kouvola", "Kuopio", "Oulu",
                "Pasila", "Pieksämäki", "Riihimäki", "Savonlinna", "Seinäjoki", "Tampere",
                "Tikkurila", "Turku", "Vainikkala").anyMatch(nimi::equalsIgnoreCase)) {
            nimi = nimi + " asema";
        } else if (nimi.equals("Tornio")) {
            nimi = nimi + "-Itäinen";
        }
        if (!asemat.containsKey(nimi)) {
            System.err.println("Asemaa ei löytynyt! Syötä oikea aseman nimi");
        }
        String koodi = asemat.get(nimi);
        return koodi;
    }

    //hakee aseman nimen aseman koodin perusteella
    public String haeAsemanNimi(String koodi) {
        lueAsemienMetaData();
        String nimi = haeAvainArvolla(asemat, koodi);
        String regex = "\\s|\\-";
        String [] asemanNimi = nimi.split(regex);
        nimi = asemanNimi[0];
        return nimi;
    }

    //apumetodi: hakee Hashmapista avaimen (aseman nimen) arvon perusteella
    public static String haeAvainArvolla(HashMap hm, Object arvo) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(arvo)) {
                return (String) o;
            }
        }
        return null;
    }

    // lukee asemien metadataa rest apista
    public void lueAsemienMetaData() {
        String kokolista = "";
        URL url = null;
        try {
            url = new URL("https://rata.digitraffic.fi/api/v1/metadata/stations");
        } catch (MalformedURLException e) {
            System.err.println("Virheellinen url-osoite!");
        }
        //avaa url-yhteyden
        HttpURLConnection yhteys = null;
        try {
            yhteys = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            System.err.println("Input/Output epäonnistui!");
        }
        try {
            yhteys.setRequestMethod("GET");
            yhteys.connect();
            int vastauskoodi = yhteys.getResponseCode();
            //käsittelee virheet, mikäli datan haku ei onnistu
            if (vastauskoodi != 200) {
                throw new RuntimeException("Virhe! HttpResponseCode: " + vastauskoodi);
            } else {
                //lukee dataa avoimesta url-streamista
                Scanner lukija = new Scanner(url.openStream());
                while (lukija.hasNext()) {
                    kokolista += lukija.nextLine();
                }
                //sulkee lukijan ja parseroi datan stringeiksi parserointimetodia kutsumalla
                lukija.close();
                parseroiAsemienJSONdata(kokolista).toString();

            }
        } catch (Exception e) {
            System.err.println("Tapahtui virhe!" + e);
            //sulkee url-yhteyden
        } finally {
            yhteys.disconnect();
        }
    }

    //parserointimetodi: lukee urlista JSON dataa ja parseroi sen
    public HashMap parseroiAsemienJSONdata(String sisalto) {
        JSONArray junat = new JSONArray(sisalto);
        for (int i = 0; i < junat.length(); i++) {
            JSONObject juna = junat.getJSONObject(i);
            //hakee datasta aseman nimet, koodit ja tiedon onko matkustaja-asema
            String asemanNimi = juna.getString("stationName");
            String asemanKoodi = juna.getString("stationShortCode");
            boolean matkustajaAsema = juna.getBoolean("passengerTraffic");
            //jos kyseessä on matkustaja-asema, lisää nimet ja koodit hashmapiin
            if (matkustajaAsema == true) {
                asemat.put(asemanNimi, asemanKoodi);
            }
        }
        return asemat;
    }
}