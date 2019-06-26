package fi.academy.junakonsoli;

import java.util.Date;
import java.util.List;

import static fi.academy.junakonsoli.TimeTableRow.kellonaika;
import static fi.academy.junakonsoli.TimeTableRow.paivamaara;

public class Juna {
    boolean cancelled;
    String commuterLineID;
    //LocalDate departureDate;  // Jackson ei oikein pidä Java 8 päivistä oletuksena
    Date departureDate;
    String operatorShortCode;
    int operatorUICCode;
    boolean runningCurrently;
    List<TimeTableRow> timeTableRows;
    Date timetableAcceptanceDate;
    String timetableType;
    String trainCategory;
    int trainNumber;
    String trainType;
    long version;

    @Override
    // Tulostetaan junan tiedot muodossa "Long-distance IC111 HKI 26.6.2019 klo 10.24 - TPE klo 11.55"
    public String toString() {
        return trainCategory + " " + trainType + trainNumber + " " + timeTableRows.get(0).stationShortCode + " " + paivamaara(timeTableRows.get(0).scheduledTime) + " " + kellonaika(timeTableRows.get(0).scheduledTime) + " - " + getPaateasema("TPE") + " " + kellonaika(getSaapumisaika("TPE"));
    }
    /* Haetaan pääteaseman mukaan kyseisen aseman tunnus. (Tässä vaiheessa vielä turha metodi kun haetaan tunnuksella tunnus..)
    Tehdään myöhemmin ominaisuus, että toimii haulla "Helsinki" --> palauttaa "HKI".
     */
    private String getPaateasema(String asema) {
        String paateasema = " ";
        for (TimeTableRow rivi: timeTableRows) {
            if (rivi.stationShortCode.equals(asema) && rivi.type.equals("ARRIVAL")) {
                paateasema = rivi.stationShortCode;
            }
        }
        return paateasema;
    }
    // Haetaan pääteaseman perusteella kyseisen junan saapumisaika tälle asemalle.
    private Date getSaapumisaika(String asema) {
        Date saapumisaika = new Date();
        for (TimeTableRow rivi: timeTableRows) {
            if (rivi.stationShortCode.equals(asema) && rivi.type.equals("ARRIVAL")) {
                saapumisaika = rivi.scheduledTime;
            }
        }
        return saapumisaika;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getCommuterLineID() {
        return commuterLineID;
    }

    public void setCommuterLineID(String commuterLineID) {
        this.commuterLineID = commuterLineID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getOperatorShortCode() {
        return operatorShortCode;
    }

    public void setOperatorShortCode(String operatorShortCode) {
        this.operatorShortCode = operatorShortCode;
    }

    public int getOperatorUICCode() {
        return operatorUICCode;
    }

    public void setOperatorUICCode(int operatorUICCode) {
        this.operatorUICCode = operatorUICCode;
    }

    public boolean isRunningCurrently() {
        return runningCurrently;
    }

    public void setRunningCurrently(boolean runningCurrently) {
        this.runningCurrently = runningCurrently;
    }

    public List<TimeTableRow> getTimeTableRows() {
        return timeTableRows;
    }

    public void setTimeTableRows(List<TimeTableRow> timeTableRows) {
        this.timeTableRows = timeTableRows;
    }

    public Date getTimetableAcceptanceDate() {
        return timetableAcceptanceDate;
    }

    public void setTimetableAcceptanceDate(Date timetableAcceptanceDate) {
        this.timetableAcceptanceDate = timetableAcceptanceDate;
    }

    public String getTimetableType() {
        return timetableType;
    }

    public void setTimetableType(String timetableType) {
        this.timetableType = timetableType;
    }

    public String getTrainCategory() {
        return trainCategory;
    }

    public void setTrainCategory(String trainCategory) {
        this.trainCategory = trainCategory;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}