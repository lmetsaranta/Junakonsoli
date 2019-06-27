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
        assertEquals(tulos,actual);
    }

    @Test
    public void TestaaHaeAsemanNimi() {
        String tulos = "Tuomarila";
        String actual = asema.haeAsemanNimi("TRL");
        assertEquals(tulos, actual);
    }
}