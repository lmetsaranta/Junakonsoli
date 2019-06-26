package fi.academy.junakonsoli;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableRowTest {

    @Test
    void paivamaaraMuuntoTest() {
        TimeTableRow testi = new TimeTableRow();
        LocalDateTime testaus = LocalDateTime.of(2019,2,28,11,05);
        Date date = new Date(119,1,28,11,05);
        LocalDateTime paiva = testi.paivamaaraMuunto(date);
        assertEquals(testaus,paiva);

    }

    @Test
    void paivamaaraTest() {
        TimeTableRow testi = new TimeTableRow();
        String testaus = "28.2.2019";
        Date date = new Date(119,1,28);
        String paiva = testi.paivamaara(date);
        assertEquals(testaus,paiva);
    }

    @Test
    void paivamaaraMaaliskuunEnsimmäinenTest() {
        TimeTableRow testi = new TimeTableRow();
        String testaus = "1.3.2019";
        Date date = new Date(119,1,29);
        String paiva = testi.paivamaara(date);
        assertEquals(testaus,paiva);
    }

    @Test
    void kellonaikaTest() {
        TimeTableRow testi = new TimeTableRow();
        String testaus = "22:01";
        Date date = new Date(119,1,28,22,01);
        String kello = testi.kellonaika(date);
        assertEquals(testaus,kello);
    }

    @Test
    void kellonaikaAamuTest() {
        TimeTableRow testi = new TimeTableRow();
        String testaus = "8:55";
        Date date = new Date(119,1,28,8,55);
        String kello = testi.kellonaika(date);
        assertEquals(testaus,kello);
    }
}