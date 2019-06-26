package fi.academy.junakonsoli;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;


public class Asema {
    //luo asemien hakuun tarvittavan HashMapin
    private HashMap<String, String> asemat = new HashMap<>();

    //hakee aseman kolmikirjaimisen koodin aseman nimen perusteella
    public String haeAsemanKoodi(String nimi) throws IOException {
        lueAsemienMetaData();
        if (Stream.of("Hanko", "Helsinki", "Ilmala", "Imatra", "Joensuu", "Järvenpää", "Kauklahti", "Kotka", "Kouvola", "Kuopio", "Oulu",
                "Pasila", "Pieksämäki", "Riihimäki", "Savonlinna", "Seinäjoki", "Tampere",
                "Tikkurila", "Tornio", "Turku", "Vainikkala").anyMatch(nimi :: equalsIgnoreCase)){
                nimi = nimi + " asema";
        }
        String koodi = asemat.get(nimi);
        return koodi;
    }

    //hakee aseman nimen aseman koodin perusteella
    public String haeAsemanNimi(String koodi) throws IOException {
        lueAsemienMetaData();
        String nimi = haeAvainArvolla(asemat, koodi);
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
    public void lueAsemienMetaData() throws IOException {
        String kokolista = "";
        URL url = new URL("https://rata.digitraffic.fi/api/v1/metadata/stations");
    //avaa url-yhteyden
        HttpURLConnection yhteys = (HttpURLConnection) url.openConnection();
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
