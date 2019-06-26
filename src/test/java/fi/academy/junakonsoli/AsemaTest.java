package fi.academy.junakonsoli;

import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.*;

public class AsemaTest {

    Asema asema = new Asema();

    @Test
    public void TestaaHaeAsemanKoodi() throws IOException {
        String tulos = "DRA";
        String actual = asema.haeAsemanKoodi("Dragsvik");
        assertEquals(tulos,actual);
    }

    @Test
    public void TestaaHaeAsemanNimi() throws IOException {
        String tulos = "Tuomarila";
        String actual = asema.haeAsemanNimi("TRL");
        assertEquals(tulos, actual);
    }
}