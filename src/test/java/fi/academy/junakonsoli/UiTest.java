package fi.academy.junakonsoli;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class UiTest {
    Scanner sc = new Scanner(System.in);

    @Test
    public void metodiMuokkaaPalauttaaOikeassaMuodossa() {
        Ui u = new Ui(sc);
        String expected = "Turku asema";
        String actual = u.muokkaaAsemanNimi(" tuRku aseMa ");
        String expected2 = "Helsinki";
        String actual2 = u.muokkaaAsemanNimi("HELSINKI");
        assertEquals(expected, actual);
        assertEquals(expected2, actual2);
    }



}