package fi.academy.junakonsoli;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

//Tekijä: Kirsi

public class AsemaTest {

    Asema asema = new Asema();

    //Testi testaa asemien koodien hakua nimen perusteella (arvon etsintä Hashmapista avaimen avulla). Testaa myös poikkeukset.
    @Test
    public void TestaaHaeAsemanKoodi() {
        String tulos = "HKI";
        String actual = asema.haeAsemanKoodi("Helsinki");
        String tulos2 = "TRI";
        String actual2 = asema.haeAsemanKoodi("Tornio");
        String tulos3 = "ITA";
        String actual3 = asema.haeAsemanKoodi("Iittala");
        assertEquals(tulos,actual);
        assertEquals(tulos2, actual2);
        assertEquals(tulos3, actual3);
    }

    //Testi testaa asemien nimien hakua koodien perusteella (avaimen etsintä Hashmapista arvon avulla). Testaa myös poikkeukset.
    @Test
    public void TestaaHaeAsemanNimi() {
        String tulos = "Tuomarila";
        String actual = asema.haeAsemanNimi("TRL");
        String tulos2 = "Tampere";
        String actual2 = asema.haeAsemanNimi("TPE");
        String tulos3 = "Tornio";
        String actual3 = asema.haeAsemanNimi("TRI");
        assertEquals(tulos, actual);
        assertEquals(tulos2, actual2);
        assertEquals(tulos3, actual3);
    }
}