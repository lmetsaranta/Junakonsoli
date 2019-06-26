package fi.academy.junakonsoli;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeTableRow {
        String stationShortCode;
        int stationUICCode;
        String countryCode;
        String type;
        boolean trainStopping;
        boolean commercialStop;
        String commercialTrack;
        boolean cancelled;
        Date scheduledTime;
        boolean unknownDelay;


        public String getStationShortCode() {
            return stationShortCode;
        }

        public void setStationShortCode(String stationShortCode) {
            this.stationShortCode = stationShortCode;
        }

        public int getStationUICCode() {
            return stationUICCode;
        }

        public void setStationUICCode(int stationUICCode) {
            this.stationUICCode = stationUICCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountrycode(String countrycode) {
            this.countryCode = countrycode;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isTrainStopping() {
            return trainStopping;
        }

        public void setTrainStopping(boolean trainStopping) {
            this.trainStopping = trainStopping;
        }

        public boolean isCommercialStop() {
            return commercialStop;
        }

        public void setCommercialStop(boolean commercialStop) {
            this.commercialStop = commercialStop;
        }

        public String getCommercialTrack() {
            return commercialTrack;
        }

        public void setCommercialTrack(String commercialTrack) {
            this.commercialTrack = commercialTrack;
        }

        public Date getScheduledTime() {
            return scheduledTime;
        }

        public void setScheduledTime(Date scheduledTime) {
            this.scheduledTime = scheduledTime;
        }

        public boolean isUnknownDelay() {
            return unknownDelay;
        }

        public void setUnknownDelay(boolean unknownDelay) {
            this.unknownDelay = unknownDelay;
        }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    //Täältä poistettava static mahdollisesti
    public static LocalDateTime paivamaaraMuunto(Date aika) {
            return aika.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
    }

    //Tässä myös turha static
    public static String paivamaara(Date aika) {
            return paivamaaraMuunto(aika).getDayOfMonth() + "." + paivamaaraMuunto(aika).getMonthValue() + "." + paivamaaraMuunto(aika).getYear();
    }

    //Tässäkin turha static
    public static String kellonaika(Date aika) {
            return paivamaaraMuunto(aika).getHour() + ":" + paivamaaraMuunto(aika).getMinute();
    }

}
