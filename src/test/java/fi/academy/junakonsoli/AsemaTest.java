package fi.academy.junakonsoli;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class AsemaTest {

    Asema asema = new Asema();

    @Test
    public void TestaaHaeAsemanKoodi() {
        String tulos = "TPE";
        String actual = asema.haeAsemanKoodi("Tampere");
        String tulos2 = "TRI";
        String actual2 = asema.haeAsemanKoodi("Tornio");
        String tulos3 = "ITA";
        String actual3 = asema.haeAsemanKoodi("Iittala");
        assertEquals(tulos,actual);
        assertEquals(tulos2, actual2);
        assertEquals(tulos3, actual3);
    }

    @Test
    public void TestaaHaeAsemanNimi() {
        String tulos = "Tuomarila";
        String actual = asema.haeAsemanNimi("TRL");
        String tulos2 = "Helsinki";
        String actual2 = asema.haeAsemanNimi("HKI");
        String tulos3 = "Tornio";
        String actual3 = asema.haeAsemanNimi("TRI");
        assertEquals(tulos, actual);
        assertEquals(tulos2, actual2);
        assertEquals(tulos3, actual3);
    }
}