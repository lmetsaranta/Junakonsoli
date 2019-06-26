package fi.academy.junakonsoli;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

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

public class JSON_pohja_junat {
    public static void main(String[] args) {
        lueJunanJSONData();
    }


    private static void lueJunanJSONData() {
        String baseurl = "https://rata.digitraffic.fi/api/v1";
        try {
            URL url = new URL(URI.create(String.format("%s/live-trains/station/HKI/LH", baseurl)).toASCIIString());
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi
            System.out.println(junat.get(0).getTrainNumber());
            // Seuraavaa varten on toteutettava TimeTableRow luokka:
            System.out.println(TimeTableRow.paivamaara(junat.get(0).getTimeTableRows().get(0).getScheduledTime()) + " ja " + TimeTableRow.kellonaika(junat.get(0).getTimeTableRows().get(0).getScheduledTime()));
            System.out.println("\n\n");
            System.out.println(junat.get(0));

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}


//class Juna {
//    boolean cancelled;
//    String commuterLineID;
//    //LocalDate departureDate;  // Jackson ei oikein pidä Java 8 päivistä oletuksena
//    Date departureDate;
//    String operatorShortCode;
//    int operatorUICCode;
//    boolean runningCurrently;
//    List<TimeTableRow> timeTableRows;
//    Date timetableAcceptanceDate;
//    String timetableType;
//    String trainCategory;
//    int trainNumber;
//    String trainType;
//    long version;
//
//    @Override
//    public String toString() {
//        return "Juna{" + "cancelled=" + cancelled + ", commuterLineID='" + commuterLineID + '\'' + ", departureDate=" + departureDate + ", operatorShortCode='" + operatorShortCode + '\'' + ", operatorUICCode=" + operatorUICCode + ", runningCurrently=" + runningCurrently + ", timeTableRows=" + timeTableRows + ", timetableAcceptanceDate=" + timetableAcceptanceDate + ", timetableType='" + timetableType + '\'' + ", trainCategory='" + trainCategory + '\'' + ", trainNumber=" + trainNumber + ", trainType='" + trainType + '\'' + ", version=" + version + '}';
//    }
//
//    public boolean isCancelled() {
//        return cancelled;
//    }
//
//    public void setCancelled(boolean cancelled) {
//        this.cancelled = cancelled;
//    }
//
//    public String getCommuterLineID() {
//        return commuterLineID;
//    }
//
//    public void setCommuterLineID(String commuterLineID) {
//        this.commuterLineID = commuterLineID;
//    }
//
//    public Date getDepartureDate() {
//        return departureDate;
//    }
//
//    public void setDepartureDate(Date departureDate) {
//        this.departureDate = departureDate;
//    }
//
//    public String getOperatorShortCode() {
//        return operatorShortCode;
//    }
//
//    public void setOperatorShortCode(String operatorShortCode) {
//        this.operatorShortCode = operatorShortCode;
//    }
//
//    public int getOperatorUICCode() {
//        return operatorUICCode;
//    }
//
//    public void setOperatorUICCode(int operatorUICCode) {
//        this.operatorUICCode = operatorUICCode;
//    }
//
//    public boolean isRunningCurrently() {
//        return runningCurrently;
//    }
//
//    public void setRunningCurrently(boolean runningCurrently) {
//        this.runningCurrently = runningCurrently;
//    }
//
//    public List<TimeTableRow> getTimeTableRows() {
//        return timeTableRows;
//    }
//
//    public void setTimeTableRows(List<TimeTableRow> timeTableRows) {
//        this.timeTableRows = timeTableRows;
//    }
//
//    public Date getTimetableAcceptanceDate() {
//        return timetableAcceptanceDate;
//    }
//
//    public void setTimetableAcceptanceDate(Date timetableAcceptanceDate) {
//        this.timetableAcceptanceDate = timetableAcceptanceDate;
//    }
//
//    public String getTimetableType() {
//        return timetableType;
//    }
//
//    public void setTimetableType(String timetableType) {
//        this.timetableType = timetableType;
//    }
//
//    public String getTrainCategory() {
//        return trainCategory;
//    }
//
//    public void setTrainCategory(String trainCategory) {
//        this.trainCategory = trainCategory;
//    }
//
//    public int getTrainNumber() {
//        return trainNumber;
//    }
//
//    public void setTrainNumber(int trainNumber) {
//        this.trainNumber = trainNumber;
//    }
//
//    public String getTrainType() {
//        return trainType;
//    }
//
//    public void setTrainType(String trainType) {
//        this.trainType = trainType;
//    }
//
//    public long getVersion() {
//        return version;
//    }
//
//    public void setVersion(long version) {
//        this.version = version;
//    }
//}

//@JsonIgnoreProperties(ignoreUnknown = true)
//class TimeTableRow {
//    String stationShortCode;
//    int stationUICCode;
//    String countrycode;
//    String type;
//    boolean trainStopping;
//    boolean commercialStop;
//    String commercialTrack;
////    Juna cancelled;
//    Date scheduledTime;
//    boolean unknownDelay;
//
//
//    public String getStationShortCode() {
//        return stationShortCode;
//    }
//
//    public void setStationShortCode(String stationShortCode) {
//        this.stationShortCode = stationShortCode;
//    }
//
//    public int getStationUICCode() {
//        return stationUICCode;
//    }
//
//    public void setStationUICCode(int stationUICCode) {
//        this.stationUICCode = stationUICCode;
//    }
//
//    public String getCountrycode() {
//        return countrycode;
//    }
//
//    public void setCountrycode(String countrycode) {
//        this.countrycode = countrycode;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public boolean isTrainStopping() {
//        return trainStopping;
//    }
//
//    public void setTrainStopping(boolean trainStopping) {
//        this.trainStopping = trainStopping;
//    }
//
//    public boolean isCommercialStop() {
//        return commercialStop;
//    }
//
//    public void setCommercialStop(boolean commercialStop) {
//        this.commercialStop = commercialStop;
//    }
//
//    public String getCommercialTrack() {
//        return commercialTrack;
//    }
//
//    public void setCommercialTrack(String commercialTrack) {
//        this.commercialTrack = commercialTrack;
//    }
//
//    public Date getScheduledTime() {
//        return scheduledTime;
//    }
//
//    public void setScheduledTime(Date scheduledTime) {
//        this.scheduledTime = scheduledTime;
//    }
//
//    public boolean isUnknownDelay() {
//        return unknownDelay;
//    }
//
//    public void setUnknownDelay(boolean unknownDelay) {
//        this.unknownDelay = unknownDelay;
//    }
//}

